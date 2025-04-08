package netty_train.echo;

/**
 * @author zhangye
 * @version 2.0
 * @date 2025-03-31 15:42
 * @description
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // Boss 线程
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // Worker 线程

        try {
            // ServerBootstrap 配置服务器。
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            // StringDecoder/Encoder 处理字符串。
                            p.addLast(new StringDecoder()); // 解码
                            p.addLast(new StringEncoder()); // 编码
                            // SimpleChannelInboundHandler 实现回显逻辑。
                            p.addLast(new SimpleChannelInboundHandler<String>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                    System.out.println("Received: " + msg);
                                    ctx.writeAndFlush(msg); // 回显
                                }
                            });
                        }
                    });

            ChannelFuture f = b.bind(8080).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
