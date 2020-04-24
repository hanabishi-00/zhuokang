package main.dao;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import main.util.DataBoolUtils;
import main.util.DataFloatUtils;


import java.util.*;

public class post_request {

    public static DataFloatUtils queFloat(String id, long endtime) {
        DataFloatUtils data = new DataFloatUtils(id);
        long interval = 86400;
        data = queFloat(id,endtime,interval);
        return data;
    }

    public static DataFloatUtils queFloat(String id, long endtime,long interval){
        DataFloatUtils data = new DataFloatUtils(id);
        long starttime = endtime-interval;
        String etime=String.valueOf(endtime*1000);
        String stime = String.valueOf(starttime*1000);
        String body1 = HttpRequest.get("http://118.178.136.233:80/ecidi-cmp/his_data/query1", true,
                "id",id,"starttime",stime,"endtime",etime
                ,"datatype",1,"interval",-1
        ).body();
        Response response = JSON.parseObject(body1, Response.class);
        List<Point> pointList = response.getResult();
        for(Point p1:pointList){
            data.addTime(p1.getTm());
            data.addValue(p1.getValue());
        }
        return data;
    }

    public static DataBoolUtils queBool(String id, long endtime) {
        DataBoolUtils data = new DataBoolUtils(id);
        long interval = 86400;
        data = queBool(id,endtime,interval);
        return data;
    }

        public static DataBoolUtils queBool(String id, long endtime,long interval){
        DataBoolUtils data = new DataBoolUtils(id);
        long starttime = endtime-interval;
        String etime=String.valueOf(endtime*1000);
        String stime = String.valueOf(starttime*1000);
        String body1 = HttpRequest.get("http://118.178.136.233:80/ecidi-cmp/his_data/query1", true,
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
        post_request c = new post_request();
        DataBoolUtils qwe= c.queBool("1609",1531450400);
        for (int b=0;b<qwe.getValue().size();b++){
            System.out.println(qwe.getValue().get(b)+":"+qwe.getTime().get(b));
        }

    }
}
