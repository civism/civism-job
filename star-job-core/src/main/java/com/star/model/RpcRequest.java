package com.star.model;

import java.io.Serializable;

/**
 * Created by star on 2017/8/31.
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
