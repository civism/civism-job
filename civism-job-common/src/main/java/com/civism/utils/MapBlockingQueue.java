package com.civism.utils;


import org.apache.commons.collections.CollectionUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author star
 * @date 2018/8/8 下午3:13
 * 满足先进先出 实现负载均衡
 */
public class MapBlockingQueue<K, V> {

    private  ConcurrentHashMap<K, LinkedBlockingQueue<V>> balanceLoadMap = new ConcurrentHashMap<>();

    public void put(K key, V value) {
        if (balanceLoadMap.get(key) == null) {
            LinkedBlockingQueue<V> blockingQueue = new LinkedBlockingQueue<>();
            blockingQueue.offer(value);
            balanceLoadMap.putIfAbsent(key, blockingQueue);
        } else {
            balanceLoadMap.get(key).offer(value);
        }
    }

    public V get(K key) {
        LinkedBlockingQueue<V> blockingQueue = balanceLoadMap.get(key);
        if (blockingQueue == null) {
            return null;
        }
        try {
            V poll = blockingQueue.poll(1, TimeUnit.SECONDS);

            this.put(key, poll);
            return poll;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int size(K key) {
        LinkedBlockingQueue<V> blockingQueue = balanceLoadMap.get(key);
        if (blockingQueue == null) {
            return 0;
        }
        return blockingQueue.size();
    }

    public Set<V> getValues(K key) {
        LinkedBlockingQueue<V> blockingQueue = balanceLoadMap.get(key);
        if (CollectionUtils.isEmpty(blockingQueue)) {
            return null;
        }
        return new HashSet<>(blockingQueue);
    }


    public void remove(K key, V value) {
        LinkedBlockingQueue<V> blockingQueue = balanceLoadMap.get(key);
        if (blockingQueue == null) {
            return;
        }
        blockingQueue.remove(value);
    }

    public void removeAll(K key){
        balanceLoadMap.remove(key);
    }


    public Set<Map.Entry<K, LinkedBlockingQueue<V>>> getMapEntry() {
        Set<Map.Entry<K, LinkedBlockingQueue<V>>> entries = balanceLoadMap.entrySet();
        return entries;
    }
}
