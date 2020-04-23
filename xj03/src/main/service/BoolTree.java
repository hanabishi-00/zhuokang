package main.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import main.util.DataBoolUtils;
import main.dao.post_request;
//import main.util.mysqlUtil;


public class BoolTree {

    public static boolean isinterval1(long down,long up,ArrayList<Long> array){
        boolean flag = false;
        for(long l1:array){
            if(l1>=down && l1<=up){
                flag=true;
                break;
            }
        }
        return flag;
    }

    public static boolean isinterval2(long down,ArrayList<Long> array){
        boolean flag = false;
        for(long l1:array){
            if(l1>=down){
                flag=true;
                break;
            }
        }
        return flag;
    }

    //bool型专用方法  返回测量量为"1"的比例  table为表名，id为测点ID，time为后期时间
//    public double BooleanTree (String table,String id, long time) throws ClassNotFoundException, SQLException {
        public double BooleanTree (String id,long time,String val) throws ClassNotFoundException, SQLException, ParseException {
        DataBoolUtils data = post_request.queBool(id,time);//获取boolean型数据
           /* DataUtils data = null;//获取boolean型数据
            try {
                data = (DataUtils) DaoTree.queBool(time,id);
            } catch (ParseException e) {
                e.printStackTrace();
            }*/

        //   ArrayList<Double> value = data.getValue();
            if(data.getValue().size()==0){
                return 0.0;
            }
            assert data != null;
            ArrayList<Integer> value = data.getValue();
//        System.out.println(value.size());
        int x = 0;   //故障次数
        int y = 0;   // 正常次数
        double p = 0;
        int val1;
        if(val.equals("null")){
            val1=1;
        }else{
            val1=Integer.parseInt(val);
        }
        for (Integer h : value) {
            if (h == val1) {
                x++;
            }
            if (h == 1-val1) {
                y++;
            }
        }
        if (x == 0 && y == 0){
            p=0.0;
        }else{
            p = ((double)x/(x+y));
        }
        return p;
    }


    //判断命令类子节点所用的方法
    public double BooltimeTree(ArrayList<String> id, long time){
        double p=0.0;
        int x = 0;
        int y = 0;
        ArrayList<ArrayList<Integer>> value1 = new ArrayList<>();
        ArrayList<ArrayList<Long>> time1 = new ArrayList<>();
        ArrayList<ArrayList<Long>> time2 = new ArrayList<>();
        for(int i=0;i<id.size();i++){
            ArrayList<Long> newtime = new ArrayList<>();
            DataBoolUtils data = post_request.queBool(id.get(i),time);
            for(int j=0;j<data.getValue().size();j++){
                if(data.getValue().get(j)==1){
                    newtime.add(data.getTime().get(j));
                }
            }
            time2.add((ArrayList<Long>) newtime.clone());
            value1.add(data.getValue());
            time1.add(data.getTime());
        }
        if(time2.get(0).size()==0){
            return 0.0;
        }else{
            for(int i=0;i<value1.get(0).size();i++){
                if(value1.get(0).get(i)==1 && i==value1.get(0).size()-1){
                    if(isinterval2(time1.get(0).get(i),time2.get(-1))){
                        y++;
                    }
                    else{
                        x++;
                    }
                }else if(value1.get(0).get(i)==1 && value1.get(0).get(i+1)==0){
                    if(isinterval1(time1.get(0).get(i),time1.get(0).get(i+1),time2.get(-1))){
                        y++;
                    }
                    else{
                        x++;
                    }
                }
            }
        }
        if(x+y==0){
            p=0.0;
        }else {
            p = (double) (x / (x + y));
        }
        return p;
    }
}
