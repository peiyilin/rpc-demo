package com.pyl.client;

import com.pyl.api.module.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: Administrator
 * @Date: 2018/7/1/001 00:21
 * @Description:消息处理类
 */
public class RemoteInvocationhandler implements InvocationHandler {
    /**
     * 发布的服务地址
     */
    String host;
    /**
     * 发布的服务端口号
     */
    int port;
    public RemoteInvocationhandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(proxy.getClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParams(args);
        RpcNetTransport transport = new RpcNetTransport(host,port);
        return transport.send(rpcRequest);
    }
}
