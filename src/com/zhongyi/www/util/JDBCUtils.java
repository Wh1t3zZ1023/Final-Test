package com.zhongyi.www.util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 * @author Wh1t3zZ
 */
public class JDBCUtils {

    private static Properties properties = new Properties();

    static {
        InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据源
     *
     */
    public static DataSource getDataSource() {
        try {
            return BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放资源
     * @param stmt
     * @param conn
     */
    public static void close(Statement stmt,Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放资源
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs,Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static Connection conn = null;
    public void getLink(){
        try {
            String driver="com.mysql.cj.jdbc.Driver";
            String url="jdbc:mysql://localhost/db2?characterEncoding=utf-8&&serverTimezone=UTC&&useSSL=false";
            String user="root";
            String password="4906";
            //加载数据库驱动
            Class.forName(driver);
            //获取数据库连接
            conn = DriverManager.getConnection(url,user,password);

            if (conn != null) {
                System.out.println("数据库连接成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
