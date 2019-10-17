package com.cglib;


/**
 * 生产者
 */
public class Producer{

    public void saleProduct(float money){
        System.out.println("销售产品并拿到钱："+money);
    }

    public void afterService(float money){
        System.out.println("售后服务并拿到钱"+money);
    }
}
