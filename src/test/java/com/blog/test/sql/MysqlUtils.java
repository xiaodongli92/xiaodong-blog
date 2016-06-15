package com.blog.test.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: lixiaodong@souyidai.com
 * @CreateDate: 2016/6/15
 */
public class MysqlUtils {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/my_test";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "admin";

    private static ThreadLocal<Connection> connContainer = new ThreadLocal<>();

    private MysqlUtils(){}

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = connContainer.get();
        if (null == conn) {
            try {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connContainer.set(conn);
            }
        }
        return conn;
    }

    /**
     * 关闭连接
     */
    public static void closeConnection(){
        Connection conn = connContainer.get();
        if (null != conn){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connContainer.remove();
            }
        }
    }

}
