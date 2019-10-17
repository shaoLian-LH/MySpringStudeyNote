package com.service.impl;

import com.dao.IAccountDao;
import com.dao.impl.AccountImpl;
import com.service.IAccountService;

public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao = new AccountImpl();
    public AccountServiceImpl(){
        System.out.println("AccountServiceImpl对象创建了");
    }
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
