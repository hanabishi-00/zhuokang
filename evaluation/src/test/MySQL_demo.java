package test;

import java.sql.*;

public class MySQL_demo {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://218.197.228.243:3306/XJdatabase_core";
    //static final String DB_URL = "jdbc:mysql://218.197.228.243:3306/XJdatabase_boolean";
    static final String DB_URL = "jdbc:mysql://218.197.228.243:3306/result";
    //static final String DB_URL = "jdbc:mysql://192.168.1.194:3306/develop";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "remote";
    static final String PASS = "123456";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            /*//sql = "SELECT * FROM tbl50_2018_10 where tm>1538324031 AND tm<1538325000";
            //ResultSet rs = stmt.executeQuery(sql);

            for (int id=4650;id<4655;id++) {
                for (int year=2017;year<2020;year++) {
                    for (int month=1;month<13;month++) {
                        String new_month=month+"";
                        if (month<10){
                            new_month="0"+new_month;
                        }
                        //String[] table_name = {"tbl" + id + "_" + year + "_" + month, "float_" + id + "_" + year + "_" + new_month};
                        String[] table_name = {"bool_" + id + "_" + year + "_" + month, "bool_" + id + "_" + year + "_" + new_month};
                        //String[] table_name = {"float_" + id + "_" + year + "_" + month, "float_" + id + "_" + year + "_" + new_month};
                        String[] c = {"tm", "id" + id + "_v", "id" + id + "_f"};
                        //String[] c = {"t", "v", "f"};

                        String sql_table = "ALTER  TABLE " + table_name[0] + " RENAME TO " + table_name[1];
                        //String sql_c1 = "ALTER  TABLE " + table_name[1] + " ChANGE  COLUMN " + c[0] + " t int(11)";
                        //String sql_c2 = "ALTER  TABLE " + table_name[1] + " ChANGE  COLUMN " + c[1] + " v float";
                        //String sql_c2 = "ALTER  TABLE " + table_name[1] + " ChANGE  COLUMN " + c[1] + " v tinyint(4)";
                        //String sql_c3 = "ALTER  TABLE " + table_name[1] + " ChANGE  COLUMN " + c[2] + " f tinyint(4)";

                        String sql_c1 = "ALTER  TABLE " + table_name[1] + " ChANGE  COLUMN t t int(11) NOT NULL";
                        String sql_c2 = "ALTER  TABLE " + table_name[1] + " ChANGE  COLUMN v v tinyint(4) NOT NULL";
                        String sql_c3 = "ALTER  TABLE " + table_name[1] + " ChANGE  COLUMN f f tinyint(4) NOT NULL";

                        try {
                            //stmt.execute(sql_table);
                            stmt.execute(sql_c1);
                            stmt.execute(sql_c2);
                            stmt.execute(sql_c3);
                            System.out.println(table_name[1]+"修改成功");

                        } catch (Exception e) {
                            System.out.println("无该表");
                        }
                    }
                }
            }*/

            sql="CREATE TABLE IF NOT EXISTS creator_test (f1 int(11) not null, f2 float not null, f_ordinal varchar(100) not null," +
                    "PRIMARY KEY(f1,f2))";
            stmt.execute(sql);

            sql="INSERT INTO creator_test VALUES ( 11, 3.44, '很不错哦');";
            stmt.execute(sql);



            /*// 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                long id  = rs.getLong("tm");
                String value = rs.getString("id50_v");
                String flag = rs.getString("id50_f");

                // 输出数据
                System.out.print("时间: " + id);
                System.out.print(", 测点值: " + value);
                System.out.print(", 标志: " + flag);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();*/
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
