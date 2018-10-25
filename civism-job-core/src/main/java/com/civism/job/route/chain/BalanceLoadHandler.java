package com.civism.job.route.chain;


import com.civism.constants.CivismConstants;
import com.civism.job.route.CivismJob;
import com.civism.job.route.GuavaJobHandler;
import com.civism.job.route.Handler;
import com.civism.utils.IpLoadRouteUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author star
 * @date 2018/8/8 下午2:58
 * 负载均衡的执行方式
 */
@Service
public class BalanceLoadHandler implements Handler {


    @Override
    public void doHandler(CivismJob civismJob, GuavaJobHandler handler) {
        //自动发现
        if (civismJob.getJobType().intValue() == 0) {
            String ipAddress = IpLoadRouteUtils.get(civismJob.getBeanName());
            if (StringUtils.isNotEmpty(ipAddress)) {
                civismJob.setExecuteIp(ipAddress);
            }
        } else if (civismJob.getJobType().intValue() == 1) {
            //指定主机的IP列表
            String s = IpLoadRouteUtils.get(CivismConstants.ZK_GUAVA + civismJob.getBeanName());
            if (StringUtils.isNotEmpty(s)) {
                civismJob.setExecuteIp(s);
            }
        }
        handler.doHandler(civismJob, handler);
    }
}
