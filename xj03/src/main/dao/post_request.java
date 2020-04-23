package main.dao;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import main.util.DataBoolUtils;
import main.util.DataFloatUtils;


import java.util.*;

public class post_request {
    public String  getToken(){

        Map<String,Object> map = new HashMap();
        map.put("username","alpha1234");
        map.put("password","159623gx");
        String jsonString = JSON.toJSONString(map);
        //发送请求,取得响应的json数据
        String body = HttpRequest.post("http://118.178.136.233:80/ecidi-cmp/sys/login",true)
                .header("Content-Type","application/json").send(jsonString).body();
        map = JSON.parseObject(body,Map.class);
//取出token
        map = JSON.parseObject(map.get("result").toString(), Map.class);
        String token = map.get("token").toString();
//        System.out.println(body);
//        String s = HttpRequest.get("http://118.178.136.233:80/ecidi-cmp/his_data/query", true, "ids", 19224, "timelag", 1).body();
//        System.out.println(s);
        return token;
    }
    public void QueryByName(){
//        HttpRequest request = HttpRequest.get("http://118.178.136.233:80/ecidi-cmp/realtime_data/list",true,
//                "pageSize",1)
//                .header("X-Access-Token", getToken());
//        String body = request.body();
////创建map存放解析的数据对象
//        Map<String, Object> map = new HashMap();
//        map = JSON.parseObject(body, Map.class);
////从result中解析出record的值
//        String result = map.get("result").toString();
//        Result results = JSON.parseObject(result, Result.class);
////从record的值中解析出name和id
//        List<Map> list = new ArrayList<Map>();
//        List records = results.getRecords();
//        for(Object o:records){
//            String record = o.toString();
//            Map<String, Object> recordMap = new HashMap();
//            recordMap = JSON.parseObject(record, Map.class);
////new一个map用于存放结果
//            Map<String, String> hashMap = new HashMap<String, String>();
//            hashMap.put("name", recordMap.get("name").toString());
//            hashMap.put("id", recordMap.get("id").toString());
//            hashMap.put("hdbId", recordMap.get("hdbId")==null?" ":
//                    recordMap.get("hdbId").toString());
////将值存放到list中
//            list.add(hashMap);
//        }
            String stime = "1513670000000";
            String etime = "1513671800000";

            String body1 = HttpRequest.get("http://118.178.136.233:80/ecidi-cmp/his_data/query1", true,
                    "id","312","starttime",stime,"endtime",etime
                    ,"datatype",1,"interval",-1
                    ).body();  //0是单精度，1是状态量
            System.out.println(body1);
//创建实体类用于存放结果
            Response response = JSON.parseObject(body1, Response.class);
//将result中的测点历史数据存放到list中
//            String qwer = "{'success':true,'message':'操作成功！','result':[{'value':20,'flag':5,'tm':1513670404},{'flag':2,'tm':1513670411,'value':0}],'timestamp':1585910474998,'code':0}";
//            Response response1 = JSON.parseObject(qwer, Response.class);
//            System.out.println(response1);
            List<Point> pointList = response.getResult();
            System.out.println(pointList.get(0).getValue());

//            System.out.println(pointList.size());

    }

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
//        if (pointList.size()==0){
//            data.addTime(endtime);
//            data.addValue(0);
//        }
            if (pointList.size()!=0) {
                for (Point p1 : pointList) {
                    data.addTime(p1.getTm());
                    data.addValue((int) p1.getValue());
                }
            }
        return data;
    }

//    public static Date addDay(Date date, int num) {
//        Calendar startDT = Calendar.getInstance();
//        startDT.setTime(date);
//        startDT.add(Calendar.DAY_OF_MONTH, num);
//        return startDT.getTime();
//    }

    public static void main(String[] args){
        post_request c = new post_request();
//        c.getToken();
        c.QueryByName();
        DataBoolUtils qwe= c.queBool("1609",1531450400);
        for (int b=0;b<qwe.getValue().size();b++){
            System.out.println(qwe.getValue().get(b)+":"+qwe.getTime().get(b));
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date=calendar.getTime();
//        if (date.before(new Date())) {
//            date = addDay(date, 1);
//        }
        System.out.println(date);



//        String qwer = "{'success':true,'message':'操作成功！','result':[{'value':20,'flag':5,'tm':1513670404},{'flag':2,'tm':1513670411,'value':0}],'timestamp':1585910474998,'code':0}";
//        Response1 response1 = JSON.parseObject(qwer, Response1.class);
//        System.out.println(response1);
//        List<Point1> pointList = response1.getResult();
//        System.out.println(pointList.get(0).getValue());
//        String qwe = "null";
//        System.out.println(qwe.equals("null"));
    }
}
