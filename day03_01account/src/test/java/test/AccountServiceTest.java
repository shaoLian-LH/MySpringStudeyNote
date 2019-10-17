package test;

import com.domain.Account;
import com.service.IAccountServie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
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
    @Qualifier("proxyAccountService")
    private IAccountServie accountService;

    @Test
    public void testTransfer(){
        accountService.transfer("aaa","bbb",new Float(200));
    }
}
