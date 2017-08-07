package com.star.serialize.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.serialize.Serialize;

import java.io.IOException;

/**
 * Created by star on 2017/8/7.
 */
public class JacksonSerialize implements Serialize {
    private final static ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public <T> byte[] serialize(T obj) {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }


    @Override
    public <T> Object deserialize(byte[] bytes, Class<T> clazz) {
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (JsonParseException e) {
            throw new IllegalStateException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

}
