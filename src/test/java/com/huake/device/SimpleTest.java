package com.huake.device;

import com.huake.device.util.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.huake.device.dao.TvfMapper;
import com.huake.device.domain.Tvf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {
    @Resource
    private TvfMapper tvfMapper;

    @Test
    public void test0() throws IOException {
        List<Tvf> tvfList = tvfMapper.select("bool_826_2018_02");
        System.out.println(tvfList.size());

        for(Tvf tvf : tvfList){
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(tvf.getT()), ZoneId.systemDefault());
            System.out.println(localDateTime);
        }
    }

    @Test
    public void testBadTable() throws IOException {
        int exist = tvfMapper.exist("xxxxx");
        System.out.println(exist);
        exist = tvfMapper.exist("bool_826_2018_02");
        System.out.println(exist);
    }

    @Test
    public void test1() throws IOException {
        List<Tvf> tvfList = tvfMapper.selectBetween("bool_826_2018_02", 1517414400, 1517673600);
        System.out.println(tvfList.size());

        Float avg = tvfMapper.avgBetween("bool_826_2018_02", 1517414400, 1517673600);
        System.out.println(avg);
    }

    @Test
    public void test2() throws IOException {
        long seconds = 1517414400;
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneId.systemDefault());
        System.out.println(localDateTime);
    }

    @Test
    public void test3() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 02, 01, 00, 00);
        long seconds = localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
        System.out.println(seconds);
    }

    @Test
    public void test4() throws IOException {
        List<Tvf> tvfList = tvfMapper.selectBetween("bool_826_2018_02", 1517414400, 1517673600);
        for(Tvf tvf : tvfList){
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(tvf.getT()), ZoneId.systemDefault());
            System.out.println(localDateTime + " " + tvf.getV());
        }
    }

    @Test
    public void test5() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.of(2019, 12, 29, 00, 00);
        LocalDateTime end = LocalDateTime.of(2020, 02, 01, 00, 00);

        for(LocalDateTime s = start; !s.isAfter(end); s = s.plusMonths(1).withDayOfMonth(1)){
            String str = s.format(formatter);
            System.out.println(str.substring(0, 7));
        }
    }


    @Test
    public void test6() throws IOException {
        System.out.println(CommonUtil.getUUID());
    }
}
