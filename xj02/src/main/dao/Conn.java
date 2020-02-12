package main.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conn{
      private Connection conn = null;     //声明数据库的连接对象
      private Statement stmt = null;       //声明statement对象
      private ResultSet rs = null;       //声明result对象

      // 仙居数据数据库，此处为自己建立的测试库，库为空
      //以下为mysql 8.0及以上的连接字段
      private String driver="com.mysql.jdbc.Driver";
      private String url="jdbc:mysql://218.197.228.243:3306/XJdatabase_core ";
      //useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
      private String user="remote";
      private String password="123456";


      static Statement sql;
       Connection con;
      static int columnCount;
      private List<String> num = new ArrayList<String>();
      ResultSetMetaData data = null;
      String sql1 = null;

      public Connection getConnection(){
          try {
              Class.forName(driver);    System.out.println("数据库驱动加载成功");
          }catch(ClassNotFoundException e) {
              e.printStackTrace();
          }
          try {
              con = DriverManager.getConnection(url, user, password);
              System.out.println("数据库连接成功");
          }catch(SQLException e){
              e.printStackTrace();
              System.out.println("数据库连接失败！");
          }
          return con;
      }

      public static void main(String[] args){
          Conn c=new Conn();
          c.getConnection();
      }

  }
