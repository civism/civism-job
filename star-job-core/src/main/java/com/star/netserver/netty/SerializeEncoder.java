package com.star.netserver.netty;

import com.star.serialize.Serialize;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by star on 2017/8/18.
 * Java默认的序列化机制效率很低、序列化后的码流也较大
 * 采用第三方的序列化框架
 */
public class SerializeEncoder extends MessageToByteEncoder {

    private Class<?> clazz;

    private Serialize serialize;

    public SerializeEncoder(Class<?> clazz, Serialize serialize) {
        this.clazz = clazz;
        this.serialize = serialize;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        //判断类型是否对应
        if (!clazz.isInstance(o)) {
            return;
        }
        byte[] serialize = this.serialize.serialize(o);
        byteBuf.writeInt(serialize.length);
        byteBuf.writeBytes(serialize);
    }
}
