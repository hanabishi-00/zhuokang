package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class FaultTreeResultSave {

    //我么要执行创建表的DDl语句
    private static String creatsql_1 = "CREATE TABLE diag_res_guide("
            + "time int(11) not null,"
            + "record_id char(50) not null"
            + ")charset=utf8;";
     public  static String creatsql_2(int time,int unit_id,String shebei){
         String a="CREATE TABLE diag_sugg_"+shebei+"_"+unit_id+"_"+time+"("
                 + "node_id int(50) not null,"
                 + "freq float(50) not null,"
                 + "sugg_id  int(50) not null"
                 + ")charset=utf8;";

         System.out.println(a);
         return a;
     }
    public  static String creatsql_3 () {
         String b="CREATE TABLE bool_129_2018_03("
            + "t int(11) not null,"
            + "v int(50) not null,"
            + "f int(50) not null"
            + ")charset=utf8;";
        System.out.println(b);
         return b;
    }
   /* private static String creatsql_2 = "CREATE TABLE diag_sugg_"+"record_id("
            + "node_id int(50) not null,"
            + "freq float(50) not null,"
            + "sugg_id  int(50) not null"
            + ")charset=utf8;";*/

    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //指定连接数据库的url
  //  final static String DB_URL = "jdbc:mysql://218.197.228.243:3306/XJdatabase_core ";
//    final static String DB_URL = "jdbc:mysql://localhost:3306/test";
    final static String DB_URL = "jdbc:mysql://localhost:3306/xjdatabase_boolean";
    //mysql用户名
    final static String name = "root";
    //mysql密码
  //  final static String pwd = "123456";
    final static String pwd = "123456";
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //注册jdbc驱动
            Class.forName(JDBC_DRIVER);
            //打开连接
            System.out.println("//连接数据库");
            conn = DriverManager.getConnection(DB_URL, name, pwd);
            //执行创建表
            System.out.println("//创建表");
            stmt = conn.createStatement();
//            if (0 == stmt.executeUpdate(creatsql_2(1576774800,2,"Gov"))) {
            if (0 == stmt.executeUpdate(creatsql_3())) {
                System.out.println("成功创建表！");
            } else {
                System.out.println("创建表失败！");
            }
            stmt.close();
            conn.close();
            System.out.println("//关闭资源");
        } catch (Exception e) {
            System.out.println("创建表失败！");
            e.printStackTrace();
        }
    }


    public static void save(FaultTreeResult ftr) {

        String time = ftr.getTime();
        String record_id = ftr.getRecord_id();
        int sugg_id=ftr.getSugg_id();
        int node_id=ftr.getNode_id();
        float freq=ftr.getFreq();


      /*  String faultName=ftr.getFaultName();
        String faultLocation=ftr.getFaultLocation();
        int unitNo=ftr.getUnitNo();
        String recommendation=ftr.getRecommendation();*/
//			String sql = "Insert into BLH_FaultTree_Result(";
//
//			sql = sql + "Time, ";
//			sql = sql + "Result, ";
//			sql = sql + "Symptom, ";
//			sql = sql + "UnitNo) values(";
        // String sql = "Insert into FaultTree_Result(Time, RecordId, FaultName, FaultLocation,Recommendation,UnitNo) values('";
        String sql_1 = "Insert into diag_res_guide(time, record_id) values(";
        sql_1 = sql_1 + time + ",'";
        //    sql = sql + faultName + "','";
        //  sql = sql + faultLocation + "','";
        //    sql = sql + recommendation + "','";
        //  sql = sql + unitNo + "')";
        sql_1 = sql_1 + record_id + "')";
      /*  String sql_2 = "Insert into diag_sugg_+record_id(node_id, freq,sugg_id) values(";
        sql_2 = sql_2 + node_id + ",'";
        sql_2 = sql_2 + freq+ ",'";
        sql_2 = sql_2 + sugg_id + "')";*/
    }
}



