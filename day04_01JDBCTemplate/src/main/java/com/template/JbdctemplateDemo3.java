package com.template;

import com.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 最基本用法
 */
public class JbdctemplateDemo3 {
    public static void main(String[] args) {
        //1. 获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2. 获取对象
        JdbcTemplate jt = (JdbcTemplate) ac.getBean("jdbcTemplate");
        //3. 执行操作
        //保存
        //jt.update("insert into account(name,money) values(?,?)","eee","33333");
        //更新
        //jt.update("update account set name=?,money=? where id=?","test","25000","6");
        //删除
        //jt.update("delete from account where id=?","6");
        //查询所有
        /*List<Account> accounts = jt.query("select * from account where money > ?",new BeanPropertyRowMapper<Account>(Account.class),100f);
        for(Account a : accounts){
            System.out.println(a);
        }*/
        //查询一个
        /*List<Account> accounts = jt.query("select * from account where id = ?",new BeanPropertyRowMapper<Account>(Account.class),1);
        System.out.println(accounts.isEmpty()?"没有内容":accounts.get(0));*/
        //查询返回一行一列（使用聚合函数，但是不包含group by子句）
        Long count = jt.queryForObject("select count(*) from account where money > ?",Long.class,10000);
        System.out.println("个数"+count);
    }
}
class AccountRowMapper implements RowMapper<Account>{
    /**
     * 可以使用spring自带的代替：new BeanPropertyRowMapper<Account>(Account.class)
     * 定义Account的封装策略
     * 把结果集中的数据封装到Account中，然后由spring把每个account加入到集合中
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getFloat("money"));

        return account;
    }
}