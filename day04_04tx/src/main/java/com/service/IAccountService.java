package com.service;

import com.domain.Account;

/**
 * 账户的业务层接口
 */
public interface IAccountService {
    /**
     * 根据id查询账户信息
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 转账
     * @param sourceName 来源
     * @param targetName 目标
     * @param money 金额数目
     */
    void transfer(String sourceName,String targetName,Float money);
}
