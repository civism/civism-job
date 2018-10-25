package com.civism.job.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;

/**
 * @author star
 * @date 2018/1/30 下午3:36
 * 这儿参考了许雪丽的代码，在做调度的时候，发现quart支持
 * github 上面也有
 */
public class ScheduleFactory {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleFactory.class);

    @Resource
    private Scheduler combCenterSchedulerBean;

    public boolean addJob(CivismJobDetail ruhnnJobDetail) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(ruhnnJobDetail.getTaskName(), ruhnnJobDetail.getGroupName());
        JobKey jobKey = new JobKey(ruhnnJobDetail.getTaskName(), ruhnnJobDetail.getGroupName());
        if (existJob(ruhnnJobDetail.getTaskName(), ruhnnJobDetail.getGroupName())) {
            logger.info(">>>>>>>>> addJob fail, job already exist, jobGroup:{}, jobName:{}", ruhnnJobDetail.getGroupName(), ruhnnJobDetail.getTaskName());
            return false;
        }
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(ruhnnJobDetail.getCron()).withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

        JobDetail jobDetail = JobBuilder.newJob(ExecuteJob.class).storeDurably(true).usingJobData(ruhnnJobDetail.getDataMap()).withIdentity(jobKey).build();
        Date date = combCenterSchedulerBean.scheduleJob(jobDetail, cronTrigger);
        logger.info(">>>>>>>>>>> addJob success, jobDetail:{}, cronTrigger:{}, date:{}", jobDetail, cronTrigger, date);
        return true;
    }


    public boolean deleteJob(String groupName, String taskName) throws SchedulerException {

        TriggerKey tk = TriggerKey.triggerKey(taskName, groupName);
        combCenterSchedulerBean.pauseTrigger(tk);
        combCenterSchedulerBean.unscheduleJob(tk);
        JobKey jobKey = JobKey.jobKey(taskName, groupName);
        combCenterSchedulerBean.deleteJob(jobKey);
        return true;
    }

    public boolean existJob(String groupName, String taskName) throws SchedulerException {
        TriggerKey tk = TriggerKey.triggerKey(taskName, groupName);
        return combCenterSchedulerBean.checkExists(tk);
    }

    /**
     * 修改任务
     *
     * @param groupName
     * @param taskName
     * @param cron
     * @return
     */
    public boolean rescheduleJob(String groupName, String taskName, String cron) throws SchedulerException {
        if (!existJob(groupName, taskName)) {
            logger.info(">>>>>>>>>>> rescheduleJob fail, job not exists, JobGroup:{}, JobName:{}", groupName, taskName);
            return false;
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(taskName, groupName);
        CronTrigger oldTrigger = (CronTrigger) combCenterSchedulerBean.getTrigger(triggerKey);
        if (oldTrigger != null) {
            String oldCron = oldTrigger.getCronExpression();
            if (oldCron.equals(cron)) {
                return true;
            }
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing();
            oldTrigger = oldTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
            combCenterSchedulerBean.rescheduleJob(triggerKey, oldTrigger);
        } else {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
            JobKey jobKey = new JobKey(taskName, groupName);
            JobDetail jobDetail = combCenterSchedulerBean.getJobDetail(jobKey);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);
            combCenterSchedulerBean.scheduleJob(jobDetail, triggerSet, true);
        }

        logger.info(">>>>>>>>>>> resumeJob success, JobGroup:{}, JobName:{}", groupName, taskName);
        return true;
    }

    /**
     * 暂停任务
     *
     * @param groupName
     * @param taskName
     * @return
     */
    public boolean pauseJob(String groupName, String taskName) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(taskName, groupName);

        if (existJob(groupName, taskName)) {
            combCenterSchedulerBean.pauseTrigger(triggerKey);
            logger.info(">>>>>>>>>>> pauseJob success, triggerKey:{}", triggerKey);
            return true;
        } else {
            logger.info(">>>>>>>>>>> pauseJob fail, triggerKey:{}", triggerKey);
        }
        return false;
    }

    /**
     * 恢复任务
     *
     * @param groupName
     * @param taskName
     * @return
     */
    public boolean resumeJob(String groupName, String taskName) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(taskName, groupName);
        if (existJob(groupName, taskName)) {
            combCenterSchedulerBean.resumeTrigger(triggerKey);
            logger.info(">>>>>>>>>>> resumeJob success, triggerKey:{}", triggerKey);
            return true;
        } else {
            logger.info(">>>>>>>>>>> resumeJob fail, triggerKey:{}", triggerKey);
        }
        return false;
    }


    public void destroy() {
        try {
            if (!combCenterSchedulerBean.isShutdown()) {
                combCenterSchedulerBean.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            if (!combCenterSchedulerBean.isStarted()) {
                combCenterSchedulerBean.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
