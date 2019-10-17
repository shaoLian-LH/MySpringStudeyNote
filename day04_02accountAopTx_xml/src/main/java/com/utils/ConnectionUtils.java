package com.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();


    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getConnection(){
        //1. 从ThreadLocal上获取
        Connection conn = tl.get();
        //2. 判断线程上是否有连接
        try {
            if(conn == null){
                //3. 从数据源中获取一个连接，并且和线程绑定（存入数组）
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            //4. 返回当前线程上的连接
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 连接和线程解绑
     */
    public void removeConnection(){
        tl.remove();
    }
}
