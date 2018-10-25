package com.civism.job.route.chain;


import com.civism.constants.CivismConstants;
import com.civism.job.route.CivismJob;
import com.civism.job.route.GuavaJobHandler;
import com.civism.job.route.Handler;
import com.civism.utils.IpLoadRouteUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;


/**
 * @author star
 * @date 2018/8/7 下午4:02
 * ip 是固定还是自动发现
 * <p>
 * <p>
 * 因为固定也可以指定多个IP，所以固定和自动都会走负载均衡责任链
 * <p>
 * 固定IP也必须是在zookeeper节点里面的 因为zookeeper维护了是否能通信
 * <p>
 * 0自动发现
 * 1固定IP
 */
@Service
public class JobTypeHandler implements Handler {

    private static final Logger logger = LoggerFactory.getLogger(JobTypeHandler.class);

    @Override
    public void doHandler(CivismJob civismJob, GuavaJobHandler handler) {

        if (CollectionUtils.isNotEmpty(civismJob.getTargetIps())) {
            //因为IPLoadRouteUtils对象里面会有所有zookeeper注册的节点，如果是固定IP，不能走zookeeper节点
            //zk里面的节点是以com.ruhnn.xxx为key，这儿选择用/comb_job/com.ruhnn.xxx为key，能区分是走zk节点还是固定IP节点

            Set<String> designValues = IpLoadRouteUtils.getValues(CivismConstants.ZK_GUAVA + civismJob.getBeanName());
            //说明zk里面没有该固定的Ip信息，不能联通
            //集合相等不用管
            if (CollectionUtils.isEmpty(designValues) || !CollectionUtils.isEqualCollection(civismJob.getTargetIps(), designValues)) {
                Set<String> values = IpLoadRouteUtils.getValues(civismJob.getBeanName());
                logger.warn("zookeeper中通信节点={}与指定IP{}不一致", values, designValues);
                if (CollectionUtils.isNotEmpty(values)) {
                    IpLoadRouteUtils.removeAll(CivismConstants.ZK_GUAVA + civismJob.getBeanName());
                    for (String ip : civismJob.getTargetIps()) {
                        if (values.contains(ip)) {
                            IpLoadRouteUtils.put(CivismConstants.ZK_GUAVA + civismJob.getBeanName(), ip);
                        }
                    }
                }
            }
        }
        handler.doHandler(civismJob, handler);
    }


}
