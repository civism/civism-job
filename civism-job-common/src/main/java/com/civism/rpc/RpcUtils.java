package com.civism.rpc;

import com.alipay.remoting.ConnectionEventType;
import com.alipay.remoting.rpc.RpcClient;
import com.alipay.remoting.rpc.RpcServer;
import com.civism.rpc.processor.ConnectEventProcessor;
import com.civism.rpc.processor.DisconnectEventProcessor;
import com.civism.rpc.processor.SyncRpcClientUserProcessor;
import com.civism.rpc.processor.SyncRpcServerUserProcessor;


/**
 * @author star
 * @date 2018/8/10 下午3:15
 */
public class RpcUtils {

    private static RpcServer server;

    private static RpcClient client;

    private static void initServer(Integer port) {
        server = new RpcServer(port);
        SerializeUtils.init();
        ConnectEventProcessor connectEventProcessor = new ConnectEventProcessor();
        DisconnectEventProcessor disconnectEventProcessor = new DisconnectEventProcessor();
        server.addConnectionEventProcessor(ConnectionEventType.CONNECT, connectEventProcessor);
        server.addConnectionEventProcessor(ConnectionEventType.CLOSE, disconnectEventProcessor);
        server.registerUserProcessor(new SyncRpcServerUserProcessor());
        server.start();
    }


    private static void initClient() {
        client = new RpcClient();
        SerializeUtils.init();
        ConnectEventProcessor connectEventProcessor = new ConnectEventProcessor();
        DisconnectEventProcessor disconnectEventProcessor = new DisconnectEventProcessor();
        client.addConnectionEventProcessor(ConnectionEventType.CONNECT, connectEventProcessor);
        client.addConnectionEventProcessor(ConnectionEventType.CLOSE, disconnectEventProcessor);
        client.registerUserProcessor(new SyncRpcClientUserProcessor());
        client.init();
    }

    public static RpcServer getServerInstance(Integer port) {
        if (server == null) {
            synchronized (RpcUtils.class) {
                if (server == null) {
                    initServer(port);
                }
            }
        }
        return server;
    }


    public static RpcClient getClientInstance() {
        if (client == null) {
            synchronized (RpcUtils.class) {
                if (client == null) {
                    initClient();
                }
            }
        }
        return client;
    }

}
