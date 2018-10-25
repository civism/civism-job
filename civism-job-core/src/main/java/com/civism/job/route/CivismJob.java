package com.civism.job.route;


import java.io.Serializable;
import java.util.Set;

/**
 * @author star
 * @date 2018/8/7 下午3:51
 */
public class CivismJob implements Serializable {

    private static final long serialVersionUID = -5618556378377368625L;

    /**
     * beanName
     */
    private String beanName;

    /**
     * 方法名称
     */
    private String method;


    /**
     * 0自动发现1固定IP
     */
    private Integer jobType;

    /**
     * 指定的多个IP，不会通过ZK找IP，想到于直接链接
     */
    private Set<String> targetIps;

    /**
     * 执行IP
     */
    private String executeIp;

    /**
     * 超时时间
     */
    private Integer timeOut;

    /**
     * 调用类型
     */
    private String invokeType;

    /**
     * 负载方式
     */
    private Integer loadWay;

    /**
     * 用户指定的链接的IP，只会调用发生在这台机器上面
     */
    private String limitIp;

    private Object[] params;

    private Class[] paramsType;


    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class[] getParamsType() {
        return paramsType;
    }

    public void setParamsType(Class[] paramsType) {
        this.paramsType = paramsType;
    }

    public String getJobName() {
        return beanName + ":" + method;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public Set<String> getTargetIps() {
        return targetIps;
    }

    public void setTargetIps(Set<String> targetIps) {
        this.targetIps = targetIps;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }


    public String getInvokeType() {
        return invokeType;
    }

    public void setInvokeType(String invokeType) {
        this.invokeType = invokeType;
    }


    public String getExecuteIp() {
        return executeIp;
    }

    public void setExecuteIp(String executeIp) {
        this.executeIp = executeIp;
    }


    public Integer getLoadWay() {
        return loadWay;
    }

    public void setLoadWay(Integer loadWay) {
        this.loadWay = loadWay;
    }

    public String getLimitIp() {
        return limitIp;
    }

    public void setLimitIp(String limitIp) {
        this.limitIp = limitIp;
    }
}
