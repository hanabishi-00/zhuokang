package main.config;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class db2xml {
    static Connection con;
    static Statement sql;
    static ResultSet res;
    //Kind表示本体与油系统，1表示本体，2表示油系统
    public void saveFile(int Kind){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            //更改故障树节点的数据库
            con =DriverManager.getConnection("jdbc:mysql://rm-bp19iox2b2ef33bgevo.mysql.rds.aliyuncs.com:3306/hdy" +
                            "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
                    "huake","huake@123");
        }catch (SQLException e){
            e.printStackTrace();
        }
         Document doucment = DocumentHelper.createDocument();
        Element root = doucment.addElement("WorkFlow");
        ArrayList<ArrayList<String>> array1 = new ArrayList<>();



        try{
            sql=con.createStatement();
            ArrayList<String> sqls=new ArrayList<>();
            sqls.add("select * from diag_model_bvb"); //本体故障树信息
            sqls.add("select * from diag_model_bvo");  //油系统故障树信息
//            res=sql.executeQuery("select * from diag_model_bvb");
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
                Element ChildElement = root.addElement("WorkNode").addAttribute("ID", ary1.get(0));
                ChildElement.addAttribute("Name",ary1.get(1));
                ChildElement.addAttribute("fatherId", ary1.get(2));
                ChildElement.addAttribute("fatherName",ary1.get(3));
                ChildElement.addAttribute("children",newstr);
                ChildElement.addAttribute("gate",ary1.get(4));
                ChildElement.addAttribute("monitorId",ary1.get(5));
//                ChildElement.addAttribute("freq",ary1.get(6));
                ChildElement.addAttribute("judgment",ary1.get(6));
                ChildElement.addAttribute("threshold",ary1.get(7));
            }

            OutputFormat format = OutputFormat.createPrettyPrint();

            //存放文件路径
            ArrayList<String> tree_dir=new ArrayList<>();
            tree_dir.add("src/main/config/FT_test1.xml"); //本体故障树信息文件存储位置
            tree_dir.add("src/main/config/FT_test2.xml");  //油系统故障树信息文件存储位置
            File file = new File(tree_dir.get(Kind-1));
            XMLWriter writer = new XMLWriter(new FileOutputStream(file),format);
            writer.write(doucment);
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
//        long date1 = System.currentTimeMillis()/1000;
//        System.out.println(date1);
        db2xml c=new db2xml();
        c.saveFile(2);
    }

}
