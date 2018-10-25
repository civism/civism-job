package com.civism.rpc.processor;

import com.alibaba.fastjson.JSON;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.SyncUserProcessor;
import com.civism.rpc.RpcRequest;


/**
 * @author star
 * @date 2018/8/9 下午2:42
 * 同步处理结果
 */
public class SyncRpcClientUserProcessor extends SyncUserProcessor<RpcRequest> {

    @Override
    public Object handleRequest(BizContext bizContext, RpcRequest request) throws Exception {
        System.out.println("收到消息" + JSON.toJSONString(request));
        return request;
    }

    @Override
    public String interest() {
        return RpcRequest.class.getName();
    }
}
