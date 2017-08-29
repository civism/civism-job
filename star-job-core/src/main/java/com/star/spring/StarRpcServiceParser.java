package com.star.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * Created by star on 2017/8/23.
 */
public class StarRpcServiceParser extends AbstractSingleBeanDefinitionParser {


    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String interfaceName = element.getAttribute("interfaceName");
        String ref = element.getAttribute("ref");
        builder.addPropertyValue("interfaceName",interfaceName);
        builder.addPropertyValue("ref",ref);
    }
}
