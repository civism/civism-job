package com.civism.rpc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author star
 * @date 2018/8/13 上午11:47
 */
public class SpringBeanMap {

    private static ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap();


    public static void put(String key, Object value) {
        beanMap.putIfAbsent(key, value);
    }

    public static Object get(String key) {
        return beanMap.get(key);
    }
}
