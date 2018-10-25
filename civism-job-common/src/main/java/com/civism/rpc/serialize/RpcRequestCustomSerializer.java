package com.civism.rpc.serialize;

import com.alibaba.fastjson.JSON;
import com.alipay.remoting.CustomSerializer;
import com.alipay.remoting.DefaultCustomSerializer;
import com.alipay.remoting.InvokeContext;
import com.alipay.remoting.exception.DeserializationException;
import com.alipay.remoting.exception.SerializationException;
import com.alipay.remoting.rpc.RequestCommand;
import com.alipay.remoting.rpc.protocol.RpcRequestCommand;
import com.civism.rpc.RpcRequest;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author star
 * @date 2018/8/9 上午10:58
 */
public class RpcRequestCustomSerializer extends DefaultCustomSerializer {

    private AtomicBoolean serialFlag = new AtomicBoolean();
    private AtomicBoolean deserialFlag = new AtomicBoolean();

    private byte contentSerializer = -1;
    private byte contentDeserializer = -1;

    /**
     * @see CustomSerializer#serializeContent(RequestCommand, InvokeContext)
     */
    @Override
    public <T extends RequestCommand> boolean serializeContent(T req, InvokeContext invokeContext)
            throws SerializationException {
        serialFlag.set(true);
        RpcRequestCommand rpcReq = (RpcRequestCommand) req;
        RpcRequest request = (RpcRequest) rpcReq.getRequestObject();
        byte[] msg;
        try {
            msg = JSON.toJSONString(request).getBytes("UTF-8");
            ByteBuffer bb = ByteBuffer.allocate(4 + msg.length);
            bb.put(msg);
            rpcReq.setContent(bb.array());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        contentSerializer = rpcReq.getSerializer();
        return true;
    }

    /**
     * @see CustomSerializer#deserializeContent(RequestCommand)
     */
    @Override
    public <T extends RequestCommand> boolean deserializeContent(T req)
            throws DeserializationException {
        deserialFlag.set(true);
        RpcRequestCommand rpcReq = (RpcRequestCommand) req;
        byte[] content = rpcReq.getContent();
        ByteBuffer bb = ByteBuffer.wrap(content);
        byte[] dst = new byte[content.length - 4];
        bb.get(dst, 0, dst.length);
        try {
            String b = new String(dst, "UTF-8");
            RpcRequest bd = JSON.parseObject(b, RpcRequest.class);
            rpcReq.setRequestObject(bd);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        contentDeserializer = rpcReq.getSerializer();
        return true;
    }

    public boolean isSerialized() {
        return this.serialFlag.get();
    }

    public boolean isDeserialized() {
        return this.deserialFlag.get();
    }

    public byte getContentSerializer() {
        return contentSerializer;
    }

    public byte getContentDeserializer() {
        return contentDeserializer;
    }

    public void reset() {
        this.contentDeserializer = -1;
        this.contentSerializer = -1;
        this.deserialFlag.set(false);
        this.serialFlag.set(false);
    }
}
