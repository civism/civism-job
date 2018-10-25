package com.civism.job.quartz;


import com.civism.constants.CivismConstants;
import com.civism.job.route.CivismJob;
import com.civism.job.schedule.GuavaJobApplication;
import org.quartz.*;

/**
 * @author star
 * @date 2018/1/25 下午4:28
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ExecuteJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("调用了");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        CivismJob ruhnnJob = (CivismJob) jobDataMap.get(CivismConstants.JOB_DETAIL);
        //处理任务链
        GuavaJobApplication.handlerManager.handler(ruhnnJob);
    }
}
