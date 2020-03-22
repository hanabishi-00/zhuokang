package util;

import java.text.ParseException;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;

public class platformUtil {
    private static final String IP="http://118.178.136.233";
    private static final String PORT="80";
    private static final String USR="A01admin";
    private static final String PSW="123456";

    public static class Point {

        private String id;

        private String unit;

        private Long tm;

        private float val;

        private int flag;

        private int dataType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Long getTm() {
            return tm;
        }

        public void setTm(Long tm) {
            this.tm = tm;
        }

        public float getVal() {
            return val;
        }

        public void setVal(float val) {
            this.val = val;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getDataType() {
            return dataType;
        }

        public void setDataType(int dataType) {
            this.dataType = dataType;
        }
    }

    public static class Response {

        private String success;

        private String message;

        private List<Point> result;

        private Long timestamp;

        private int code;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Point> getResult() {
            return result;
        }

        public void setResult(List<Point> result) {
            this.result = result;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public static class Result {
        private List records;

        private int total;

        private int size;

        private  int current;

        private boolean searchCount;

        private int pages;

        public List getRecords() {
            return records;
        }

        public void setRecords(List records) {
            this.records = records;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }
    }




    public static void main(String[] args) throws ParseException {
        String token=getToken();
        List<Map> point_id=getID("1号机5号上导轴瓦发电机工况出油边温度Z119", token);
        int[] ids= new int[]{Integer.parseInt((String) point_id.get(0).get("id"))};
        Date[] time_gap=new Date[]{timeUtil.stringToDate("2018/10/30 00:00:00","yyyy/MM/dd HH:mm:ss"),
                timeUtil.stringToDate("2018/10/30 23:59:59","yyyy/MM/dd HH:mm:ss")};
        List<Point> res=getData(time_gap,ids);
        System.out.println("Finished!");
    }

    public static String getToken(){
        //拼接raw
        Map<String, Object> map = new HashMap();
        map.put("username", USR);
        map.put("password", PSW);
        String jsonString = JSON.toJSONString(map);
//发送请求,取得响应的json数据
        String body = HttpRequest
                .post(IP+":"+PORT+"/ecidi-cmp/sys/login",true)
                .header("Content-Type","application/json")
                .send(jsonString).body();
//将json转换成map
        map = JSON.parseObject(body, Map.class);
//取出token
        map = JSON.parseObject(map.get("result").toString(), Map.class);
        String token = map.get("token").toString();
        System.out.println(token);
        return token;
    }

    public static List<Map> getID(String idName, String token){
        int pageNo=1;
        int pageSize=1;
        //发送get请求获取相应信息,需要将id、pageNo、pageSize替换,token替换为对应token
        HttpRequest request = HttpRequest.get(IP+":"+PORT+"/ecidi-cmp/realtime_data/list",true,
                "name",idName,"pageNo",pageNo,"pageSize",pageSize)
                .header("X-Access-Token", token);
        String body = request.body();
//创建map存放解析的数据对象
        Map<String, Object> map = new HashMap();
        map = JSON.parseObject(body, Map.class);
//从result中解析出record的值
        String result = map.get("result").toString();
        Result results = JSON.parseObject(result, Result.class);
//从record的值中解析出name和id
        List<Map> list = new ArrayList<Map>();
        List records = results.getRecords();
        for(Object o:records) {
            String record = o.toString();
            Map<String, Object> recordMap = new HashMap();
            recordMap = JSON.parseObject(record, Map.class);
//new一个map用于存放结果
            Map<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("name", recordMap.get("name").toString());
            hashMap.put("id", recordMap.get("id").toString());
            hashMap.put("hdbId", recordMap.get("hdbId") == null ? " " :
                    recordMap.get("hdbId").toString());
//将值存放到list中
            list.add(hashMap);
        }
        return list;
    }

    public static List<Point> getData(Date[] time_gap, int[] ids){
        Long startTime=timeUtil.dateToLong(time_gap[0]);
        Long endTime=timeUtil.dateToLong(time_gap[1]);

        String ids_str="";
        for (int i=0;i<ids.length;i++){
            ids_str=ids_str+ids[i];
            if (i!=ids.length-1){
                ids_str=ids_str+",";
            }
        }

        //获取响应数据
        String body = HttpRequest.get(IP+":"+PORT+"/ecidi-cmp/his_data/query", true,
                "ids", ids_str, "starttime", startTime,"endtime",endTime).body();
//创建实体类用于存放结果
        Response response = JSON.parseObject(body, Response.class);
//将result中的测点历史数据存放到list中
        List<Point> pointList = response.getResult();

        return pointList;
    }
}
