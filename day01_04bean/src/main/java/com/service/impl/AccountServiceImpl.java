package com.service.impl;

import com.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    public AccountServiceImpl(){
        System.out.println("AccountServiceImpl对象创建了");
    }
    public void saveAccount() {
        System.out.println("service中的saveAccount执行了");
    }
    public void init() {
        System.out.println("service对象被创建了");
    }
    public void destroy() {
        System.out.println("service对象被销毁了");
    }
}
