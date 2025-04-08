package org.peter.netty.aio;

import org.peter.netty.aio.server.AioServer;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author zhangye
 * @version 2.0
 * @date 2025-04-02 23:03
 * @description
 */
public abstract class ChannelInitializer implements CompletionHandler<AsynchronousSocketChannel, AioServer> {

    @Override
    public void completed(AsynchronousSocketChannel socketChannel, AioServer server) {
        try {
            initChannel(socketChannel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 此处接收客户端连接
            server.serverSocketChannel().accept(server, this);
        }
    }

    @Override
    public void failed(Throwable exc, AioServer server) {
        exc.getStackTrace();
    }

    protected abstract void initChannel(AsynchronousSocketChannel socketChannel) throws Exception;
}
