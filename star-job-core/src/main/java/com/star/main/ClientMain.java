package com.star.main;

import com.star.model.Message;
import com.star.model.RpcRequest;
import com.star.netserver.netty.client.NettyClient;
import com.star.serialize.Serialize;
import com.star.serialize.impl.HessianSerialize;

/**
 * Created by star on 2017/8/15.
 */
public class ClientMain {
    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        Serialize serialize = new HessianSerialize();
        client.setSerialize(serialize);
        client.setIp("127.0.0.1");
        client.setPort(9999);
        try {
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
