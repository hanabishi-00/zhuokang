package main.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import main.util.DataBoolUtils;
import main.util.DataFloatUtils;


import java.util.*;

public class post_request {

    public static DataFloatUtils queFloat(String id, long endtime) {
        id=id.trim();
        DataFloatUtils data = new DataFloatUtils(id);
        long interval = 86400;
        data = queFloat(id,endtime,interval);
        return data;
    }

    public static DataFloatUtils queFloat(String id, long endtime,long interval){
        id=id.trim();
        DataFloatUtils data = new DataFloatUtils(id);
        long starttime = endtime-interval;
        String etime=String.valueOf(endtime*1000);
        String stime = String.valueOf(starttime*1000);
        String body1 = HttpRequest.get("http://47.111.161.164:8081/ecidi-cmp/his_data/query1", true,
                "id",id,"starttime",stime,"endtime",etime
                ,"datatype",0,"interval",-1
        ).body();
//        System.out.println(body1);
        Response response = JSON.parseObject(body1, Response.class);

        List<Point> pointList = response.getResult();


        for(Point p1:pointList){
            data.addTime(p1.getTm());
            data.addValue(p1.getValue());
        }
        return data;
    }

    public static DataBoolUtils queBool(String id, long endtime) {
        id=id.trim();
        DataBoolUtils data = new DataBoolUtils(id);
        long interval = 86400;
        data = queBool(id,endtime,interval);
        return data;
    }

        public static DataBoolUtils queBool(String id, long endtime,long interval){
        id=id.trim();
        DataBoolUtils data = new DataBoolUtils(id);
        long starttime = endtime-interval;
        String etime=String.valueOf(endtime*1000);
        String stime = String.valueOf(starttime*1000);
        String body1 = HttpRequest.get("http://47.111.161.164:8081/ecidi-cmp/his_data/query1", true,
                "id",id,"starttime",stime,"endtime",etime
                ,"datatype",1,"interval",-1
        ).body();

            Response response = JSON.parseObject(body1, Response.class);
            List<Point> pointList = response.getResult();


            if (pointList.size()!=0) {
                for (Point p1 : pointList) {
                    data.addTime(p1.getTm());
                    data.addValue((int) p1.getValue());
                }
            }
        return data;
    }

    public static void main(String[] args){
        long date1 = System.currentTimeMillis()/1000;
        post_request c = new post_request();

        DataBoolUtils qwe= c.queBool("819",1513670197);
//        DataFloatUtils asd = c.queFloat("16",1533670197);
        System.out.println("停机：");
        for (int b=0;b<qwe.getValue().size();b++){
            System.out.println("值："+qwe.getValue().get(b)+"；时间戳："+qwe.getTime().get(b));
        }

        String body1 = HttpRequest.get("http://47.111.161.164:8081/ecidi-cmp/his_data/query2", true,
                "id","1601","starttime","1513583797000","endtime","1513670197000"
                ,"datatype",1,"datalag",1
        ).body();
        System.out.println(body1);

        DataBoolUtils qwe1= c.queBool("827",1513670197);
        DataFloatUtils asd = c.queFloat("762",1533670197);
        for (int b=0;b<asd.getValue().size();b++){
            System.out.println("值："+asd.getValue().get(b)+"；时间戳："+asd.getTime().get(b));
        }
        System.out.println("调相：");
        for (int b=0;b<qwe1.getValue().size();b++){
            System.out.println("值："+qwe1.getValue().get(b)+"；时间戳："+qwe1.getTime().get(b));
        }

    }
}
