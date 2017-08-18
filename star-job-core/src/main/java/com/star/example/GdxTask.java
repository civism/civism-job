package com.star.example;

import com.star.anno.GdxRPC;
import org.springframework.stereotype.Service;

/**
 * Created by star on 2017/8/18.
 */
@GdxRPC(server = "netty",ip = "127.0.0.1",port = 9999)
@Service("gdxTask")
public class GdxTask extends BaseTask  {

    public void say() {
        System.out.println("1111");
    }


}
