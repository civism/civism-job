package com.civism.job.observer;


import com.civism.job.constants.JobRecordStatus;

/**
 * @author star
 * @date 2018/10/23 下午3:07
 */
public interface Observer {
    void update(Observer o, JobRecordStatus status);
}
