package com.civism;

import com.civism.zookeeper.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * @author star
 * @date 2018/10/25 下午4:09
 */
public class ZkInitTest {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        zkClient.create("/guava", CreateMode.PERSISTENT);
    }
}
