package main.tool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static main.dao.TreeUpdate.searchstr;

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
                array2.add(String.valueOf(res.getInt("id")));   //0
                array2.add(res.getString("name"));  //1
                array2.add(String.valueOf(res.getInt("pid")));  //2
                array2.add(res.getString("father_name"));   //3
                array2.add(res.getString("gatetype"));  //4
                array2.add((res.getString("points")==null || res.getString("points").equals(""))?"null":res.getString("points"));   //5
//                array2.add(( res.getString("叶子节点发生概率")==null || res.getString("叶子节点发生概率").equals(""))?"null":res.getString("叶子节点发生概率"));  //6
                array2.add((res.getString("method")==null || res.getString("method").equals(""))?"null":res.getString("method"));   //6
                array2.add(( res.getString("threshold")==null || res.getString("threshold").equals(""))?"null":res.getString("threshold")); //7
                array2.add(String.valueOf(model_edition));
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

    //一直父节点寻找子节点
    public static ArrayList<ArrayList<String>> searchchild(ArrayList<ArrayList<String>> array1,String pid){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for(ArrayList<String> ary1:array1){
            if(ary1.get(3).equals(pid)){
                result.add(ary1);
            }
        }
        return result;
    }

    // 格式转换
    public static ArrayList<ArrayList<String>> change(ArrayList<ArrayList<String>> array1,String record_id){
        Map<String,String> ljmap = new HashMap<>();
        ljmap.put("+","或门");
        ljmap.put("0","与门");
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> midary = new ArrayList<>();
        String x=record_id.substring(2,3);
        for(ArrayList<String> ary1:array1){
            midary.add(x+ary1.get(0));  //id
            midary.add(ary1.get(1));    //name
            if(ary1.get(2).equals("0")){
                midary.add("ROOT");     //pid
                midary.add("null");     //father_name
            }else{
                midary.add(x+"g"+ary1.get(2));      //pid
                midary.add(ljmap.get(searchstr(array1,0,ary1.get(2)).get(0).get(4)));       //father_name
            }
            midary.add("N");        //nodetype
            midary.add("");         //gatetype
            if(!ary1.get(5).equals("null")){
                midary.add("1");        //isleaf
                midary.add(ary1.get(6).equals("null")?"":ary1.get(6));      //method
                midary.add(ary1.get(5).equals("null")?"":ary1.get(5));      //points
                midary.add(ary1.get(7).equals("null")?"":ary1.get(7));      //threshold
                midary.add(ary1.get(8));                                    //model_edition
                midary.add(ary1.get(9));                                    //freq
                result.add((ArrayList<String>) midary.clone());
                midary.clear();
            }else{
                midary.add("0");
                midary.add(ary1.get(6).equals("null")?"":ary1.get(6));
                midary.add(ary1.get(5).equals("null")?"":ary1.get(5));
                midary.add(ary1.get(7).equals("null")?"":ary1.get(7));
                midary.add(ary1.get(8));
                midary.add(ary1.get(9));
                result.add((ArrayList<String>) midary.clone());
                midary.clear();
                midary.add(x+"g"+ary1.get(0));      //id
                if(ary1.get(4).equals("+")){
                    midary.add("或门");              //name
                }else{
                    midary.add("与门");
                }
                midary.add(x+ary1.get(0));          //pid
                midary.add(ary1.get(1));            //father_name
                midary.add("G");                    //nodetype
                if(ary1.get(4).equals("+")){
                    midary.add("或门");               //gatetype
                }else{
                    midary.add("与门");
                }
                midary.add("0");                    //isleaf
                midary.add("");                     //method
                midary.add("");                     //points
                midary.add("");                     //threshold
                midary.add(ary1.get(8));            //model_edition
                midary.add("0");                     //freq
                result.add((ArrayList<String>) midary.clone());
                midary.clear();
            }
        }
        return result;
    }

    //递归生成
    public static JSONObject getObject(ArrayList<ArrayList<String>> array1,ArrayList<String> array2){
        JSONObject result = new JSONObject();
        JSONArray childs = new JSONArray();
        result.put("pageIndex",0);
        result.put("pageSize",-1);
        result.put("nodetype",array2.get(4));
        result.put("isleaf",array2.get(6));
        result.put("id",array2.get(0));
        result.put("name",array2.get(1));
        result.put("pid",array2.get(2));
        result.put("fatherName",array2.get(3));
        result.put("gatetype",array2.get(5));
        result.put("points",array2.get(8));
        result.put("method",array2.get(7));
        result.put("threshold",array2.get(9));
        result.put("freq",array2.get(11));
        result.put("modelEdition",array2.get(10));
        result.put("rootFlag","null");
        if(searchstr(array1,2,array2.get(0)).size()==0){
            result.put("children",childs);
        }else{
            for(ArrayList<String> ary2:searchstr(array1,2,array2.get(0))){
                childs.add(getObject(array1,ary2));
            }
            result.put("children",childs);
        }
        return result;
    }

    //综合部分程序
    public static JSONObject getFaultTreeList(String record_id){
        ArrayList<ArrayList<String>> ary1 = tree_show_get(record_id);
        ArrayList<ArrayList<String>> ary2 = change(ary1,record_id);
        JSONObject result = getObject(ary2,searchstr(ary2,2,"ROOT").get(0));
        return result;
    }


    public static void main(String[] args){
        ArrayList<ArrayList<String>> asd = tree_show_get("Bvo_1_1588146100");
        ArrayList<ArrayList<String>> asd1=change(asd,"Bvo_1_1588146100");
        for(ArrayList<String> as:asd1){
            System.out.println(as);
        }
        System.out.println(getFaultTreeList("Bvo_1_1588146100"));
//        String asd="1234567";
//        System.out.println(asd.substring(2,3));
    }

}
