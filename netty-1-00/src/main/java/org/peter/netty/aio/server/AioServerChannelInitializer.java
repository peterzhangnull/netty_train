package org.peter.netty.aio.server;

import org.peter.netty.aio.ChannelInitializer;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangye
 * @version 2.0
 * @date 2025-04-02 23:07
 * @description
 */
public class AioServerChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(AsynchronousSocketChannel socketChannel) throws Exception {
        socketChannel.read(ByteBuffer.allocate(1024), 10, TimeUnit.SECONDS,null,
                new AioServerHandler(socketChannel, StandardCharsets.UTF_8));
    }
}
