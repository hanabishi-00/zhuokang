package main.config;

import java.sql.*;
import java.util.ArrayList;

public class db2xml {
    static Connection con;
    static Statement sql;
    static ResultSet res;
    //Kind表示本体与油系统，1表示本体，2表示油系统
    public ArrayList<ArrayList<String>> saveFile(int Kind){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            //更改故障树节点的数据库
            con =DriverManager.getConnection("jdbc:mysql://rm-bp19iox2b2ef33bgevo.mysql.rds.aliyuncs.com:3306/hdy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","huake","huake@123");
//            con =DriverManager.getConnection("jdbc:mysql://localhost:3306/hdy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true","huake","huake@123");
        }catch (SQLException e){
            e.printStackTrace();
        }
        ArrayList<ArrayList<String>> array1 = new ArrayList<>();

        try{
            sql=con.createStatement();
            ArrayList<String> sqls=new ArrayList<>();
            sqls.add("select * from diag_model_bvb where model_edition = (select max(model_edition) from diag_model_bvb)"); //本体故障树信息
            sqls.add("select * from diag_model_bvo where model_edition = (select max(model_edition) from diag_model_bvo)");  //油系统故障树信息
            res=sql.executeQuery(sqls.get(Kind-1));

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
                array1.add((ArrayList<String>) array2.clone());
                array2.clear();

            }
            for (ArrayList<String> ary1:array1){
                String newstr = "";
                for (ArrayList<String> ary2:array1){
                    StringBuilder newstr1 = new StringBuilder(newstr);
                    if(ary2.get(3).equals(ary1.get(1))){
                        if(!newstr.equals("")){
                            newstr1.append(",");
                        }
                        newstr1.append(ary2.get(1));
                    }
                    newstr = newstr1.toString();
                }
                if (newstr.equals("")){
                    newstr="null";
                }
                ary1.add(newstr);
            }
            res.close();
            sql.close();
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return array1;

    }
    public static void main(String[] args){
        db2xml c=new db2xml();
        c.saveFile(2);
    }

}
