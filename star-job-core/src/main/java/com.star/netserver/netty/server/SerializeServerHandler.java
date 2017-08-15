package com.star.netserver.netty.server;

import com.alibaba.fastjson.JSON;
import com.star.model.Message;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by star on 2017/8/7.
 */
public class SerializeServerHandler extends SimpleChannelInboundHandler {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        System.out.println(JSON.toJSONString(message));
        Message tmp = new Message();
        tmp.setContent("hello client");
        tmp.setId(2);
        System.out.println("MessageServerHandler.channelRead()");
        ctx.writeAndFlush(tmp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}
