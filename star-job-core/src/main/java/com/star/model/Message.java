package com.star.model;


import java.io.Serializable;

/**
 * Created by star on 2017/8/15.
 */
public class Message implements Serializable {

  public static final long serialVersionUID = -539691155161871L;

    private String content;
    private Integer id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
