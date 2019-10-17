package com.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，里面提供了公共的代码
 */
@Component("logger")
@Aspect/*表示当前类是一个切面类*/
public class Logger {
    /**
     * 在使用注解代码的结构下，拥有一个调用顺序的问题
     * spring的调用顺序是：前置 -- 最终 -- 后置（或异常）
     * 实际开发中应该注意
     */
    @Pointcut("execution(* com.service.impl.*.*(..))")
    private void pointcut(){

    }
    /**
     *  前置通知
     */
    //@Before("pointcut()")
    public void beforePrintLog(){
        System.out.println("前置通知beforePrintLog方法开始记录日志了");
    }
    /**
     *  后置通知
     */
    //@AfterReturning("pointcut()")
    public void afterReturningPrintLog(){
        System.out.println("后置通知afterReturningPrintLog方法开始记录日志了");
    }
    /**
     *  异常通知
     */
    //@AfterThrowing("pointcut()")
    public void afterThrowingPrintLog(){
        System.out.println("异常通知afterThrowingPrintLog方法开始记录日志了");
    }
    /**
     *  最终通知
     */
    //@After("pointcut()")
    public void afterPrintLog(){
        System.out.println("最终通知afterPrintLog方法开始记录日志了");
    }

    /**
     * 但是使用环绕通知就不会发生上述问题
     * 使用注解时用环绕通知会好
     * @param proceedingJoinPoint
     * @return
     */
    @Around("pointcut()")
    public Object aroundPrintLog(ProceedingJoinPoint proceedingJoinPoint){
        /* 明确调用了切入点方法 */
        Object rtValue = null;
        try{
            Object[] args = proceedingJoinPoint
                    .getArgs();
            System.out.println("环绕通知方法的前置");
            rtValue = proceedingJoinPoint.proceed(args);
            System.out.println("环绕通知方法的后置");
            return rtValue;
        }catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知方法的异常");
            throw new RuntimeException(throwable);
        }finally {
            System.out.println("环绕通知方法的最终");
        }
    }
}
