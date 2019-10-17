package com.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 用于记录日志的工具类，里面提供了公共的代码
 */
public class Logger {
    /**
     *  前置通知
     */
    public void beforePrintLog(){
        System.out.println("前置通知beforePrintLog方法开始记录日志了");
    }
    /**
     *  后置通知
     */
    public void afterReturningPrintLog(){
        System.out.println("后置通知afterReturningPrintLog方法开始记录日志了");
    }
    /**
     *  异常通知
     */
    public void afterThrowingPrintLog(){
        System.out.println("异常通知afterThrowingPrintLog方法开始记录日志了");
    }
    /**
     *  最终通知
     */
    public void afterPrintLog(){
        System.out.println("最终通知afterPrintLog方法开始记录日志了");
    }
    /**
     *  环绕通知
     *  问题：
     *      当配置了环绕通知之后，切入点方法没有执行，而环绕通知执行了
     *  分析：
     *      通过对比动态代理的环绕通知代码，发现动态代理中的环绕通知有明确的切入点方法调用，而此例中没有
     *  解决：
     *      Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法是proceed()，此方法就相当于明确调用切入点方法
     *      该接口可作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用
     *  Spring中的环绕通知：
     *      是spring框架为我们提供的一种可以在代码中执行的通知方式
     */
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
