package com.civism.job.listener;


import com.civism.constants.CivismConstants;
import com.civism.zookeeper.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author star
 * @date 2018/8/9 下午5:37
 */
@Service
public class ZkInit {

    private static final Logger logger = LoggerFactory.getLogger(ZkChildBeanNameListener.class);

    @Resource
    private ZkClient zkClient;

    @Resource
    private ZkChildBeanNameListener zkChildBeanNameListener;

    @PostConstruct
    public void init() {
        try {
            zkClient.listenChild(CivismConstants.ZK_GUAVA.substring(0, CivismConstants.ZK_GUAVA.length() - 1), zkChildBeanNameListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
