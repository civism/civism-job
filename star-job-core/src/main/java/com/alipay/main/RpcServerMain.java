package com.alipay.main;

import com.alipay.CONNECTEventProcessor;
import com.alipay.DISCONNECTEventProcessor;
import com.alipay.remoting.ConnectionEventType;
import com.alipay.server.GuavaServer;
import com.alipay.server.SimpleServerUserProcessor;

import java.util.concurrent.TimeUnit;

/**
 * @author star
 * @date 2018/5/14 下午1:35
 */
public class RpcServerMain {

    SimpleServerUserProcessor serverUserProcessor = new SimpleServerUserProcessor();

    CONNECTEventProcessor serverConnectProcessor = new CONNECTEventProcessor();
    DISCONNECTEventProcessor serverDisConnectProcessor = new DISCONNECTEventProcessor();

    public RpcServerMain() {
        GuavaServer server = new GuavaServer(30001);
        server.addConnectionEventProcessor(ConnectionEventType.CONNECT, serverConnectProcessor);
        server.addConnectionEventProcessor(ConnectionEventType.CLOSE, serverDisConnectProcessor);
        server.registerUserProcessor(serverUserProcessor);
        // 4. server start
        server.start();
        System.out.println("server start ok!");
    }

    public static void main(String[] args) {
        new RpcServerMain();
    }
}
