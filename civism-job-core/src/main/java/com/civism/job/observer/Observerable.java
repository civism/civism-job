package com.civism.job.observer;


import com.civism.job.constants.JobRecordStatus;

/**
 * @author star
 * @date 2018/10/23 下午3:05
 */
public interface Observerable {

    void registerObserver(Observer o);


    void removeObserver(Observer o);


    void notifyObserver(JobRecordStatus status);
}
