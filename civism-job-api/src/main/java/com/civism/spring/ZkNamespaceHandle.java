package com.civism.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author star
 * @date 2018/1/29 上午10:45
 */
public class ZkNamespaceHandle extends NamespaceHandlerSupport {


    @Override
    public void init() {
        registerBeanDefinitionParser("zk", new ZkReferenceParser());
    }
}
