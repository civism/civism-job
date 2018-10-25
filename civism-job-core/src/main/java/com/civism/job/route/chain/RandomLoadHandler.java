package com.civism.job.route.chain;


import com.civism.job.route.CivismJob;
import com.civism.job.route.GuavaJobHandler;
import com.civism.job.route.Handler;
import com.civism.utils.IpLoadRouteUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author star
 * @date 2018/8/8 下午2:32
 * 随机负载均衡
 */
@Service
public class RandomLoadHandler implements Handler {

    @Override
    public void doHandler(CivismJob civismJob, GuavaJobHandler handler) {
        Set<String> targetIps = civismJob.getTargetIps();
        if (CollectionUtils.isEmpty(targetIps)) {
            targetIps = IpLoadRouteUtils.getValues(civismJob.getBeanName());
        }
        if (CollectionUtils.isNotEmpty(targetIps)) {
            List<String> ipList = new ArrayList<>(targetIps);
            int i = new Random().nextInt(targetIps.size());
            civismJob.setExecuteIp(ipList.get(i));
        }
        handler.doHandler(civismJob, handler);
    }
}
