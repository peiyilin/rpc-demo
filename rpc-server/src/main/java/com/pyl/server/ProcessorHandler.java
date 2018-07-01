package com.pyl.server;

import com.pyl.api.module.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Author: Administrator
 * @Date: 2018/6/30/030 23:54
 * @Description:消息处理类
 */
public class ProcessorHandler implements Runnable{
    /**
     *  socket对象
     */
    Socket socket;
    /**
     * 接口对象
     */
    Object service;
    public ProcessorHandler(Socket socket,Object service) {
        this.service = service;
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("ProcessorHandler begin");
        ObjectInputStream inputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            try {
                //接收客户端传输的对象，反序列化
                RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();

                //反射调用方法
                Object result = invoke(rpcRequest);

                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(result);
                objectOutputStream.flush();
                System.out.println("返回给client："+result);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("ProcessorHandler end");
    }

    /**
     * 反射调用
     * @param rpcRequest 传输对象
     * @return
     */
    private Object invoke(RpcRequest rpcRequest){
        Object[] args = rpcRequest.getParams();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i <types.length ; i++) {
            types[i] = args[i].getClass();
        }
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), types);
            try {
                return method.invoke(service,args);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
