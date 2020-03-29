package com.huake.device.util;

import java.util.UUID;

public class CharUtil {
    // 获取UUID
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String fillTimestamp(String timestamp){
        return (Long.valueOf(timestamp) + 10000000000l + "").substring(1);
    }

}
