package com.pyl.controller;

import com.pyl.api.service.IPylHello;
import com.pyl.client.RpcClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Administrator
 * @Date: 2018/7/1/001 18:08
 * @Description:
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private RpcClientProxy rpcClientProxy;

    @RequestMapping("/test")
    public String test(String msg){
        IPylHello pylHello = rpcClientProxy.clientProxy(IPylHello.class);
        return pylHello.sayHello(msg);
    }
}
