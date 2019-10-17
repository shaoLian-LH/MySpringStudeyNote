package com.factory;

import com.service.IAccountService;
import com.service.impl.AccountServiceImpl;

/**
 * 模拟一个工厂类（存在于jar包中），无法通过修改源码的方式提供默认构造函数
 */
public class StaticFactory {

    public static IAccountService getAccountService(){
        return new AccountServiceImpl();

    }
}
