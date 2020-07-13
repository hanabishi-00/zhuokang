package main.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;


import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.*;

//import static main.tool.diag_report.diag_id;

public class send_message {

    public static JSONArray post_msg(long time, String Uid, int kind){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        JSONArray result = new JSONArray();
        ArrayList<String> tname = new ArrayList<>();
        tname.add("Bvb");
        tname.add("Bvo");
        String record_id = tname.get(kind-1)+"_"+Uid+"_"+time;
        JSONArray njary = diag_report.diag_appear(record_id).getJSONArray("result");
        JSONArray rjary = diag_report.diag_reason(record_id).getJSONArray("result");
        for(int i=0;i<njary.size();i++){
            JSONObject mid = new JSONObject();
            mid.put("name",njary.getJSONObject(i).getString("name"));
            mid.put("equipName",Uid+"号机组球阀及其附属设备");
            mid.put("startTime",sd.format(new Date(time*1000)));
            mid.put("faultCause",rjary.getJSONObject(i).getString("reason"));
            mid.put("confidence",rjary.getJSONObject(i).getString("freq"));
            result.add(mid.clone());
            mid.clear();
        }
        return result;
    }




    public static void post_diag(JSONArray msg){
        String ip_port="23.49.171.53:8085/ecidi-cmp/jkpjBreakdown/jkpjBreakdown/add";;
        String jsonString = JSON.toJSONString(msg);
        String body = HttpRequest
                .post( "http://"+ip_port,true)
                .header("Content-Type","application/json")
                .send(jsonString).body();
    }





        public static void main(String[] args) throws IOException {
        System.out.println(post_msg(1588146100,"1",2));
        ;
    }
}
