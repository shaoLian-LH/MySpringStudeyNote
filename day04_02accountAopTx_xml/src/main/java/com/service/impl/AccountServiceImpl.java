package com.service.impl;

import com.dao.IAccountDao;
import com.domain.Account;
import com.service.IAccountServie;
import com.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * 事务控制应该放在业务层
 */
public class AccountServiceImpl implements IAccountServie {

    @Autowired
    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer id) {
       return accountDao.findAccountById(id);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountDao.deleteAccount(id);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println("transfer....");
        //2.1. 根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2.2. 根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        //2.3. 转出账户金额检查与扣款
        source.setMoney(source.getMoney() - money);
        //2.4. 转入账户添款
//        int i = 1/0;
        target.setMoney(target.getMoney() + money);
        //2.5. 更新两个账户
        accountDao.updateAccount(source);

        accountDao.updateAccount(target);
    }
}
