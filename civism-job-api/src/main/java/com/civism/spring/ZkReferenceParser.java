package com.civism.spring;

import com.civism.job.ZkAddress;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by star on 2017/8/23.
 */
public class ZkReferenceParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String address = element.getAttribute("address");
        String nettyPort = element.getAttribute("port");


        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(ZkAddress.class);
        beanDefinition.setLazyInit(false);
        beanDefinition.getPropertyValues().addPropertyValue("address", address);
        beanDefinition.getPropertyValues().addPropertyValue("port", StringUtils.isEmpty(nettyPort) ? "3000" : nettyPort);
        parserContext.getRegistry().registerBeanDefinition("zkAddress", beanDefinition);
        return beanDefinition;
    }

}
