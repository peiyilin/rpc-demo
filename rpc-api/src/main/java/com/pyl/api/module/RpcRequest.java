package com.pyl.api.module;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @Author: Administrator
 * @Date: 2018/7/1/001 00:24
 * @Description:请求对象类
 */
public class RpcRequest implements Serializable{
    private static final long serialVersionUID = 7909728590044914943L;

    /**
     * 调用类名字
     */
    private String className;

    /**
     * 方法名字
     */
    private String methodName;

    /**
     * 参数数组
     */
    private Object[] params;

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
