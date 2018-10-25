package com.civism.rpc.processor;

import com.alibaba.fastjson.JSON;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.SyncUserProcessor;
import com.civism.rpc.RpcRequest;
import com.civism.rpc.SpringBeanMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


/**
 * @author star
 * @date 2018/8/9 下午5:40
 */
public class SyncRpcServerUserProcessor extends SyncUserProcessor<RpcRequest> {

    private static final Logger logger = LoggerFactory.getLogger(SyncRpcServerUserProcessor.class);

    @Override
    public Object handleRequest(BizContext bizContext, RpcRequest request) throws Exception {
        Object o = SpringBeanMap.get(request.getName());
        if (o == null) {
            logger.warn("没有发现调用类，请检查该调用类是否为spring的bean#####request={}", JSON.toJSONString(request));
            return null;
        }
        logger.info("\n\t\t\t\t\t\t\t\t>>>>>>>>任务调度执行<<<<<<<<<<\n" +
                "\t\t\t\t\t\t\t\t>>>requestId:" + request.getRequestId() + "<<<<<<<<<<\n" +
                "\t\t\t\t\t\t\t\t>>bean:" + request.getName() + ">>method:" + request.getMethod() + "<<<<<<<" +
                "\n\t\t\t\t\t\t\t\t>>>>>>>>>传递参数：" + JSON.toJSONString(request.getParams()) + "<<<<\n"
                + "\t\t\t\t\t\t\t\t>>>>>>>>>任务调度over<<<<<<<<<\n");
        if (request.getParams() != null && request.getParams().length == request.getParamsType().length) {
            Method method = o.getClass().getMethod(request.getMethod(), request.getParamsType());
            return method.invoke(o, request.getParams());
        } else {
            Method method = o.getClass().getMethod(request.getMethod());
            return method.invoke(o);
        }
    }

    @Override
    public String interest() {
        return RpcRequest.class.getName();
    }
}
