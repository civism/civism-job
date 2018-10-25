package com.civism.rpc.processor;

import com.alipay.remoting.Connection;
import com.alipay.remoting.ConnectionEventProcessor;
import com.civism.utils.IpLoadRouteUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author star
 * @date 2018/8/2 下午5:15
 */
public class DisconnectEventProcessor implements ConnectionEventProcessor {

    private AtomicBoolean dicConnected = new AtomicBoolean();
    private AtomicInteger disConnectTimes = new AtomicInteger();

    @Override
    public void onEvent(String remoteAddr, Connection conn) {
        System.out.println("disconnect addr:" + remoteAddr);
        Set<Map.Entry<String, LinkedBlockingQueue<String>>> mapEntrySet = IpLoadRouteUtils.getMapEntry();
        for (Map.Entry<String, LinkedBlockingQueue<String>> mapEntry : mapEntrySet) {
            LinkedBlockingQueue<String> linkedBlockingQueue = mapEntry.getValue();
            if (CollectionUtils.isNotEmpty(linkedBlockingQueue)) {
                if (linkedBlockingQueue.contains(remoteAddr)) {
                    linkedBlockingQueue.remove(remoteAddr);
                    System.out.println("从缓存移除处： " + remoteAddr);
                    continue;
                }
            }
        }
        System.out.println("断开链接了" + remoteAddr);
        dicConnected.set(true);
        disConnectTimes.incrementAndGet();
    }

    public boolean isDisConnected() {
        return this.dicConnected.get();
    }

    public int getDisConnectTimes() {
        return this.disConnectTimes.get();
    }

    public void reset() {
        this.disConnectTimes.set(0);
        this.dicConnected.set(false);
    }
}
