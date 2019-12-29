package org.linlinjava.device;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.device.dao.TvfMapper;
import org.linlinjava.device.domain.Tvf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {
    @Autowired
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

}
