package com.star.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by star on 2017/8/23.
 */
public class StarRpcServiceParser implements BeanDefinitionParser {


    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String interfaceName = element.getAttribute("interfaceName");
        String ref = element.getAttribute("ref");
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(RpcService.class);
        beanDefinition.setLazyInit(false);
        beanDefinition.getPropertyValues().addPropertyValue("interfaceName",interfaceName);
        beanDefinition.getPropertyValues().addPropertyValue("ref",ref);
        parserContext.getRegistry().registerBeanDefinition(interfaceName,beanDefinition);
        return beanDefinition;
    }
}
