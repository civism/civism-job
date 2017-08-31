package com.star.netserver.netty.client;

import com.star.netserver.ClientHandler;
import com.star.serialize.Serialize;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.Callable;

/**
 * Created by star on 2017/8/7.
 */
public class NettyClient  implements Callable<Boolean>{


    private String ip;

    private Integer port;

    private Serialize serialize;

    public boolean start() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ClientHandler(serialize));
            ChannelFuture f = b.connect(ip, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
        return true;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Serialize getSerialize() {
        return serialize;
    }

    public void setSerialize(Serialize serialize) {
        this.serialize = serialize;
    }

    @Override
    public Boolean call() throws Exception {
        return this.start();
    }
}
