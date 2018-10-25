package com.civism.main;


import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author star
 * @date 2017/12/1 上午11:35
 */
public class Main {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
        context.start();
        System.out.println("spring加载成功");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
