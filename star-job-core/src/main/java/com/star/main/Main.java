package com.star.main;

import com.star.example.GdxTask;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by star on 2017/8/18.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/*.xml");

        synchronized (Main.class) {
            while (true) {
                try {
                    Main.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }

}
