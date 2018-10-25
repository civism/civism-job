package com.civism.job.listener;


import com.civism.utils.IpLoadRouteUtils;
import com.civism.zookeeper.ZkClientException;
import com.civism.zookeeper.listener.Listener;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.SocketException;

/**
 * @author star
 * @date 2018/8/9 下午3:56
 */
@Service
public class ZkChildIpListener implements Listener {

    private static final Logger logger = LoggerFactory.getLogger(ZkChildIpListener.class);

    @Override
    public void listen(String path, Watcher.Event.EventType eventType, byte[] data) throws ZkClientException, SocketException {
        String realPath = path.substring(path.lastIndexOf("/") + 1, path.length());
        String suffixPath = path.substring(10, path.lastIndexOf("/"));

        switch (eventType) {
            case NodeCreated:
                IpLoadRouteUtils.put(suffixPath, realPath);
                break;
            case NodeDeleted:
                IpLoadRouteUtils.remove(suffixPath, realPath);
                break;
            case NodeChildrenChanged:
                logger.warn("该状态没有处理，请排查");
                break;
            default:
                break;
        }
    }
}
