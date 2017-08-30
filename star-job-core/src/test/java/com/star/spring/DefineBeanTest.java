package com.star.spring;

import com.star.service.HelloWorldService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by star on 2017/8/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-fristar-test.xml"})
public class DefineBeanTest {

    @Autowired
    private HelloWorldService helloWorldService;


    @Test
    public void testA(){
        System.out.println(helloWorldService);

    }

}
