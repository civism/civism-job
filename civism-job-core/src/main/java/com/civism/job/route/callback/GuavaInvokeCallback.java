package com.civism.job.route.callback;

import com.alibaba.fastjson.JSON;
import com.alipay.remoting.InvokeCallback;
import com.civism.job.constants.JobRecordStatus;
import com.civism.job.schedule.GuavaJobApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author star
 * @date 2018/8/13 下午4:26
 */
@Service
public class GuavaInvokeCallback implements InvokeCallback {

    private static final Logger logger = LoggerFactory.getLogger(GuavaInvokeCallback.class);

    private String requestId;

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 20, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());

    @Override
    public void onResponse(Object o) {
        GuavaJobApplication.ruhnnJobDealHandle.updateJobRecord(JobRecordStatus.RECORD_SUCCESS, requestId, JSON.toJSONString(o));
    }

    @Override
    public void onException(Throwable throwable) {
        GuavaJobApplication.ruhnnJobDealHandle.updateJobRecord(JobRecordStatus.RECORD_FAIL, requestId, throwable.getMessage());
        logger.error("callback error", throwable);
    }

    @Override
    public Executor getExecutor() {
        logger.warn(">>>>>>>>>>>callback线程池资源,线程池中线程数目{},当前活跃线程数{}个,队列中等待执行的任务数目：{}个", executor.getPoolSize(), executor.getActiveCount(), executor.getQueue().size());
        return executor;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
