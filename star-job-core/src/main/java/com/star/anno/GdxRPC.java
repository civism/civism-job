package com.star.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by star on 2017/8/18.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface GdxRPC {

    String server() default "netty";

    String ip() default "127.0.0.1";

    int port() default 8888;
}
