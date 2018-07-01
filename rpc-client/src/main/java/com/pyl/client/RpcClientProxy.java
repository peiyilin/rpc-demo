package com.pyl.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @Author: Administrator
 * @Date: 2018/7/1/001 00:16
 * @Description:代理类（将调用透明化）
 */
@Component
public class RpcClientProxy {

    @Value("${rpc.host}")
    private String host;

    @Value("${rpc.port}")
    private int port;

    public <T> T clientProxy(Class<T> interfaceCls){
        //jdk动态代理
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),new Class<?>[]{interfaceCls},
                new RemoteInvocationhandler(host,port));
    }
}
