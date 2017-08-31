package com.star.netserver;

import com.star.model.RpcRequest;
import com.star.model.RpcResponse;
import com.star.netserver.netty.SerializeDecoder;
import com.star.netserver.netty.SerializeEncoder;
import com.star.netserver.netty.client.SerializeClientHandler;
import com.star.serialize.Serialize;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * Created by star on 2017/8/31.
 */
public class ClientHandler extends ChannelInitializer {


    private Serialize serialize;

    public ClientHandler(Serialize serialize) {
        this.serialize = serialize;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new SerializeDecoder(RpcRequest.class, serialize));
        channel.pipeline().addLast(new SerializeEncoder(RpcResponse.class, serialize));
        channel.pipeline().addLast(new SerializeClientHandler());
    }
}
