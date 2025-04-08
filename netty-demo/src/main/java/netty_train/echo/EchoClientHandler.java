package netty_train.echo;

/**
 * @author zhangye
 * @version 2.0
 * @date 2025-03-31 15:49
 * @description
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @description: echo客户端业务处理类
 **/
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     *此方法会在连接到服务器后被调用
     * */
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks hi!", CharsetUtil.UTF_8));
    }

    /**
     *此方法会在接收到服务器数据后调用
     * */
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println("客户端收到数据: " + in.toString(CharsetUtil.UTF_8));
    }

    /**
     *捕捉到异常
     * */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}

