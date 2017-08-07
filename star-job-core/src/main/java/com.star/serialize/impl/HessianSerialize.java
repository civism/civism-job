package com.star.serialize.impl;


import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.star.serialize.Serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by star on 2017/8/7.
 */
public class HessianSerialize implements Serialize {

	@Override
	public <T> byte[] serialize(T obj){
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HessianOutput ho = new HessianOutput(os);
		try {
			ho.writeObject(obj);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
		return os.toByteArray();
	}

	@Override
	public <T> Object deserialize(byte[] bytes, Class<T> clazz) {
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		HessianInput hi = new HessianInput(is);
		try {
			return hi.readObject();
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

}
