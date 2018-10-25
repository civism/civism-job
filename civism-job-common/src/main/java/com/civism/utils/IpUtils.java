package com.civism.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * @author star
 * @date 2018/10/25 上午11:34
 */
public class IpUtils {

    private static Pattern pattern = Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");


    public static String getIpAddress() {
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    final String host = ips.nextElement().getHostAddress();
                    if (!host.matches("^127\\..*")
                            && host.matches("([0-9]{0,3}\\.){3}[0-9]{0,3}")) {
                        return host;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("getIp constants", e);
        }
        return null;
    }
}
