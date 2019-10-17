package test;

import com.domain.Account;
import com.service.IAccountServie;
import config.SpringConfiguration;
import config.jdbcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试，测试配置
 * Spring整合junit配置
 * 1. 导入spring整合jniut的jar包（坐标）
 * 2. 使用Junit提供的一个注解把原有的main方法替换，替换成spring提供的main
 *    @RunWith
 * 3. 告知Spring的运行器，spring和ioc创建是基于xml还是注解，并且说明位置
 *    @ContextConfiguration
 *    location：指定xml文件位置，加上classpath表示在类路径下
 *    classes：指定注解类所在的位置
 * 当我们使用spring 5.x版本时，要求Junit的jar包必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
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
