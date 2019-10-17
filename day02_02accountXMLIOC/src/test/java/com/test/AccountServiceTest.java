package com.test;

import com.domain.Account;
import com.service.IAccountServie;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

/**
 * 使用Junit单元测试，测试配置
 */
public class AccountServiceTest {
    @Test
    public void testFindAll(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountServie accountServie = (IAccountServie) ac.getBean("accountService");
        List<Account> accounts = accountServie.findAllAccount();
        System.out.println(accounts);
    }
}
