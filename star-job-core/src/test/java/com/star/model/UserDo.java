package com.star.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by star on 2017/8/7.
 */
public class UserDo implements Serializable {

    private static final long serialVersionUID = 1899427754241961L;

    private Integer id;
    private String name;

    private Date gmtCreate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
