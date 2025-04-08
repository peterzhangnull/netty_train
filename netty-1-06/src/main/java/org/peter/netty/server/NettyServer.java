package org.peter.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zhangye
 * @version 2.0
 * @date 2025-04-08 21:51
 * @description
 */
public class NettyServer {
    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.bind(7397);
    }

    private void bind(int port) {
        //配置服务端NIO线程组
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childHandler(new MyChannelInitializer());
        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("netty server started on port " + port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }
    }
}
