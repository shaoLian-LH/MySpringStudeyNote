package com.dao;

import com.domain.Account;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();

    /**
     * 查询一个
     * @param id
     * @return
     */

    Account findAccountById(Integer id);

    /**
     * 保存用户
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新用户资料
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除用户资料
     * @param id
     */
    void deleteAccount(Integer id);
}
