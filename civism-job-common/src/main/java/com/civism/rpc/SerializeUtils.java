package com.civism.rpc;

import com.alipay.remoting.CustomSerializerManager;
import com.civism.rpc.serialize.RpcRequestCustomSerializer;
import com.civism.rpc.serialize.StringCustomSerializer;


/**
 * @author star
 * @date 2018/8/10 下午2:37
 */
public class SerializeUtils {

    public static void init() {
        RpcRequestCustomSerializer rpcRequestCustomSerializer = new RpcRequestCustomSerializer();
        StringCustomSerializer s2 = new StringCustomSerializer();
        CustomSerializerManager.registerCustomSerializer(RpcRequest.class.getName(), rpcRequestCustomSerializer);
        CustomSerializerManager.registerCustomSerializer(String.class.getName(), s2);
    }
}
