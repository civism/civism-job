package com.civism.zookeeper.listener;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author star
 * @date 2018/8/3 下午2:09
 */
public class ThreadProcessFactory implements ThreadFactory {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        thread.setName("zkClient-listener-process-" + count.incrementAndGet());
        return thread;
    }
}
