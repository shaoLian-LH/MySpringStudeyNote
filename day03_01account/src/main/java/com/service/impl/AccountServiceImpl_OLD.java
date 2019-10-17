package com.service.impl;

import com.dao.IAccountDao;
import com.domain.Account;
import com.service.IAccountServie;
import com.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * 事务控制应该放在业务层
 */
public class AccountServiceImpl_OLD implements IAccountServie {

    private IAccountDao accountDao;
    private TransactionManager tsManager;

    public void setTsManager(TransactionManager tsManager) {
        this.tsManager = tsManager;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        try{
            //1. 开启事务
            tsManager.beginTransaction();
            //2. 执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //3. 提交事务
            tsManager.commit();
            //4. 返回结果
            return accounts;
        }catch (Exception e){
            //5. 回滚操作
            tsManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6. 释放连接
            tsManager.release();
        }
    }

    @Override
    public Account findAccountById(Integer id) {
        try{
            //1. 开启事务
            tsManager.beginTransaction();
            //2. 执行操作
            Account account = accountDao.findAccountById(id);
            //3. 提交事务
            tsManager.commit();
            //4. 返回结果
            return account;
        }catch (Exception e){
            //5. 回滚操作
            tsManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6. 释放连接
            tsManager.release();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try{
            //1. 开启事务
            tsManager.beginTransaction();
            //2. 执行操作
            accountDao.saveAccount(account);
            //3. 提交事务
            tsManager.commit();
        }catch (Exception e){
            //4. 回滚操作
            tsManager.rollback();
        }finally {
            //5. 释放连接
            tsManager.release();
        }

    }

    @Override
    public void updateAccount(Account account) {
        try{
            //1. 开启事务
            tsManager.beginTransaction();
            //2. 执行操作
            accountDao.updateAccount(account);
            //3. 提交事务
            tsManager.commit();
        }catch (Exception e){
            //4. 回滚操作
            tsManager.rollback();
        }finally {
            //5. 释放连接
            tsManager.release();
        }

    }

    @Override
    public void deleteAccount(Integer id) {
        try{
            //1. 开启事务
            tsManager.beginTransaction();
            //2. 执行操作
            accountDao.deleteAccount(id);
            //3. 提交事务
            tsManager.commit();
        }catch (Exception e){
            //4. 回滚操作
            tsManager.rollback();
        }finally {
            //5. 释放连接
            tsManager.release();
        }

    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        //需要使用ThreadLocal对象把Connection和当前线程绑定，从而使一个线程中只有一个控制事务的对象
        try{
            //1. 开启事务
            tsManager.beginTransaction();
            //2. 执行操作
                //2.1. 根据名称查询转出账户
                Account source = accountDao.findAccountByName(sourceName);
                //2.2. 根据名称查询转入账户
                Account target = accountDao.findAccountByName(targetName);
                //2.3. 转出账户金额检查与扣款
                source.setMoney(source.getMoney() - money);
                //2.4. 转入账户添款
                target.setMoney(target.getMoney() + money);
                //2.5. 更新两个账户
                accountDao.updateAccount(source);
                int i = 1/0;
                accountDao.updateAccount(target);
            //3. 提交事务
            tsManager.commit();
        }catch (Exception e){
            //4. 回滚操作
            tsManager.rollback();
            e.printStackTrace();
        }finally {
            //5. 释放连接
            tsManager.release();
        }
    }
}
