package main.dao;

import main.service.BoolTree;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TreeUpdate {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    final static String DB_URL_result = "jdbc:mysql://rm-bp19iox2b2ef33bgevo.mysql.rds.aliyuncs.com:3306/hdy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    final static String DB_URL_result = "jdbc:mysql://localhost:3306/hdy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    static final String USER = "huake";
    static final String PASS = "huake@123";

    public static ArrayList<ArrayList<String>> searchstr(ArrayList<ArrayList<String>> array0, int x,String str){
        ArrayList<ArrayList<String>> array2 = new ArrayList<>();
        for(ArrayList<String> array1:array0){
            if(array1.get(x).equals(str)){
                array2.add(array1);
            }
        }
        return array2;
    }

    public static ArrayList<ArrayList<String>> tree2model1(ArrayList<ArrayList<String>> array1,
                                                           ArrayList<ArrayList<String>> array2,
                                                           ArrayList<ArrayList<String>> array3){
        for(ArrayList<String> newarray:array1){
            if(newarray.get(6).equals("0")){                //若不是叶节点
                ArrayList<String> midarray = new ArrayList<>();
                midarray.add(newarray.get(0));                  //id
                midarray.add(newarray.get(1));                  //name
                midarray.add(newarray.get(2));                  //des
                if(newarray.get(3).equals("ROOT")){
                    midarray.add("0");                  //pid
                    midarray.add("null");                  //father_name
                }else{
                    midarray.add(searchstr(array3,0,newarray.get(3)).get(0).get(3));                  //pid
                    midarray.add(searchstr(array3,0,searchstr(array3,0,newarray.get(3)).get(0).get(3)).get(0).get(1));
                    //father_name
                }
                if(searchstr(array3,3,newarray.get(0)).get(0).get(5).equals("与门")){
                    midarray.add("0");                  //gatetype
                }else{
                    midarray.add("+");                  //gatetype
                }
                midarray.add("null");                  //points
                midarray.add("null");                  //method
                midarray.add("null");                  //threshold
                array2.add((ArrayList<String>) midarray.clone());
                tree2model1(searchstr(array3,3,searchstr(array3,3,newarray.get(0)).get(0).get(0)),array2,array3);
                midarray.clear();
            }else{
                ArrayList<String> midarray = new ArrayList<>();
                midarray.add(newarray.get(0));                  //id
                midarray.add(newarray.get(1));                  //name
                midarray.add(newarray.get(2));                  //des
                if(newarray.get(3).equals("ROOT")){
                    midarray.add("0");                  //pid
                    midarray.add("null");                  //father_name
                }else{
                    midarray.add(searchstr(array3,0,newarray.get(3)).get(0).get(3));                  //pid
                    midarray.add(searchstr(array3,0,searchstr(array3,0,newarray.get(3)).get(0).get(3)).get(0).get(1));
                    //father_name
                }
                midarray.add("+");                  //gatetype

                midarray.add(newarray.get(8));                  //points
                midarray.add(newarray.get(7));                  //method
                midarray.add(newarray.get(9));                  //threshold
                array2.add((ArrayList<String>) midarray.clone());
                midarray.clear();
            }
        }
        return array2;
    }

//将修改的节点信息从diag_tree表中存到diag_model_bvo中和diag_model_bvb中
   public static boolean tree2model(String x){
        ArrayList<ArrayList<String>> treedata = new ArrayList<>();
        ArrayList<ArrayList<String>> otdata = new ArrayList<>();
        ArrayList<ArrayList<String>> btdata = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement sql1;
        PreparedStatement sql2;
        ResultSet res;

        try{
            Class.forName(JDBC_DRIVER);
            String DB_URL = DB_URL_result;
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt=conn.createStatement();
            res = stmt.executeQuery("select * from diag_tree");
            while(res.next()){
                ArrayList<String> array2 = new ArrayList<>();
                array2.add(res.getString("id"));
                array2.add(res.getString("name"));
                array2.add((res.getString("des")==null || res.getString("des").equals(""))?"null":res.getString("des"));
                array2.add(res.getString("pid"));
                array2.add(res.getString("nodetype"));
                array2.add((res.getString("gatetype")==null || res.getString("gatetype").equals(""))?"null":res.getString("gatetype"));
                array2.add(res.getString("isleaf"));
                array2.add((res.getString("method")==null || res.getString("method").equals(""))?"null":res.getString("method"));
                array2.add((res.getString("points")==null || res.getString("points").equals(""))?"null":res.getString("points"));
                array2.add((res.getString("threshold")==null || res.getString("threshold").equals(""))?"null":res.getString("threshold"));
                treedata.add((ArrayList<String>) array2.clone());
                array2.clear();
            }
            res.close();
            ArrayList<ArrayList<String>> rootary = searchstr(treedata,3,"ROOT"); //寻找根节点
            Map<String,String> map = new HashMap<>();
            map.put("o","insert into diag_model_bvo values(?,?,?,?,?,?,?,?,?,?)");
            map.put("b","insert into diag_model_bvb values(?,?,?,?,?,?,?,?,?,?)");
            for(ArrayList<String> array3:rootary){
                ArrayList<ArrayList<String>> temparray = new ArrayList<>();
                if(array3.get(0).substring(0,1).equals(x)){
                    temparray.add(array3);
                    otdata = tree2model1(temparray,otdata,treedata);
                    temparray.clear();
                    if(!TreeCheck(otdata)){
                        return false;
                    }
                    int save_time = (int) (System.currentTimeMillis()/1000);
                    sql1 = conn.prepareStatement(map.get(x));
                    for(ArrayList<String> ary1:otdata){
                        sql1.setInt(1, Integer.parseInt(ary1.get(0).substring(1)));
                        sql1.setString(2, ary1.get(1));
                        sql1.setString(3, ary1.get(2));
                        if(ary1.get(3).substring(1).equals("")){
                            sql1.setInt(4, Integer.parseInt(ary1.get(3)));
                        }else {
                            sql1.setInt(4, Integer.parseInt(ary1.get(3).substring(1)));
                        }
                        sql1.setString(5, ary1.get(4));
                        sql1.setString(6, ary1.get(5));
                        sql1.setString(7, ary1.get(6));
                        sql1.setString(8, ary1.get(7));
                        sql1.setString(9, ary1.get(8));
                        sql1.setInt(10, save_time);
                        sql1.executeUpdate();
                    }
                    sql1.close();
                }
//                else if(array3.get(0).substring(0,1).equals("b")){
//                    temparray.add(array3);
//                    btdata = tree2model1(temparray,btdata,treedata);
//                    temparray.clear();
//                }
            }

//            int save_time = (int) (System.currentTimeMillis()/1000);
//            //向diag_model_bvo中存放数据
//            sql1 = conn.prepareStatement("insert into diag_model_bvo values(?,?,?,?,?,?,?,?,?,?)");
//            for(ArrayList<String> ary1:otdata){
//                sql1.setInt(1, Integer.parseInt(ary1.get(0).substring(1)));
//                sql1.setString(2, ary1.get(1));
//                sql1.setString(3, ary1.get(2));
//                if(ary1.get(3).substring(1).equals("")){
//                    sql1.setInt(4, Integer.parseInt(ary1.get(3)));
//                }else {
//                    sql1.setInt(4, Integer.parseInt(ary1.get(3).substring(1)));
//                }
//                sql1.setString(5, ary1.get(4));
//                sql1.setString(6, ary1.get(5));
//                sql1.setString(7, ary1.get(6));
//                sql1.setString(8, ary1.get(7));
//                sql1.setString(9, ary1.get(8));
//                sql1.setInt(10, save_time);
//                sql1.executeUpdate();
//            }
//            sql1.close();
//
//            //向diag_model_bvb中存放数据
//            sql2 = conn.prepareStatement("insert into diag_model_bvb values(?,?,?,?,?,?,?,?,?,?)");
//            for(ArrayList<String> ary1:btdata){
//                sql2.setInt(1, Integer.parseInt(ary1.get(0).substring(1)));
//                sql2.setString(2, ary1.get(1));
//                sql2.setString(3, ary1.get(2));
//                if(ary1.get(3).substring(1).equals("")){
//                    sql1.setInt(4, Integer.parseInt(ary1.get(3)));
//                }else {
//                    sql1.setInt(4, Integer.parseInt(ary1.get(3).substring(1)));
//                }
//                sql2.setString(5, ary1.get(4));
//                sql2.setString(6, ary1.get(5));
//                sql2.setString(7, ary1.get(6));
//                sql2.setString(8, ary1.get(7));
//                sql2.setString(9, ary1.get(8));
//                sql2.setInt(10, save_time);
//                sql2.executeUpdate();
//            }
//            sql2.close();
        conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }



//        System.out.println(otdata.size());
//        for(ArrayList<String> a1:otdata){
//            System.out.println(a1);
//        }
       return true;
    }

    //将节点信息从diag_model_bvo中和diag_model_bvb中存到diag_tree表中
    public static void model2tree(){
        ArrayList<ArrayList<String>> treedata = new ArrayList<>();
        ArrayList<ArrayList<String>> otdata = new ArrayList<>();
        ArrayList<ArrayList<String>> btdata = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement sql1;
//        PreparedStatement sql2;
        ResultSet res1;
        ResultSet res2;
        try {
            Class.forName(JDBC_DRIVER);
            String DB_URL = DB_URL_result;
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            res1 = stmt.executeQuery("select * from diag_model_bvo where model_edition = (select max(model_edition) from diag_model_bvo)");
            while(res1.next()){
                ArrayList<String> array2 = new ArrayList<>();
                array2.add(String.valueOf(res1.getInt("id")));
                array2.add(res1.getString("name"));
                array2.add((res1.getString("des")==null || res1.getString("des").equals(""))?"null":res1.getString("des"));
                array2.add(String.valueOf(res1.getInt("pid")));
                array2.add(res1.getString("father_name"));
                array2.add(res1.getString("gatetype"));
                array2.add((res1.getString("points")==null || res1.getString("points").equals(""))?"null":res1.getString("points"));
//                array2.add(( res.getString("叶子节点发生概率")==null || res.getString("叶子节点发生概率").equals(""))?"null":res.getString("叶子节点发生概率"));
                array2.add((res1.getString("method")==null || res1.getString("method").equals(""))?"null":res1.getString("method"));
                array2.add(( res1.getString("threshold")==null || res1.getString("threshold").equals(""))?"null":res1.getString("threshold"));
                otdata.add((ArrayList<String>) array2.clone());
                array2.clear();
            }
            res1.close();
//            System.out.println(otdata);
            res2 = stmt.executeQuery("select * from diag_model_bvb where model_edition = (select max(model_edition) from diag_model_bvb)");
            while(res2.next()){
                ArrayList<String> array2 = new ArrayList<>();
                array2.add(String.valueOf(res2.getInt("id")));
                array2.add(res2.getString("name"));
                array2.add((res2.getString("des")==null || res2.getString("des").equals(""))?"null":res2.getString("des"));
                array2.add(String.valueOf(res2.getInt("pid")));
                array2.add(res2.getString("father_name"));
                array2.add(res2.getString("gatetype"));
                array2.add((res2.getString("points")==null || res2.getString("points").equals(""))?"null":res2.getString("points"));
//                array2.add(( res.getString("叶子节点发生概率")==null || res.getString("叶子节点发生概率").equals(""))?"null":res.getString("叶子节点发生概率"));
                array2.add((res2.getString("method")==null || res2.getString("method").equals(""))?"null":res2.getString("method"));
                array2.add(( res2.getString("threshold")==null || res2.getString("threshold").equals(""))?"null":res2.getString("threshold"));
                btdata.add((ArrayList<String>) array2.clone());
                array2.clear();
            }

            stmt = conn.createStatement();
            stmt.executeUpdate("truncate diag_tree");

            sql1 = conn.prepareStatement("insert into diag_tree(id,name,des,pid,nodetype,gatetype," +
                    "isleaf,method,points,threshold) values(?,?,?,?,?,?,?,?,?,?)");
            for(ArrayList<String> ary1:otdata){
                sql1.setString(1,"o"+ary1.get(0));      //id
                sql1.setString(2,ary1.get(1));          //name
                sql1.setString(3,ary1.get(2).equals("null")?"":ary1.get(2));          //des
                if(ary1.get(3).equals("0")){
                    sql1.setString(4,"ROOT");           //pid
                }else{
                    sql1.setString(4,"og"+ary1.get(3));         //pid
                }
                sql1.setString(5,"N");                  //nodetype
                sql1.setString(6,"");                  //gatetype
                if(!ary1.get(6).equals("null")){
                    sql1.setString(7,"1");          //isleaf
                    sql1.setString(8,ary1.get(7).equals("null")?"":ary1.get(7));     //method
                    sql1.setString(9,ary1.get(6).equals("null")?"":ary1.get(6));     //points
                    sql1.setString(10,ary1.get(8).equals("null")?"":ary1.get(8));     //threshold
                    sql1.executeUpdate();
                }else{
                    sql1.setString(7,"0");          //isleaf
                    sql1.setString(8,ary1.get(7).equals("null")?"":ary1.get(7));     //method
                    sql1.setString(9,ary1.get(6).equals("null")?"":ary1.get(6));     //points
                    sql1.setString(10,ary1.get(8).equals("null")?"":ary1.get(8));     //threshold
                    sql1.executeUpdate();
                    sql1.setString(1,"og"+ary1.get(0));
                    if(ary1.get(5).equals("+")){
                        sql1.setString(2,"或门");
                        sql1.setString(6,"或门");
                    }else{
                        sql1.setString(2,"与门");
                        sql1.setString(6,"与门");
                    }
                    sql1.setString(5,"G");
                    sql1.setString(4,"o"+ary1.get(0));
                    sql1.setString(7,"0");
                    sql1.setString(3,"");
                    sql1.setString(8,"");
                    sql1.setString(9,"");
                    sql1.setString(10,"");
                    sql1.executeUpdate();
                }

            }
            for(ArrayList<String> ary1:btdata){
                sql1.setString(1,"b"+ary1.get(0));      //id
                sql1.setString(2,ary1.get(1));          //name
                sql1.setString(3,ary1.get(2).equals("null")?"":ary1.get(2));          //des
                if(ary1.get(3).equals("0")){
                    sql1.setString(4,"ROOT");           //pid
                }else{
                    sql1.setString(4,"bg"+ary1.get(3));         //pid
                }
                sql1.setString(5,"N");                  //nodetype
                sql1.setString(6,"");
                if(!ary1.get(6).equals("null")){                    //是叶子节点
                    sql1.setString(7,"1");
                    sql1.setString(8,ary1.get(7).equals("null")?"":ary1.get(7));     //method
                    sql1.setString(9,ary1.get(6).equals("null")?"":ary1.get(6));     //points
                    sql1.setString(10,ary1.get(8).equals("null")?"":ary1.get(8));     //threshold
                    sql1.executeUpdate();
                }else{
                    sql1.setString(7,"0");
                    sql1.setString(8,ary1.get(7).equals("null")?"":ary1.get(7));
                    sql1.setString(9,ary1.get(6).equals("null")?"":ary1.get(6));
                    sql1.setString(10,ary1.get(8).equals("null")?"":ary1.get(8));
                    sql1.executeUpdate();
                    sql1.setString(1,"bg"+ary1.get(0));
                    if(ary1.get(5).equals("+")){
                        sql1.setString(2,"或门");
                        sql1.setString(6,"或门");
                    }else{
                        sql1.setString(2,"与门");
                        sql1.setString(6,"与门");
                    }
                    sql1.setString(5,"G");
                    sql1.setString(4,"b"+ary1.get(0));
                    sql1.setString(7,"0");
                    sql1.setString(3,"");
                    sql1.setString(8,"");
                    sql1.setString(9,"");
                    sql1.setString(10,"");
                    sql1.executeUpdate();
                }

            }
            sql1.close();
        conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean TreeCheck(ArrayList<ArrayList<String>> ary1){
        boolean flag = true;
        String regex;
        for(ArrayList<String> ary2:ary1){


            //检查输入的测点是否符合格式，eg. 1,2,3,4;5,6,7,8
            regex= "^\\s*\\d+\\s*,\\s*\\d+\\s*,\\s*\\d+\\s*,\\s*\\d+\\s*$";
            if(ary2.get(6).equals("null")){
                continue;
            }else{
                for(int i=0;i<ary2.get(6).split(";").length;i++){
                    if(!ary2.get(6).split(";")[i].matches(regex)){
                        flag=false;
                        break;
                    }
                }
            }

            //判断Id和Pid是否由一个字母和一组数字组成
            regex = "^[a-z]\\d+$|0";
            if(!ary2.get(0).matches(regex) || !ary2.get(3).matches(regex)){
                flag=false;
            }

            ////检查输入的阈值是否符合格式，eg. 1或1,2或null,1
//            if(ary2.get(8).equals("null")){
//                continue;
//            }else{
//                if(ary2.get(8).split(",").length>2){
//                    flag=false;
//                }
//            }

            //判断是否存在不存在子节点的非叶子节点
            if(ary2.get(6).equals("null") && searchstr(ary1,3,ary2.get(0)).size()==0){
                flag=false;
            }

        }

        return flag;
    }

    public static void main(String[] args){
//        int num=11;
//        String asd="r123";
//        System.out.println("o"+num);

        tree2model("o");
//        model2tree();
//        String regex= "^\\s*\\d+\\s*,\\s*\\d+\\s*,\\s*\\d+\\s*,\\s*\\d+\\s*$";
////        regex = "^[a-z]\\d+$|0";
////        regex = "0";
//        String asd1=" 1612, 3526, 5440, 7354";
//        System.out.println(tree2model("o"));
//        System.out.println(asd1.matches(regex));
    }


}
