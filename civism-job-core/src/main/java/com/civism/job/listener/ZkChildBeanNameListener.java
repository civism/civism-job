package com.civism.job.listener;


import com.civism.zookeeper.ZkClient;
import com.civism.zookeeper.ZkClientException;
import com.civism.zookeeper.listener.Listener;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.SocketException;

/**
 * @author star
 * @date 2018/8/6 下午5:45
 */
@Service
public class ZkChildBeanNameListener implements Listener {

    @Resource
    private ZkClient zkClient;

    @Resource
    private ZkChildIpListener zkChildIPListener;


    @Override
    public void listen(String path, Watcher.Event.EventType eventType, byte[] data) throws ZkClientException, SocketException {
        zkClient.listenChild(path, zkChildIPListener);
    }
}
