package com.alipay.server;

import com.alipay.RequestBody;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.InvokeContext;
import com.alipay.remoting.rpc.protocol.SyncUserProcessor;
import org.junit.Assert;

/**
 * @author star
 * @date 2018/5/14 下午1:39
 */
public class SimpleServerUserProcessor extends SyncUserProcessor<RequestBody> {

    @Override
    public Object handleRequest(BizContext bizCtx, RequestBody request) throws Exception {
        System.out.println("Request received:" + request + ", timeout:" + bizCtx.getClientTimeout()
                + ", arriveTimestamp:" + bizCtx.getArriveTimestamp());

        if (bizCtx.isRequestTimeout()) {
            String errMsg = "Stop process in server biz thread, already timeout!";
            System.out.println(errMsg);
            throw new Exception(errMsg);
        }

        String remoteAddress = bizCtx.getRemoteAddress();
        Assert.assertNotNull(bizCtx.getConnection());
        Assert.assertTrue(bizCtx.getConnection().isFine());

        Long waittime = (Long) bizCtx.getInvokeContext().get(InvokeContext.BOLT_PROCESS_WAIT_TIME);
        Assert.assertNotNull(waittime);
        System.out.println("Server User processor process wait time " + waittime);

        System.out.println("Server User processor say, remote address is [" + remoteAddress + "].");
        return null;
    }

    @Override
    public String interest() {
        return RequestBody.class.getName();
    }

}
