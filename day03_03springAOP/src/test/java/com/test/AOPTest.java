package com.test;

import com.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {
    public static void main(String[] args) {
        //1. 获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:bean.xml");
        //2. 获取对象
        IAccountService AS = (IAccountService) ac.getBean("accountService");
        //3. 执行方法
        AS.saveAccount();
        AS.updateAccount(4);
        AS.deleteAccount();
    }
}
