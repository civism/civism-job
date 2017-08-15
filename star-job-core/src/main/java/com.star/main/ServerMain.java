package com.star.main;

import com.star.netserver.netty.server.NettyServer;

/**
 * Created by star on 2017/8/15.
 */
public class ServerMain {

    public static void main(String[] args) {
        NettyServer server = new NettyServer();
        server.setIp("127.0.0.1");
        server.setPort(9999);
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
