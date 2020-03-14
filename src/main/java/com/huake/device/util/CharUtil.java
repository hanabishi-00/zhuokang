package com.huake.device.util;

import java.util.UUID;

public class CharUtil {
    // 获取UUID
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
