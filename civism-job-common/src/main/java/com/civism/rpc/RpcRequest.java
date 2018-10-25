package com.civism.rpc;

import java.io.Serializable;

/**
 * @author star
 * @date 2018/1/29 下午2:16
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 959929637375335218L;

    /**
     * 唯一ID
     */
    private String requestId;

    /**
     * 类名
     */
    private String name;

    /**
     * 方法
     */
    private String method;

    /**
     * 任务ID
     */
    private Integer taskId;

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


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

}

