package com.civism.job.route;

import java.util.LinkedList;
import java.util.List;

/**
 * @author star
 * @date 2018/8/8 下午2:14
 */
public class GuavaJobHandler implements Handler {


    private List<Handler> handlers = new LinkedList<>();

    /**
     * 计数器
     */
    private int index = 0;

    public GuavaJobHandler addHander(Handler handler) {
        handlers.add(handler);
        return this;
    }

    @Override
    public void doHandler(CivismJob ruhnnJob, GuavaJobHandler handler) {
        if (index == handlers.size()) {
            return;
        }
        Handler baseHandler = handlers.get(index);
        index++;
        baseHandler.doHandler(ruhnnJob, handler);
    }

}
