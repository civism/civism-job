package com.civism.job.route.chain;

import com.alibaba.fastjson.JSON;
import com.alipay.remoting.rpc.RpcClient;
import com.alipay.remoting.rpc.RpcResponseFuture;
import com.civism.job.constants.JobRecordStatus;
import com.civism.job.route.CivismJob;
import com.civism.job.route.GuavaJobHandler;
import com.civism.job.route.Handler;
import com.civism.job.route.callback.GuavaInvokeCallback;
import com.civism.job.schedule.GuavaJobApplication;
import com.civism.rpc.InvokeType;
import com.civism.rpc.RpcRequest;
import com.civism.rpc.RpcUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * @author star
 * @date 2018/8/9 上午10:45
 */
@Service
public class ExecuteJobHandler implements Handler {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteJobHandler.class);

    @Resource
    private GuavaInvokeCallback guavaInvokeCallback;

    @Override
    public void doHandler(CivismJob civismJob, GuavaJobHandler handler) {
        try {
            RpcRequest request = new RpcRequest();
            request.setRequestId(UUID.randomUUID().toString().replaceAll("-", ""));
            request.setName(civismJob.getBeanName());
            request.setMethod(civismJob.getMethod());
            request.setParams(civismJob.getParams());
            request.setParamsType(civismJob.getParamsType());
            if (StringUtils.isEmpty(civismJob.getExecuteIp())) {
                GuavaJobApplication.ruhnnJobDealHandle.saveJobRecord(civismJob, request, civismJob.getExecuteIp(), JobRecordStatus.RECORD_NO_CHANEL);
                return;
            }

            GuavaJobApplication.ruhnnJobDealHandle.saveJobRecord(civismJob, request, civismJob.getExecuteIp(), JobRecordStatus.RECORD_LOADING);
            callTask(civismJob.getInvokeType(), civismJob.getExecuteIp(), request, civismJob.getTimeOut());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void callTask(String invokeType, String address, RpcRequest request, Integer timeOut) {
        RpcClient client = RpcUtils.getClientInstance();
        try {
            if (invokeType.equalsIgnoreCase(InvokeType.ONEWAY.name())) {
                // 当前线程发起调用后，不关心调用结果，不做超时控制，只要请求已经发出，
                // 就完成本次调用。注意 Oneway 调用不保证成功，而且发起方无法知道调用结果。
                // 因此通常用于可以重试，或者定时通知类的场景，调用过程是有可能因为网络问题，机器故障等原因，
                // 导致请求失败。业务场景需要能接受这样的异常场景，才可以使用。

                //oneway 不关心响应，请求线程不会被阻塞，但使用时需要注意控制调用节奏，防止压垮接收方
                client.oneway(address, request);
                GuavaJobApplication.ruhnnJobDealHandle.updateJobRecord(JobRecordStatus.RECORD_SUCCESS, request.getRequestId(), null);
            } else if (invokeType.equalsIgnoreCase(InvokeType.CALLBACK.name())) {
                // 当前线程发起调用，则本次调用马上结束，可以马上执行下一次调用。
                // 发起调用时需要注册一个回调，该回调需要分配一个异步线程池。
                // 待响应回来后，会在回调的异步线程池，来执行回调逻辑

                //callback 是真正的异步调用，永远不会阻塞线程，结果处理是在异步线程里执行。
                guavaInvokeCallback.setRequestId(request.getRequestId());
                client.invokeWithCallback(address, request, guavaInvokeCallback, timeOut);
            } else if (invokeType.equalsIgnoreCase(InvokeType.FUTURE.name())) {
                //当前线程发起调用，得到一个 RpcResponseFuture 对象，当前线程可以继续执行下一次调用。
                // 可以在任意时刻，使用 RpcResponseFuture 对象的 get() 方法来获取结果，如果响应已经回来，此时就马上得到结果；
                // 如果响应没有回来，则会阻塞住当前线程，直到响应回来，或者超时时间到

                //future 调用，在调用过程不会阻塞线程，但获取结果的过程会阻塞线程；
                RpcResponseFuture rpcResponseFuture = client.invokeWithFuture(address, request, timeOut);
                Object o = rpcResponseFuture.get();
                GuavaJobApplication.ruhnnJobDealHandle.updateJobRecord(JobRecordStatus.RECORD_SUCCESS, request.getRequestId(), JSON.toJSONString(o));
            } else {
                //当前线程发起调用后，需要在指定的超时时间内，等到响应结果，才能完成本次调用。
                // 如果超时时间内没有得到结果，那么会抛出超时异常。这种调用模式最常用。注意要根据对端的处理能力，合理设置超时时间

                //sync 调用会阻塞请求线程，待响应返回后才能进行下一个请求。这是最常用的一种通信模型
                Object o = client.invokeSync(address, request, timeOut);
                GuavaJobApplication.ruhnnJobDealHandle.updateJobRecord(JobRecordStatus.RECORD_SUCCESS, request.getRequestId(), JSON.toJSONString(o));
            }
        } catch (Exception e) {
            logger.error("任务调度失败>>>>>>>request={}", JSON.toJSONString(request));
            e.printStackTrace();
            GuavaJobApplication.ruhnnJobDealHandle.updateJobRecord(JobRecordStatus.RECORD_FAIL, request.getRequestId(), e.getMessage());
        }
    }
}
