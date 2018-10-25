package com.civism.job.constants;

/**
 * @author star
 * @date 2018/10/25 下午12:02
 */
public enum JobRecordStatus {
    RECORD_LOADING(0, "调用中"),
    RECORD_SUCCESS(1, "成功"),
    RECORD_FAIL(2, "失败"),
    RECORD_NO_CHANEL(3, "没有发现通信通道");
    private Integer status;
    private String desc;

    JobRecordStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static JobRecordStatus getRecordDesc(Integer status) {
        JobRecordStatus[] values = JobRecordStatus.values();
        for (JobRecordStatus jobRecordStatus : values) {
            if (jobRecordStatus.getStatus().intValue() == status.intValue()) {
                return jobRecordStatus;
            }
        }
        return RECORD_FAIL;
    }
}
