package org.peter.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * @author zhangye
 * @version 2.0
 * @date 2025-04-08 10:47
 * @description
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 基于换行符号
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码器转String
        socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        socketChannel.pipeline().addLast(new MyServerHandler());
    }
}
