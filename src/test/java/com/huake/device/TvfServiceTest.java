package com.huake.device;

import com.huake.device.service.EvaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.huake.device.service.TvfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TvfServiceTest {
    @Autowired
    private TvfService tvfService;
    @Autowired
    private EvaService evaService;

    @Test
    public void test1() throws Exception {
        evaService.cal("{\"type\":\"tur\",\"unit\":\"1\"}");
    }
    @Test
    public void test2() throws Exception {
        evaService.click_cal("{\"type\":\"tur\",\"unit\":\"1\"}");
    }
    @Test
    public void test3() throws Exception {
        System.out.println("-----------"+evaService.load_res("{\"type\":\"tur\",\"unit\":\"1\"}"));
    }
    @Test
    public void test4() throws Exception {
        System.out.println("-----------"+
                evaService.timeRange_res("{\"type\":\"tur\",\"unit\":\"1\",\"time\":[\"2017-01-01 00:00:00\",\"2020-05-01 00:00:00\"]}"));
    }

}
