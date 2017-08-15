package com.star.netserver.netty.client;

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

/**
 * Created by star on 2017/8/7.
 */
public class NettyClient {

    private String ip;

    private Integer port;

    private Serialize serialize;

    public void start() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    //添加对象解码器，负责对POJO对象进行解码，设置对象序列化最大长度为1M，防止内存溢出
                    //设置线程安全的WeakReferenceMap对类加载器进行缓存，支持多线程并发，防止内存溢出
                    ch.pipeline().addLast(new ObjectDecoder(1024*1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                   //添加对象编辑器 在服务器对发送消息时自动将实现POJO对象编码
                    ch.pipeline().addLast(new ObjectEncoder());
                    ch.pipeline().addLast(new SerializeClientHandler());
                }
            });
            ChannelFuture f = b.connect(ip, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        try {
            new NettyClient().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
