package com.star.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by star on 2017/8/7.
 */
public class MainTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/spring-fristar-test.xml");
        Object helloWorldService = context.getBean("helloWorldService");
        System.out.println(helloWorldService);
    }

}
