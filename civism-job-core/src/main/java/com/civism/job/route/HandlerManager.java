package com.civism.job.route;


import com.civism.job.route.chain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author star
 * @date 2018/8/8 上午10:52
 */
@Service
public class HandlerManager {

    /**
     * 自动发现IP责任链
     */
    @Resource
    private JobTypeHandler jobTypeHandler;

    /**
     * 随机选择一台主机执行策略
     */
    @Resource
    private RandomLoadHandler randomLoadBalanceHandler;

    /**
     * 指定执行的主机IP策略
     */
    @Resource
    private DesignateIpLoadHandler designateIpLoadBalanceHandler;

    /**
     * 负载均衡策略
     */
    @Resource
    private BalanceLoadHandler balanceLoadHandler;


    /**
     * 执行任务责任链，责任链末端
     */
    @Resource
    private ExecuteJobHandler executeJobHandler;


    public void handler(CivismJob ruhnnJob) {
        GuavaJobHandler guavaJobHandler = new GuavaJobHandler();
        // 1固定IP
        if (ruhnnJob.getJobType().intValue() == 1) {
            guavaJobHandler.addHander(jobTypeHandler);
        }

        if (ruhnnJob.getLoadWay() == null) {
            ruhnnJob.setLoadWay(0);
        }
        switch (ruhnnJob.getLoadWay()) {
            case 0:
                guavaJobHandler.addHander(randomLoadBalanceHandler);
                break;
            case 1:
                guavaJobHandler.addHander(designateIpLoadBalanceHandler);
                break;
            case 2:
                guavaJobHandler.addHander(balanceLoadHandler);
                break;
            default:
                guavaJobHandler.addHander(randomLoadBalanceHandler);
                break;
        }
        //末端策略
        guavaJobHandler.addHander(executeJobHandler);
        guavaJobHandler.doHandler(ruhnnJob, guavaJobHandler);
    }
}
