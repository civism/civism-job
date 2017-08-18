package com.star.netserver.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by star on 2017/8/18.
 * Java默认的序列化机制效率很低、序列化后的码流也较大
 * 采用第三方的序列化框架
 */
public class SerializeEncoder extends MessageToByteEncoder {

    //判断传送客户端传送过来的数据是否按照协议传输，
    // 头部信息的大小应该是 byte+byte+int = 1+1+4 = 6
    private static final int HEADER_SIZE = 6;
    
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {

    }
}
