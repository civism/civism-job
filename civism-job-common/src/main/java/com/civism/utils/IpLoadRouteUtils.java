package com.civism.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author star
 * @date 2018/8/8 下午5:06
 */
public class IpLoadRouteUtils {


    private static MapBlockingQueue<String, String> mapIpBlockingQueue = new MapBlockingQueue<>();

    public static void put(String key, Set<String> values) {
        if (CollectionUtils.isEmpty(values)) {
            return;
        }
        for (String value : values) {
            mapIpBlockingQueue.put(key, value);
        }
    }

    public static void put(String key, String value) {
        mapIpBlockingQueue.put(key, value);
    }


    public static String get(String key) {
        return mapIpBlockingQueue.get(key);
    }

    public static Set<String> getValues(String key) {
        return mapIpBlockingQueue.getValues(key);
    }


    public static void remove(String key, String value) {
        mapIpBlockingQueue.remove(key, value);
    }

    public static void removeAll(String key) {
        mapIpBlockingQueue.removeAll(key);
    }


    public static Set<Map.Entry<String, LinkedBlockingQueue<String>>> getMapEntry() {
        return mapIpBlockingQueue.getMapEntry();
    }

}
