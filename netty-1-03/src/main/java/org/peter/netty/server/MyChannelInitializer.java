package org.peter.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangye
 * @version 2.0
 * @date 2025-04-07 23:11
 * @description
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        /* 解码器 */
        // 基于换行符号
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 基于指定字符串【换行符，这样功能等同于LineBasedFrameDecoder】
        // e.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, false, Delimiters.lineDelimiter()));
        // 基于最大长度
        // e.pipeline().addLast(new FixedLengthFrameDecoder(4));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        socketChannel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
        //在管道中添加我们自己的接收数据实现方法
        socketChannel.pipeline().addLast(new MyServerHandler());
    }
}
