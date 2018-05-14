package com.alipay.server;

import com.alipay.remoting.ConnectionEventProcessor;
import com.alipay.remoting.ConnectionEventType;
import com.alipay.remoting.rpc.RpcServer;
import com.alipay.remoting.rpc.protocol.UserProcessor;

/**
 * @author star
 * @date 2018/5/14 下午12:05
 */
public class GuavaServer {
    private int port;

    private RpcServer server;

    // ~~~ constructors
    public GuavaServer(int port) {
        this.port = port;
        this.server = new RpcServer(this.port);
    }

    public GuavaServer(int port, boolean manageFeatureEnabled) {
        this.port = port;
        this.server = new RpcServer(this.port, manageFeatureEnabled);
    }

    public GuavaServer(int port, boolean manageFeatureEnabled, boolean syncStop) {
        this.port = port;
        this.server = new RpcServer(this.port, manageFeatureEnabled, syncStop);
    }

    public boolean start() {
        return this.server.start();
    }

    public void stop() {
        this.server.stop();
    }

    public RpcServer getRpcServer() {
        return this.server;
    }

    public void registerUserProcessor(UserProcessor<?> processor) {
        this.server.registerUserProcessor(processor);
    }

    public void addConnectionEventProcessor(ConnectionEventType type,
                                            ConnectionEventProcessor processor) {
        this.server.addConnectionEventProcessor(type, processor);
    }
}
