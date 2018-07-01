package com.pyl;

import com.pyl.api.service.IPylHello;
import com.pyl.server.RpcProxyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Auther: Administrator
 * @Date: 2018/6/30/030 23:19
 * @Description:
 */
@SpringBootApplication
public class RpcServerApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(RpcServerApplication.class, args);
        RpcProxyServer rpcProxyServer = context.getBean(RpcProxyServer.class);
        IPylHello pylHello = context.getBean(IPylHello.class);
        rpcProxyServer.publisher(pylHello);
    }
}
