package com.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 和事务管理相关的工具类，它包含了开启事务、提交事务、回滚事务和释放链接
 */
@Component("tsManager")
@Aspect
public class TransactionManager {
    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.service.impl.*.*(..))")
    private void pointCut1(){}
    /**
     * 开启事务
     */

    public void beginTransaction()  {
        try {
            connectionUtils.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 提交事务
     */

    public void commit(){
        try {
            connectionUtils.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 回滚事务
     */

    public void rollback(){
        try {
            connectionUtils.getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 释放连接，将连接还给连接池
     */

    public void release(){
        try {
            connectionUtils.getConnection().close();
            connectionUtils.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 环绕通知
     */
    @Around("pointCut1()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        Object rtValue;
        try {
            Object[] args = proceedingJoinPoint.getArgs();
            this.beginTransaction();
            rtValue = proceedingJoinPoint.proceed(args);
            this.commit();
            return rtValue;
        }catch (Throwable t){
            t.printStackTrace();
            this.rollback();
            throw new RuntimeException(t);
        }finally {
            this.release();
        }
    }
}