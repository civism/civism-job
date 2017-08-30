package com.star.core;

import com.alibaba.fastjson.JSON;
import com.star.anno.GdxRPC;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by star on 2017/8/18.
 */
@Service
public class RegisterGdxRPC extends ApplicationObjectSupport {

    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        super.initApplicationContext(context);
//        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(GdxRPC.class);
//        beansWithAnnotation.forEach((k, v) -> {
//            Class<?> clazz = v.getClass();
//            GdxRPC annotation = clazz.getAnnotation(GdxRPC.class);
//            System.out.println(annotation);
//            annotation.ip();
//        });
    }
}
