package com.factory;

import com.domain.Account;
import com.service.IAccountServie;
import com.utils.TransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 用于创建Service代理对象的bean的工厂
 */

public class BeanFactory {
    /* 工厂版本 */
    private IAccountServie accountServie;
    private TransactionManager tsManager;

    public void setTsManager(TransactionManager tsManager) {
        this.tsManager = tsManager;
    }
    public final void setAccountServie(IAccountServie accountServie) {
        this.accountServie = accountServie;
    }


    public IAccountServie getAccountService(){
        return (IAccountServie) Proxy.newProxyInstance(accountServie.getClass().getClassLoader(),
                accountServie.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtVaule = null;
                        try{
                            //1. 开启事务
                            tsManager.beginTransaction();
                            //2. 执行操作
                            rtVaule = method.invoke(accountServie,args);
                            //3. 提交事务
                            tsManager.commit();
                            //4. 返回结果
                            return rtVaule;
                        }catch (Exception e){
                            //5. 回滚操作
                            tsManager.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            //6. 释放连接
                            tsManager.release();
                        }
                    }
                });
    }
}
