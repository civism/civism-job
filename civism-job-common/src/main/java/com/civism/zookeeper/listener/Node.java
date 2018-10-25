package com.civism.zookeeper.listener;

/**
 * @author star
 * @date 2018/8/3 下午2:07
 */
public class Node {
    private String path;
    private byte[] data;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
