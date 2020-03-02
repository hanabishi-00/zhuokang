package com.huake.device.util;

import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();

    public static Integer next100() {
        return random.nextInt(100);
    }
}
