package main.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
public class GovDBConfig {
    private static String sDBDriver;
    private static String url;
    private static String user;
    private static String pwd;

    static {
        Properties p =new Properties();
        InputStream stream = null;
    }
    public static Connection getconnection() {    //连接数据库
        Connection conn = null;
        try{        //加载数据库驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");    System.out.println("数据库驱动加载成功");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test_xjfta?useSSL=false&serverTimezone=UTC","root","123456");
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 批量执行sql语句
     *
     * @param list
     * @return
     */
    //此处缺少一段代码？？？？

    /**
     * 关闭Statement
     *
     * @param stmt
     */
    private static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void closeResultSet(ResultSet rs){
        if (rs != null) {
            try{
                rs.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 关闭ResultSet、Statement、Connection
     *
     * @param rs
     * @param stmt
     * @param con
     */
    public static void closeConnection(ResultSet rs, Statement stmt, Connection con) {
        closeResultSet(rs);
        closeStatement(stmt);
        closeConnection(con);
    }

    /**
     * 关闭Statement、Connection
     *
     * @param stmt
     * @param con
     */
    public static void closeConnection(Statement stmt, Connection con) {
        closeStatement(stmt);
        closeConnection(con);
    }

    /**
     * 关闭Connection
     *
     * @param con
     */
    private static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
