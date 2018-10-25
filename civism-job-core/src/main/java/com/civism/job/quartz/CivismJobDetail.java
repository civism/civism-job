package com.civism.job.quartz;

import org.quartz.JobDataMap;


/**
 * @author star
 * @date 2018/1/30 下午3:42
 */
public class CivismJobDetail {
    private String groupName;
    private String taskName;
    private String cron;

    private JobDataMap dataMap;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public JobDataMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(JobDataMap dataMap) {
        this.dataMap = dataMap;
    }
}
