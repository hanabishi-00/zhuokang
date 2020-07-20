package main.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;


public class send_message_example {
    public static void post_diag_example() {
        JSONArray result1=new JSONArray();
        JSONObject result = new JSONObject();
        String ip_port = "192.168.31.216:8087/ecidi-cmp/jkpjBreakdown/jkpjBreakdown/add";
        result.put("startTime", "2020-07-01 00:00:00");
        result.put("equipName", 1 + "号水轮机及其附属设备");
        result.put("faultCause", "错误2");
        result.put("name", "油路漏油");
        result.put("confidence","0.5");
        result1.add(result);
        String jsonString = JSON.toJSONString(result1);

        String body = HttpRequest
                .post("http://" + ip_port, true)
                .header("Content-Type", "application/json")
                .send(jsonString).body();
    }

    public static void main(String[] args){
        post_diag_example();
    }
}
