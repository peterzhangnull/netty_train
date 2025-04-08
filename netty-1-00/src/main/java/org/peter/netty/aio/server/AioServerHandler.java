package org.peter.netty.aio.server;

import org.peter.netty.aio.ChannelAdapter;
import org.peter.netty.aio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author zhangye
 * @version 2.0
 * @date 2025-04-02 23:14
 * @description
 */
public class AioServerHandler extends ChannelAdapter {

    public AioServerHandler(AsynchronousSocketChannel socketChannel, Charset charset) {
        super(socketChannel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println(" aio server channel active " + ctx.getChannel().getRemoteAddress());
            // 通知客户端链接建立成功
            ctx.writeAndFlush("通知客户端链接建立成功" + " " + new Date() + " " + ctx.getChannel().getRemoteAddress() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(" aio server channelRead " + new Date() + " " + msg);
        ctx.writeAndFlush("服务端信息处理success！\r\n");
    }
}
