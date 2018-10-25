package com.civism.spring;


import com.civism.constants.CivismConstants;
import com.civism.job.GuavaJob;
import com.civism.job.ZkAddress;
import com.civism.rpc.RpcUtils;
import com.civism.rpc.SpringBeanMap;
import com.civism.utils.IpUtils;
import com.civism.zookeeper.ZkClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author star
 * @date 2018/1/30 下午1:15
 */
public class GuavaZookeeperNodeInit implements ApplicationContextAware, InitializingBean {


    public static ApplicationContext context;


    private static ZkClient zkClient;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        GuavaZookeeperNodeInit.context = applicationContext;
    }

    private void zkNodeCreate(Integer port) throws Exception {
        Map<String, Object> beansWithAnnotations = context.getBeansWithAnnotation(GuavaJob.class);
        if (beansWithAnnotations != null && beansWithAnnotations.size() > 0) {
            for (String beanName : beansWithAnnotations.keySet()) {
                GuavaJob annotation = beansWithAnnotations.get(beanName).getClass().getAnnotation(GuavaJob.class);
                SpringBeanMap.put(annotation.value(), beansWithAnnotations.get(beanName));
                if (annotation != null) {
                    StringBuilder builder = new StringBuilder(CivismConstants.ZK_GUAVA);
                    builder.append(annotation.value());
                    builder.append("/");
                    builder.append(IpUtils.getIpAddress());
                    builder.append(":");
                    builder.append(port);
                    zkClient.createStubborn(builder.toString());
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ZkAddress zkAddress = context.getBean(ZkAddress.class);
        if (zkClient == null) {
            zkClient = new ZkClient(zkAddress.getAddress());
        }
        RpcUtils.getServerInstance(zkAddress.getPort());
        this.zkNodeCreate(zkAddress.getPort());
    }
}
