package main.dao;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import main.model.Node;
import main.model.MakeFaultTree;

public class ResultSave {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String DB_URL_result = "jdbc:mysql://rm-bp19iox2b2ef33bgevo.mysql.rds.aliyuncs.com:3306/hdy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    static final String USER = "huake";
    static final String PASS = "huake@123";

//    存放单次诊断结果
    public static int savediagres(long time, String Uid, int kind, ArrayList<Node> resultnode){
        ArrayList<String> tname = new ArrayList<>();
        tname.add("Bvb");
        tname.add("Bvo");
        String tablename = "Diag_result";
//        tablename += time;
        String creatsql = "CREATE TABLE "+tablename+"(id int not null auto_increment, record_id char(20)," +
                " node_id int, freq float, " +
                "sugg_id int, primary key (id))auto_increment=1";
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement sql;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            String DB_URL = DB_URL_result;
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet sugg_id = stmt.executeQuery("select * from diag_sugg");
            ArrayList<String> sugg_id1 = new ArrayList<>();
            while(sugg_id.next()){
                sugg_id1.add(String.valueOf(sugg_id.getInt("sugg_id")));
            }
            sugg_id.close();
            if(tablename != null){
                ResultSet tables = conn.getMetaData().getTables(conn.getCatalog(),null,tablename, new String[]{"TABLE"});
                if (!tables.next()){
                    Statement creatstate = conn.createStatement();
                    creatstate.execute(creatsql);
//                    throw new Exception("该表名已存在，请重新输入！");
//                    System.out.println("该表名已存在");
//                    return 0;
                }
                tables.close();

                sql = conn.prepareStatement("insert into "+tablename+"(record_id,node_id,freq,sugg_id) values(?,?,?,?)");
                for (int i=0;i<resultnode.size();i++){
                    sql.setString(1,tname.get(kind-1)+"_"+Uid+"_"+time);
                    sql.setInt(2,Integer.parseInt(resultnode.get(i).getId()));
                    sql.setFloat(3, resultnode.get(i).getX());
                    if(sugg_id1.contains("1"+resultnode.get(i).getId())){
                        sql.setInt(4, Integer.parseInt("1"+resultnode.get(i).getId()));
                    }
                    else {
                        sql.setInt(4, -1);
                    }
                    sql.executeUpdate();
                }
                conn.close();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return 1;
    }


    //    存放诊断结果引导表
    public static int savediagresguide(long time, String Uid, int kind){
        ArrayList<String> tname = new ArrayList<>();
        tname.add("Bvb");
        tname.add("Bvo");
        String tablename = "Diag_res_guide";
//        tablename += time;
        String creatsql = "CREATE TABLE "+tablename+"(time int(11), record_id char(20) not null," +
                " primary key (record_id))";
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement sql;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            String DB_URL = DB_URL_result;
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            ResultSet sugg_id = stmt.executeQuery("select sugg_id from diag_sugg");
//            ArrayList<String> sugg_id1 = new ArrayList<>();
//            while(sugg_id.next()){
//                sugg_id1.add(String.valueOf(sugg_id.getInt("sugg_id")));
//            }
//            sugg_id.close();
            if(tablename != null){
                ResultSet tables = conn.getMetaData().getTables(conn.getCatalog(),null,tablename, new String[]{"TABLE"});
                if (!tables.next()){
                    Statement creatstate = conn.createStatement();
                    creatstate.execute(creatsql);
//                    throw new Exception("该表名已存在，请重新输入！");
//                    System.out.println("该表名已存在");
//                    return 0;
                }
                tables.close();
                sql = conn.prepareStatement("insert into "+tablename+" values(?,?)");
                sql.setInt(1, (int) time);
                sql.setString(2,tname.get(kind-1)+"_"+Uid+"_"+time);
                sql.executeUpdate();
                conn.close();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return 1;
    }

}


