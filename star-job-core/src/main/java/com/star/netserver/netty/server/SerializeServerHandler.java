package com.star.netserver.netty.server;

import com.star.factory.CenterFactory;
import com.star.model.RpcRequest;
import com.star.model.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by star on 2017/8/7.
 */
public class SerializeServerHandler extends SimpleChannelInboundHandler {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest request = (RpcRequest) msg;
        //业务处理中心工厂
        CenterFactory factory = new CenterFactory();

        RpcResponse response = new RpcResponse();
        ctx.writeAndFlush(response);
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
