package com.dao.impl;

import com.dao.IAccountDao;
import org.springframework.stereotype.Controller;

@Controller
public class AccountImpl implements IAccountDao {




    public void saveAccount() {
        System.out.println("保存了用户");
    }
}
