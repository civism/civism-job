package com.star.serialize;

/**
 * Created by star on 2017/8/7.
 */
public interface Serialize {

    public <T> byte[] serialize(T obj);

    public <T> Object deserialize(byte[] bytes, Class<T> clazz);
}
