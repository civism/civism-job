package com.star.netserver.netty.server;


import com.alibaba.fastjson.JSON;
import com.star.model.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by star on 2017/8/7.
 */
public class SerializeServerHandler extends SimpleChannelInboundHandler {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(JSON.toJSONString(msg));
        RpcResponse response = new RpcResponse();
        response.setContent("我是service");
        ctx.write(response);
        ctx.flush();
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
