package com.civism.zookeeper;

/**
 * @author star
 * @date 2018/8/3 下午2:16
 */
public class ZkClientException extends RuntimeException {
    public ZkClientException(String msg) {
        super(msg);
    }

    public ZkClientException(String message, Exception e) {
        super(message, e);
    }

    public ZkClientException(Exception e) {
        super(e);
    }
}
