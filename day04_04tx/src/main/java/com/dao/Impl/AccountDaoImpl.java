package com.dao.Impl;

import com.dao.IAccountDao;
import com.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 *  采用XML配置继承Support达到代码精简的效果
 */
public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao{

    @Override
    public Account findAccountById(Integer id) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where id = ?",new BeanPropertyRowMapper<Account>(Account.class),id);
        return (accounts.isEmpty()?null:accounts.get(0));
    }

    @Override
    public Account findAccountByName(String name) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where name = ?",new BeanPropertyRowMapper<Account>(Account.class),name);
        if(accounts.isEmpty()){
            return null;
        }
        if(accounts.size() > 1){
            throw new RuntimeException("结果集不为1");
        }
        return accounts.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        super.getJdbcTemplate().update("update account set name = ?,money = ? where id =?",account.getName(),account.getMoney(),account.getId());
    }

}
