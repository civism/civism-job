package com.civism;


import com.civism.constants.CivismConstants;
import com.civism.job.quartz.CivismJobDetail;
import com.civism.job.quartz.ScheduleFactory;
import com.civism.job.route.CivismJob;
import com.civism.rpc.InvokeType;
import org.junit.Test;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;

import javax.annotation.Resource;

/**
 * @author star
 * @date 2018/8/30 下午4:16
 */
public class TaskJobTest extends BaseTest {

    @Resource
    private ScheduleFactory scheduleFactory;


    @Test
    public void 暂停任务() throws SchedulerException {
        scheduleFactory.pauseJob("guava", "com.ruhnn.service.impl.HelloWordServiceImpl:sayHello");
    }

    @Test
    public void 恢复任务() throws SchedulerException {
        scheduleFactory.resumeJob("guava", "com.ruhnn.service.impl.HelloWordServiceImpl:sayHello");
    }

    @Test
    public void 更新任务() throws SchedulerException {
        scheduleFactory.rescheduleJob("guava", "com.ruhnn.service.impl.HelloWordServiceImpl:sayHello", "0/30 * * * * ?");
    }

    @Test
    public void 添加quartz任务() throws SchedulerException {
        CivismJob civismJob = new CivismJob();
        civismJob.setBeanName("com.ruhnn.service.impl.HelloWordServiceImpl");
        civismJob.setMethod("sayHello");
        civismJob.setTimeOut(3000);
        //0 自动发现IP机制  1指定IP机制
        civismJob.setJobType(0);
        civismJob.setInvokeType(InvokeType.CALLBACK.name());
        CivismJobDetail ruhnnJobDetail = new CivismJobDetail();
        ruhnnJobDetail.setTaskName("com.ruhnn.service.impl.HelloWordServiceImpl:sayHello");
        ruhnnJobDetail.setGroupName("guava");
        ruhnnJobDetail.setCron("0/30 * * * * ?");
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(CivismConstants.JOB_DETAIL, civismJob);
        ruhnnJobDetail.setDataMap(jobDataMap);
        scheduleFactory.addJob(ruhnnJobDetail);
    }


    @Test
    public void 删除quartz任务() throws SchedulerException {
        scheduleFactory.deleteJob("guava", "com.ruhnn.service.impl.HelloWordServiceImpl:sayHello");
    }


}
