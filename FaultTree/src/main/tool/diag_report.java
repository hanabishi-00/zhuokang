package main.tool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class diag_report {
        // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final static String DB_URL_result = "jdbc:mysql://localhost:3306/hdy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
//    final static String DB_URL_result = "jdbc:mysql://rm-bp19iox2b2ef33bgevo.mysql.rds.aliyuncs.com:3306/hdy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        static final String USER = "huake";
        static final String PASS = "huake@123";


        //1.故障现象模块
        public static JSONObject diag_appear(String report_id){
            String[] reportid_msg = report_id.split("_");  //report_id形式类似于Bvo_1_1588146100,即本体/油系统_机组号_时间戳
            ArrayList<String> node_id = new ArrayList<>();
            ArrayList<Integer> model_edi = new ArrayList<>();
            ArrayList<String> nodename = new ArrayList<>();
            JSONObject result = new JSONObject();
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
                res = stmt.executeQuery("select * from diag_result where record_id = '"+report_id+"'");
                while(res.next()) {
                    if (res.getInt("sugg_id") != -1) {
                        node_id.add(String.valueOf(res.getInt("node_id")));
                        model_edi.add(res.getInt("model_edition"));
                    }
                }
                String ids = "(";
                for(int i=0;i<node_id.size();i++){
                    ids+=node_id.get(i);
                    if(i!=node_id.size()-1){
                        ids+=",";
                    }
                }
                ids+=")";
                res=stmt.executeQuery("select name from diag_model_"+reportid_msg[0].toLowerCase()
                        +" where model_edition = "+model_edi.get(0)+" and id in"+ids);
                while(res.next()){
                    nodename.add(res.getString("name"));
                }
                JSONArray jarray = new JSONArray();

                for(String name:nodename){
                    JSONObject jobject = new JSONObject();
                    jobject.put("name",name);
                    jobject.put("part","主进水阀");
                    jobject.put("id",String.valueOf(reportid_msg[1]));
                    jarray.add(jobject);
                }
                result.put("result",jarray);

                res.close();
                conn.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

        //2.数据特征模块
        public static JSONObject diag_data(String report_id){
            String[] reportid_msg = report_id.split("_");  //report_id形式类似于Bvo_1_1588146100,即本体/油系统_机组号_时间戳
            String[] pointkind1={"Boolean","Booltime","Special3"};//method为这几类时，测点为状态量
            String[] pointkind2={"Float","Trend","SpeedTrend","Special1"};//method为这几类时，测点为单精度
            String pointkind3="Special2";//method为这类时，测点为状态量与单精度组合
            ArrayList<String> node_id = new ArrayList<>();
            ArrayList<String> datatype = new ArrayList<>();//表示测点为单精度与状态量，1表示状态量，0表示单精度
            ArrayList<String> points = new ArrayList<>();
            ArrayList<Integer> model_edi = new ArrayList<>();
            JSONObject result = new JSONObject();
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
                res = stmt.executeQuery("select * from diag_result where record_id = '"+report_id+"'");
                while(res.next()) {
                    node_id.add(String.valueOf(res.getInt("node_id")));
                    model_edi.add(res.getInt("model_edition"));

                }
                String ids = "(";
                for(int i=0;i<node_id.size();i++){
                    ids+=node_id.get(i);
                    if(i!=node_id.size()-1){
                        ids+=",";
                    }
                }
                ids+=")";
                node_id.clear();
                res=stmt.executeQuery("select * from diag_model_"+reportid_msg[0].toLowerCase()
                        +" where model_edition = "+model_edi.get(0)+" and id in"+ids);
                while(res.next()){
                    if(!(res.getString("points")==null || res.getString("points").equals(""))){
                        if(Arrays.asList(pointkind1).contains(res.getString("method"))){
                            for(String p1:res.getString("points").split(";")){
                                if(!points.contains(p1.split(",")[Integer.parseInt(reportid_msg[1])-1])) {
                                    points.add(p1.split(",")[Integer.parseInt(reportid_msg[1]) - 1]);
                                    datatype.add("1");
                                }
                            }
                        }else if(Arrays.asList(pointkind2).contains(res.getString("method"))){
                            for(String p1:res.getString("points").split(";")){
                                if(!points.contains(p1.split(",")[Integer.parseInt(reportid_msg[1])-1])) {
                                    points.add(p1.split(",")[Integer.parseInt(reportid_msg[1]) - 1]);
                                    datatype.add("0");
                                }
                            }
                        }else if(pointkind3.equals(res.getString("method"))){
                            String p1=res.getString("points").split(";")[0];
                            if(!points.contains(p1.split(",")[Integer.parseInt(reportid_msg[1])-1])) {
                                points.add(p1.split(",")[Integer.parseInt(reportid_msg[1]) - 1]);
                                datatype.add("1");
                            }
                            p1=res.getString("points").split(";")[1];
                            if(!points.contains(p1.split(",")[Integer.parseInt(reportid_msg[1])-1])) {
                                points.add(p1.split(",")[Integer.parseInt(reportid_msg[1]) - 1]);
                                datatype.add("0");
                            }
                        }
                    }
                }

                JSONArray jarray = new JSONArray();

                for(int i=0;i<points.size();i++){
                    JSONObject jobject = new JSONObject();
                    jobject.put("hdbid",points.get(i).trim());
                    jobject.put("datatype",datatype.get(i));
                    jarray.add(jobject);
                }
                result.put("result",jarray);
                String starttime = String.valueOf(Integer.parseInt(reportid_msg[2])-3600);
                result.put("starttime",starttime+"000");
                result.put("endtime",reportid_msg[2]+"000");

                res.close();
                conn.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

        //3.故障原因模块
        public static JSONObject diag_reason(String report_id){
            JSONObject result = new JSONObject();
            ArrayList<Float> node_freq = new ArrayList<>();
            ArrayList<Integer> sugg_id = new ArrayList<>();
            ArrayList<String> diag_reason = new ArrayList<>();
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
                res = stmt.executeQuery("select * from diag_result where record_id = '" + report_id + "'");
                while(res.next()){
                    if(res.getInt("sugg_id")!=-1){
                        sugg_id.add(res.getInt("sugg_id"));
                        node_freq.add(res.getFloat("freq"));
                    }
                }
                for(int sugg_id1:sugg_id) {
                    res = stmt.executeQuery("select reason_com from diag_sugg where sugg_id = "+sugg_id1);
                    while(res.next()){
                        diag_reason.add(res.getString("reason_com"));
                    }
                }
                JSONArray jarray = new JSONArray();

                for(int i=0;i<diag_reason.size();i++){
                    JSONObject jobject = new JSONObject();
                    jobject.put("reason",diag_reason.get(i));
                    jobject.put("freq",node_freq.get(i));
                    jarray.add(jobject);
                }
                result.put("result",jarray);

                res.close();
                conn.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }


            return result;
        }

        //4.处理措施模块
        public static JSONObject diag_treat(String report_id){
            JSONObject result = new JSONObject();
            ArrayList<String> diag_repair = new ArrayList<>();
            ArrayList<Integer> sugg_id = new ArrayList<>();
            ArrayList<String> diag_tool = new ArrayList<>();
            ArrayList<String> diag_reason = new ArrayList<>();
            ArrayList<String> diag_part = new ArrayList<>();
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
                res = stmt.executeQuery("select * from diag_result where record_id = '" + report_id + "'");
                while(res.next()){
                    if(res.getInt("sugg_id")!=-1){
                        sugg_id.add(res.getInt("sugg_id"));
                    }
                }
                for(int sugg_id1:sugg_id) {
                    res = stmt.executeQuery("select * from diag_sugg where sugg_id = "+sugg_id1);
                    while(res.next()){
                        diag_reason.add(res.getString("reason_com"));
                        diag_part.add(res.getString("part_com"));
                        diag_repair.add(res.getString("repair_com"));
                        diag_tool.add(res.getString("tool_com"));
                    }
                }
                JSONArray jarray = new JSONArray();

                for(int i=0;i<diag_reason.size();i++){
                    JSONObject jobject = new JSONObject();
                    jobject.put("reason",diag_reason.get(i));
                    jobject.put("repair",diag_repair.get(i));
                    jobject.put("tool",diag_tool.get(i));
                    jobject.put("part",diag_part.get(i));
                    jarray.add(jobject);
                }
                result.put("result",jarray);
                res.close();
                conn.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return result;
        }

        //5.运行建议模块
        public static JSONObject diag_suggest(String report_id){
            JSONObject result = new JSONObject();
            ArrayList<String> diag_run = new ArrayList<>();
            ArrayList<Integer> sugg_id = new ArrayList<>();
            ArrayList<String> diag_detail = new ArrayList<>();
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
                res = stmt.executeQuery("select * from diag_result where record_id = '" + report_id + "'");
                while(res.next()){
                    if(res.getInt("sugg_id")!=-1){
                        sugg_id.add(res.getInt("sugg_id"));
                    }
                }
                for(int sugg_id1:sugg_id) {
                    res = stmt.executeQuery("select * from diag_sugg where sugg_id = "+sugg_id1);
                    while(res.next()){
                        diag_run.add(res.getString("run_com"));
                        diag_detail.add((res.getString("detail_com")==null || res.getString("detail_com").equals(""))?"无":res.getString("detail_com"));
                    }
                }
                JSONArray jarray = new JSONArray();

                for(int i=0;i<diag_run.size();i++){
                    JSONObject jobject = new JSONObject();
                        jobject.put("run", diag_run.get(i));
                        jobject.put("detail", diag_detail.get(i));
                        jarray.add(jobject);

                }
                result.put("result",jarray);
                res.close();
                conn.close();


            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return result;
        }



    // 返回故障名称节点id
    public static ArrayList<String> diag_id(){
        ArrayList<String> result = new ArrayList<>();
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
                result.add(String.valueOf(sugg_id.getInt("sugg_id")));
            }
            sugg_id.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


        public static void main(String[] args){
            System.out.println(diag_appear("Bvo_3_1588146100"));
            System.out.println(diag_reason("Bvo_3_1588146100"));
            System.out.println(diag_treat("Bvo_3_1588146100"));
            System.out.println(diag_suggest("Bvo_3_1588146100"));
            System.out.println(diag_data("Bvo_1_1513651125"));
//            String[] asd={"123","456"};
//            String qwe="123";
//            System.out.println(Arrays.asList(asd).contains(qwe));
        }
}
