package com.star.main;

import com.alibaba.fastjson.JSON;
import com.star.model.UserDo;
import com.star.serialize.Serialize;
import com.star.serialize.impl.ProtostuffSerialize;

import java.util.Date;

/**
 * Created by star on 2017/8/7.
 */
public class Main {

    public static void main(String[] args) {
        UserDo userDo = new UserDo();
        userDo.setId(1);
        userDo.setName("gdx");
        userDo.setGmtCreate(new Date());
        Serialize serialize = new  ProtostuffSerialize();

        byte[] bytes = serialize.serialize(userDo);

        UserDo temp = (UserDo) serialize.deserialize(bytes, UserDo.class);
        System.out.println(JSON.toJSONString(temp));

    }

}
