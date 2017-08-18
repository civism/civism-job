package com.star.core;

import com.alibaba.fastjson.JSON;
import com.star.annotation.GdxRPC;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

import java.util.Map;

/**
 * Created by star on 2017/8/18.
 */
public class RegisterGdxRPC extends ApplicationObjectSupport {

    @Override
    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        super.initApplicationContext(context);
        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(GdxRPC.class);
        System.out.println(JSON.toJSONString(beansWithAnnotation));
    }
}
