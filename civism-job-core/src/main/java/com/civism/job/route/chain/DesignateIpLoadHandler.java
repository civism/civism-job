package com.civism.job.route.chain;


import com.civism.job.route.CivismJob;
import com.civism.job.route.GuavaJobHandler;
import com.civism.job.route.Handler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author star
 * @date 2018/8/8 下午2:48
 * 指定IP执行，调用只会发生在该上面
 */
@Service
public class DesignateIpLoadHandler implements Handler {

    @Override
    public void doHandler(CivismJob civismJob, GuavaJobHandler handler) {
        if (StringUtils.isNoneBlank(civismJob.getLimitIp())) {
            civismJob.setExecuteIp(civismJob.getLimitIp());
        }
        handler.doHandler(civismJob, handler);
    }
}
