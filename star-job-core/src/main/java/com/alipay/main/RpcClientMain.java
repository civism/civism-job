package com.alipay.main;

import com.alipay.CONNECTEventProcessor;
import com.alipay.DISCONNECTEventProcessor;
import com.alipay.RequestBody;
import com.alipay.remoting.ConnectionEventType;
import com.alipay.remoting.rpc.RpcClient;

/**
 * @author star
 * @date 2018/5/14 下午1:55
 */
public class RpcClientMain {

    private static RpcClient client;

    CONNECTEventProcessor clientConnectProcessor = new CONNECTEventProcessor();
    DISCONNECTEventProcessor clientDisConnectProcessor = new DISCONNECTEventProcessor();

    public RpcClientMain() {
        // 1. create a rpc client
        client = new RpcClient();
        // 2. add processor for connect and close event if you need
        client.addConnectionEventProcessor(ConnectionEventType.CONNECT, clientConnectProcessor);
        client.addConnectionEventProcessor(ConnectionEventType.CLOSE, clientDisConnectProcessor);
        // 3. do init
        client.init();
    }

    public static void main(String[] args) {
        new RpcClientMain();
        RequestBody req = new RequestBody(2, "hello world sync");
        try {
            String res = (String) client.invokeSync("127.0.0.1:30001", req, 3000);
            System.out.println("invoke sync result = [" + res + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.shutdown();
    }
}
