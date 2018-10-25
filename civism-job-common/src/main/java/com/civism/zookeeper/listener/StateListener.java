package com.civism.zookeeper.listener;

import org.apache.zookeeper.Watcher;

/**
 * @author star
 * @date 2018/8/3 下午2:01
 * zooleeper 客户端状态发生变化时的监听类
 * 目前只有2种事件可以被监听。
 * 1. 连接断开
 * 2. 连接重新连上
 */
public interface StateListener {

    void listen(Watcher.Event.KeeperState state);

}
