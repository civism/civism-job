package com.civism.job.schedule;


import com.civism.dao.JobRecordDao;
import com.civism.job.constants.JobRecordStatus;
import com.civism.job.observer.GuavaJobObserverManage;
import com.civism.job.observer.MessageSendObserver;
import com.civism.job.route.CivismJob;
import com.civism.model.JobRecordDO;
import com.civism.rpc.RpcRequest;
import com.civism.utils.IpUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author whs
 * @date 2018/8/15 下午2:40
 */
@Service
public class GuavaJobDealHandle {

    @Resource
    private JobRecordDao jobRecordDao;

    @Resource
    private GuavaJobObserverManage guavaJobObserverManage;

    @Resource
    private MessageSendObserver messageSendObserver;

    public void saveJobRecord(CivismJob ruhnnJob, RpcRequest request, String executeIp, JobRecordStatus jobRecordStatus) {
        JobRecordDO jobRecordDO = new JobRecordDO();
        jobRecordDO.setJobName(ruhnnJob.getJobName());
        jobRecordDO.setStatus(jobRecordStatus.getStatus());
        jobRecordDO.setRequestId(request.getRequestId());
        jobRecordDO.setStartTime(new Date());
        jobRecordDO.setJobType(ruhnnJob.getJobType());
        jobRecordDO.setSendIp(IpUtils.getIpAddress());
        jobRecordDO.setAcceptIp(executeIp);
        jobRecordDO.setInvokeType(ruhnnJob.getInvokeType());
        jobRecordDao.create(jobRecordDO);

        guavaJobObserverManage.registerObserver(messageSendObserver);

        guavaJobObserverManage.notifyObserver(jobRecordStatus);
    }

    public void updateJobRecord(JobRecordStatus jobRecordStatus, String requestId, String result) {
        JobRecordDO jobRecordDO = new JobRecordDO();
        jobRecordDO.setRequestId(requestId);
        jobRecordDO.setStatus(jobRecordStatus.getStatus());
        jobRecordDO.setResult(result);
        jobRecordDO.setEndTime(new Date());
        jobRecordDao.update(jobRecordDO);

        guavaJobObserverManage.registerObserver(messageSendObserver);
        guavaJobObserverManage.notifyObserver(jobRecordStatus);
    }
}
