package test;

import com.domain.Account;
import com.service.IAccountServie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试，测试配置
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:bean.xml"})
public class AccountServiceTest {

    @Autowired
    private IAccountServie accountServie;

    @Test
    public void testFindAll(){
        List<Account> accounts = accountServie.findAllAccount();
        System.out.println(accounts);
    }

    @Test
    public void testFindOne(){
        Account account = accountServie.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSaveAccount(){
        Account account = new Account();
        account.setName("邵莲");
        account.setMoney(new Float(268495));
        accountServie.saveAccount(account);
    }

    @Test
    public void testUpdate(){
        Account account = new Account();
        account.setId(4);
        account.setName("郑骘");
        account.setMoney(new Float(268495));
        accountServie.updateAccount(account);
    }

    @Test
    public void testDelete(){
        Account account = new Account();
        accountServie.deleteAccount(4);
    }
}
