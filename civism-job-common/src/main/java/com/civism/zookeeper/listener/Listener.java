package com.civism.zookeeper.listener;

import com.civism.zookeeper.ZkClientException;
import org.apache.zookeeper.Watcher;

import java.net.SocketException;

/**
 * @author star
 * @date 2018/8/3 下午1:59
 */
public interface Listener {

    /**
     * 监听回调函数
     *
     * @param path      发生变化的节点路径
     * @param eventType 变化类型
     * @param data      变化数据，当监听的是数据变化时有效，其它为null
     * @throws ZkClientException
     * @throws SocketException
     */
    void listen(String path, Watcher.Event.EventType eventType, byte[] data) throws ZkClientException, SocketException;
}
