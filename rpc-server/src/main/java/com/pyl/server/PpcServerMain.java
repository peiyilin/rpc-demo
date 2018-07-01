package com.pyl.server;

import com.pyl.api.service.IPylHello;
import com.pyl.service.PylHello;

/**
 * @Author: Administrator
 * @Date: 2018/7/1/001 00:04
 * @Description:
 */
public class PpcServerMain {
    public static void main(String[] args) {
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        IPylHello iPylHello = new PylHello();
        rpcProxyServer.publisher(iPylHello);
    }
}
