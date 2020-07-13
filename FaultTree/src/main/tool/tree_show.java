package main.tool;

import java.sql.*;
import java.util.ArrayList;

public class tree_show {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //        final static String DB_URL_result = "jdbc:mysql://localhost:3306/hdy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    final static String DB_URL_result = "jdbc:mysql://rm-bp19iox2b2ef33bgevo.mysql.rds.aliyuncs.com:3306/hdy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    static final String USER = "huake";
    static final String PASS = "huake@123";


    public static ArrayList<ArrayList<String>> tree_show_get(String record_id){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        int model_edition=1;

        Connection conn;
        Statement stmt;
        ResultSet res;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
//            System.out.println("连接数据库...");
            String DB_URL = DB_URL_result;
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            res = stmt.executeQuery("select * from diag_result where record_id = '"+record_id+"'");
            while(res.next()){
                model_edition=res.getInt("model_edition");
            }
            res = stmt.executeQuery("select * from diag_model_"+record_id.substring(0,3).toLowerCase()
                    +" where model_edition = "+model_edition);
            while(res.next()){
                ArrayList<String> array2 = new ArrayList<>();
                array2.add(String.valueOf(res.getInt("id")));
                array2.add(res.getString("name"));
                array2.add(String.valueOf(res.getInt("pid")));
                array2.add(res.getString("father_name"));
                array2.add(res.getString("gatetype"));
                array2.add((res.getString("points")==null || res.getString("points").equals(""))?"null":res.getString("points"));
//                array2.add(( res.getString("叶子节点发生概率")==null || res.getString("叶子节点发生概率").equals(""))?"null":res.getString("叶子节点发生概率"));
                array2.add((res.getString("method")==null || res.getString("method").equals(""))?"null":res.getString("method"));
                array2.add(( res.getString("threshold")==null || res.getString("threshold").equals(""))?"null":res.getString("threshold"));
                result.add((ArrayList<String>) array2.clone());
                array2.clear();
            }
            String freq="0.0";
            for(int i=0;i<result.size();i++){
                res=stmt.executeQuery("select freq from diag_result where record_id = '"+record_id+"' and node_id = "+result.get(i).get(0));
//                System.out.println(res.next());
                if(!res.next()){
                    result.get(i).add("0.0");
                }else{
                    result.get(i).add(String.valueOf(res.getFloat("freq")));

                }
            }




        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args){
//        ArrayList<ArrayList<String>> asd = tree_show_get("Bvo_1_1588146100");
//        for(ArrayList<String> as:asd){
//            System.out.println(as);
//        }
    }

}
