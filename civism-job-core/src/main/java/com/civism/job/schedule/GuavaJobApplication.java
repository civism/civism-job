package com.civism.job.schedule;

import com.civism.job.route.HandlerManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author star
 * @date 2018/1/30 下午6:26
 */
public class GuavaJobApplication implements ApplicationContextAware {


    public static HandlerManager handlerManager;

    public static GuavaJobDealHandle ruhnnJobDealHandle;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        handlerManager = applicationContext.getBean(HandlerManager.class);
        ruhnnJobDealHandle = applicationContext.getBean(GuavaJobDealHandle.class);
    }

}
