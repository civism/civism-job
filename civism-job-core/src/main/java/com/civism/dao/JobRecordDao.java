package com.civism.dao;

import com.civism.model.JobRecordDO;

/**
 * @author star
 * @date 2018/10/25 上午11:59
 */
public interface JobRecordDao {
    Boolean create(JobRecordDO jobRecordDO);

    Boolean update(JobRecordDO jobRecordDO);
}
