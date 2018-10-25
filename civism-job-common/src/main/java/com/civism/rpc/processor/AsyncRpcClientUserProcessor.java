package com.civism.rpc.processor;

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.AsyncUserProcessor;
import com.civism.rpc.RpcRequest;


/**
 * @author star
 * @date 2018/8/9 下午2:45
 * 异步
 */
public class AsyncRpcClientUserProcessor extends AsyncUserProcessor<RpcRequest> {


    @Override
    public void handleRequest(BizContext bizContext, AsyncContext asyncContext, RpcRequest request) {

    }

    @Override
    public String interest() {
        return RpcRequest.class.getName();
    }
}
