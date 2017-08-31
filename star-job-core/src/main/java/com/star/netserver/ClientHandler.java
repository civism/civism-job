package com.star.netserver;

import com.star.netserver.netty.SerializeDecoder;
import com.star.netserver.netty.SerializeEncoder;
import com.star.netserver.netty.client.SerializeClientHandler;
import com.star.serialize.Serialize;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by star on 2017/8/31.
 */
public class ClientHandler extends ChannelInitializer {

    private Class<?> clazz;

    private Serialize serialize;

    public ClientHandler(Class<?> clazz, Serialize serialize) {
        this.clazz = clazz;
        this.serialize = serialize;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new SerializeDecoder(clazz, serialize));
        channel.pipeline().addLast(new SerializeEncoder(clazz, serialize));
        channel.pipeline().addLast(new SerializeClientHandler());
    }
}
