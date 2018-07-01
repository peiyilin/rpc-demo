package com.pyl.client;

import com.pyl.api.module.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author: Administrator
 * @Date: 2018/7/1/001 00:24
 * @Description:消息传输类
 */
public class RpcNetTransport {
    /**
     * 服务的地址
     */
    String host;
    /**
     * 服务的端口号
     */
    int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 创建socket连接
     * @return socket对象
     */
    private Socket createSocket(){
        System.out.println("create socket connect begin");
        Socket socket = null;
        try {
            socket = new Socket(host,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    /**
     * 消息传输
     * @param rpcRequest 传输对象
     * @return 返回的结果对象
     */
    public Object send(RpcRequest rpcRequest) {
        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream inputStream = null;
        try {
            socket = createSocket();
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //序列化
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();
            System.out.println("server返回："+ result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    System.out.println(socket.getLocalSocketAddress()+" socket connect关闭");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
