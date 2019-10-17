package com.template;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 最基本用法
 */
public class JbdctemplateDemo1 {
    public static void main(String[] args) {
        //准备数据源：spring的内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=UTF-8");
        ds.setUsername("root");
        ds.setPassword("");
        //1. 创建JdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate(ds);
        //2. 执行操作
        jt.execute("insert into account(name,money) values('fff',10000)");
    }
}
