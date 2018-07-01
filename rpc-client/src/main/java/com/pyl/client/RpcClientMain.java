package com.pyl.client;

import com.pyl.api.service.IPylHello;

/**
 * @Author: Administrator
 * @Date: 2018/7/1/001 00:50
 * @Description:
 */
public class RpcClientMain {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        IPylHello pylHello = rpcClientProxy.clientProxy(IPylHello.class);
        String str = pylHello.sayHello("world");
        System.out.println(str);
    }
}
