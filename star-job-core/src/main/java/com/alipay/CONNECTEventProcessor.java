package com.alipay;

import com.alipay.remoting.Connection;
import com.alipay.remoting.ConnectionEventProcessor;
import org.junit.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author star
 * @date 2018/5/14 下午2:46
 */
public class CONNECTEventProcessor implements ConnectionEventProcessor {

    private AtomicBoolean connected = new AtomicBoolean();
    private AtomicInteger connectTimes = new AtomicInteger();
    private Connection connection;
    private String remoteAddr;
    private CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void onEvent(String remoteAddr, Connection conn) {
        Assert.assertNotNull(remoteAddr);
        doCheckConnection(conn);
        this.remoteAddr = remoteAddr;
        this.connection = conn;
        connected.set(true);
        connectTimes.incrementAndGet();
        latch.countDown();
    }

    /**
     * do check connection
     *
     * @param conn
     */
    private void doCheckConnection(Connection conn) {
        Assert.assertNotNull(conn);
        Assert.assertNotNull(conn.getPoolKeys());
        Assert.assertTrue(conn.getPoolKeys().size() > 0);
        Assert.assertNotNull(conn.getChannel());
        Assert.assertNotNull(conn.getUrl());
        Assert.assertNotNull(conn.getChannel().attr(Connection.CONNECTION).get());
    }

    public boolean isConnected() throws InterruptedException {
        latch.await();
        return this.connected.get();
    }

    public int getConnectTimes() throws InterruptedException {
        latch.await();
        return this.connectTimes.get();
    }

    public Connection getConnection() throws InterruptedException {
        latch.await();
        return this.connection;
    }

    public String getRemoteAddr() throws InterruptedException {
        latch.await();
        return this.remoteAddr;
    }

    public void reset() {
        this.connectTimes.set(0);
        this.connected.set(false);
        this.connection = null;
    }
}
