package org.peter.netty.server;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author zhangye
 * @version 2.0
 * @date 2025-04-08 21:44
 * @description
 */
public class ChannelHandler {

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
