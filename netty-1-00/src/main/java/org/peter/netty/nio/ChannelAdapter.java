package org.peter.netty.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhangye
 * @version 2.0
 * @date 2025-04-05 22:25
 * @description
 */
public abstract class ChannelAdapter extends Thread {

    private Selector selector;

    private ChannelHandler channelHandler;

    private Charset charset;

    public ChannelAdapter(Selector selector, Charset charset) {
        this.selector = selector;
        this.charset = charset;
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    handleInput(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) {
            return;
        }
        Class<?> superclass = key.channel().getClass().getSuperclass();
        if (superclass == SocketChannel.class) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (socketChannel.finishConnect()) {
                    channelHandler = new ChannelHandler(socketChannel, charset);
                    channelActive(channelHandler);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else {
                    System.exit(1);
                }
            }
        }

        // 服务端ServerSocketChannel
        if (superclass == ServerSocketChannel.class) {
            if (key.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);

                channelHandler = new ChannelHandler(socketChannel, charset);
                channelActive(channelHandler);
            }
        }
        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int readBytes = socketChannel.read(readBuffer);
            if (readBytes > 0) {
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                channelRead(channelHandler, new String(bytes, charset));
            } else if (readBytes < 0) {
                key.cancel();
                socketChannel.close();
            }
        }
    }

    // 链接通知抽象类
    public abstract void channelActive(ChannelHandler ctx);

    // 读取消息抽象类
    public abstract void channelRead(ChannelHandler ctx, Object msg);
}
