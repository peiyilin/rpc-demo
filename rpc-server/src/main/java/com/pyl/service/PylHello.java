package com.pyl.service;

import com.pyl.api.service.IPylHello;
import org.springframework.stereotype.Service;

/**
 * @Author: Administrator
 * @Date: 2018/7/1/001 18:24
 * @Description:
 */
@Service
public class PylHello implements IPylHello {
    @Override
    public String sayHello(String msg) {
        return "hello "+ msg;
    }
}
