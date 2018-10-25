package com.civism.job.observer;

import com.civism.job.constants.JobRecordStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author star
 * @date 2018/10/23 下午3:31
 */
@Service
public class MessageSendObserver implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(MessageSendObserver.class);

//    private ListeningExecutorService executorService = MoreExecutors.listeningDecorator(new ThreadPoolExecutor(1, 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>()));

    @Resource
    private GuavaJobObserverManage ruhnnJobObserverManage;

    @Override
    public void update(Observer o, JobRecordStatus status) {
        logger.info("调用结果：" + status.getDesc());
        switch (status) {
            case RECORD_LOADING:
                break;
            case RECORD_SUCCESS:
                break;
            case RECORD_FAIL:
                break;
            case RECORD_NO_CHANEL:
                break;
            default:
                break;
        }
        ruhnnJobObserverManage.removeObserver(o);
    }
}
