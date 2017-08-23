package com.star.main;


import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;

/**
 * Created by star on 2017/8/23.
 */
public class GuavaMain {
    public static void main(String[] args) {
        ClassToInstanceMap classToInstanceMap = MutableClassToInstanceMap.create();
        classToInstanceMap.put(Integer.class,1);
        classToInstanceMap.put(String.class,1);


    }

}
