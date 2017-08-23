package com.star.netserver.netty;

import com.star.constanst.Constant;
import com.star.serialize.Serialize;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by star on 2017/8/18.
 * Java默认的序列化机制效率很低、序列化后的码流也较大
 * 采用第三方序列化框架
 */
public class SerializeDecoder extends ByteToMessageDecoder {

    private Class<?> clazz;

    private Serialize serialize;

    public SerializeDecoder(Class<?> clazz, Serialize serialize) {
        this.clazz = clazz;
        this.serialize = serialize;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        //出现粘包导致消息头长度不对，直接返回
        if (byteBuf.readableBytes() < Constant.HEADER_SIZE) {
            return;
        }
        byteBuf.markReaderIndex();
        //读取消息的内容长度
        int messageLength = byteBuf.readInt();
        if (messageLength < 0) {
            ctx.close();
        }
        //读到的消息长度和报文头的已知长度不匹配。那就重置一下ByteBuf读索引的位置
        if (byteBuf.readableBytes() < messageLength) {
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] messageBody = new byte[messageLength];
        byteBuf.readBytes(messageBody);
        Object obj = serialize.deserialize(messageBody, clazz);
        list.add(obj);
    }
}
