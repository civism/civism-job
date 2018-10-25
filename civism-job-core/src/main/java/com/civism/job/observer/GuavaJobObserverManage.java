package com.civism.job.observer;

import com.civism.job.constants.JobRecordStatus;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author star
 * @date 2018/10/23 下午3:37
 */
@Service
public class GuavaJobObserverManage implements Observerable {

    private List<Observer> list;


    public GuavaJobObserverManage() {
        this.list = Collections.synchronizedList(new LinkedList<>());
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);

    }


    @Override
    public void removeObserver(Observer o) {
        if (CollectionUtils.isNotEmpty(list)) {
            Iterator<Observer> iterator = list.iterator();
            while (iterator.hasNext()) {
                Observer next = iterator.next();
                if (next.equals(o)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    @Override
    public void notifyObserver(JobRecordStatus status) {
        for (Observer observer : list) {
            observer.update(observer, status);
        }
    }

}
