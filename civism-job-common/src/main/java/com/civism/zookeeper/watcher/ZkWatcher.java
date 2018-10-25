package com.civism.zookeeper.watcher;


import com.civism.zookeeper.ZkClient;
import com.civism.zookeeper.ZkClientException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

/**
 * @author star
 * @date 2018/8/3 下午12:03
 */
public class ZkWatcher implements Watcher {

    private static final Logger logger = LoggerFactory.getLogger(ZkWatcher.class);

    private WatcherProcess process;
    private ZkClient zkClient;
    private Semaphore connLock;

    public ZkWatcher(Semaphore connLock, ZkClient zkClient) {
        this.connLock = connLock;
        this.zkClient = zkClient;
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getState()) {
            case ConnectedReadOnly:
            case SyncConnected:
                if (!zkClient.isConnection()) {
                    zkClient.setConnection(true);
                    //连接成功
                    connLock.release();
                    logger.warn("Zookeeper connection or retry success......");
                }
                break;
            //会话超时
            case Expired:
                this.stateChange(event.getState());
                resetSession();
                break;
            //连接断开
            case Disconnected:
                zkClient.setConnection(false);
                this.stateChange(event.getState());
                logger.warn("Zookeeper connection break......");
                break;
            default:
                logger.warn("Zookeeper state: " + event.getState());
                break;
        }
        switch (event.getType()) {
            //子节点变化
            case NodeChildrenChanged:
                this.childChange(event.getPath());
                break;
            //节点数据变化
            case NodeDataChanged:
                this.dataChange(event.getPath());
            default:
                break;
        }

    }

    /**
     * 数据变化处理
     *
     * @param path
     */
    private void dataChange(String path) {
        try {
            process.dataChange(path);
        } catch (ZkClientException e) {
            logger.error("Data change watcher exception.", e);
        }
    }


    /**
     * 重置会话信息
     */
    private void resetSession() {
        logger.warn("Zookeeper session timeout......");
        try {
            zkClient.reconnection();
            logger.warn("Zookeeper session timeout,retry success. ");
        } catch (ZkClientException e) {
            logger.error("Zookeeper reset session fail.", e);
        }
    }


    /**
     * 子节点发生变化
     *
     * @param path
     */
    private void childChange(String path) {
        try {
            process.childChange(path, false);
        } catch (ZkClientException e) {
            logger.error("Child change watcher exception.", e);
        }
    }

    /**
     * 状态变化监听
     *
     * @param state
     */
    private void stateChange(Event.KeeperState state) {
        process.listen(state);
    }

    /**
     * 添加zookeeper事件处理类
     *
     * @param process
     */
    public void setWatcherProcess(WatcherProcess process) {
        this.process = process;
    }
}
