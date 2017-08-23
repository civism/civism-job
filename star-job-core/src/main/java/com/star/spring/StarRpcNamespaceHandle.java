package com.star.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by star on 2017/8/23.
 */
public class StarRpcNamespaceHandle extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("",null);
    }
}
