package org.peter.netty.aio.client;

import org.peter.netty.aio.ChannelAdapter;
import org.peter.netty.aio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author zhangye
 * @version 2.0
 * @date 2025-04-02 18:40
 * @description
 */
public class AioClientHandler extends ChannelAdapter {


    public AioClientHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println(" aio client active done: " + ctx.getChannel().getRemoteAddress());
            // 通知客户端链接建立成功
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(" aio client read done" + new Date() + " " + msg + "\r\n");
        ctx.writeAndFlush("客户端信息处理success！ \r\n");
    }
}
