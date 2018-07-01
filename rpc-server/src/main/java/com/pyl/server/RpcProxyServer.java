package com.pyl.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @Author: Administrator
 * @Date: 2018/6/30/030 23:34
 * @Description:
 */
@Component
public class RpcProxyServer {

    /**
     * 创建线程池
     */
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,Integer.MAX_VALUE,1, TimeUnit.MINUTES,new ArrayBlockingQueue<>(10));

    @Value("${rpc.port}")
    private int port;
    /**
     * 发布服务，socket服务和接口
     * @param service 接口对象
     */
    public void publisher(Object service){
        ServerSocket serverSocket = null;
        try {
            //启动一个socket server服务
            serverSocket = new ServerSocket(port);
            while (true){
                Socket accept = serverSocket.accept();
                System.out.println("client请求："+accept.getRemoteSocketAddress());
                threadPoolExecutor.execute(new ProcessorHandler(accept,service));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
