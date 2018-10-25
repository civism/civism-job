package com.civism.job;

import java.lang.annotation.*;

/**
 * @author star
 * @date 2018/1/29 上午11:46
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface GuavaJob {
    String value();
}
