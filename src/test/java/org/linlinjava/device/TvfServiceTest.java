package org.linlinjava.device;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.device.dao.TvfMapper;
import org.linlinjava.device.domain.Tvf;
import org.linlinjava.device.service.TvfService;
import org.linlinjava.device.vo.SearchData;
import org.linlinjava.device.vo.SearchData2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TvfServiceTest {
    @Autowired
    private TvfService tvfService;

    @Test
    public void testS() throws IOException {
        String device = "";
        List<String> points = new ArrayList<>();
        points.add("aaa");
        List<String> tableList = new ArrayList<>();
        tableList.add("bool_826_2018_02");
        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochSecond(1517414400), ZoneId.systemDefault());
        LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochSecond(1517673600), ZoneId.systemDefault());

        SearchData searchData = tvfService.selectInS(tableList, points, start, end);

        List<String> xList = searchData.getxList();
        for(String x : xList){
            System.out.println(x);
        }

        List<SearchData2> yList = searchData.getyList();
        for(SearchData2 y : yList){
            for (Object d : y.getData()) {
                System.out.println(d);
            }
        }
    }

    @Test
    public void testM() throws IOException {
        String device = "";
        List<String> points = new ArrayList<>();
        points.add("aaa");
        List<String> tableList = new ArrayList<>();
        tableList.add("bool_826_2018_02");
        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochSecond(1517414400), ZoneId.systemDefault());
        LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochSecond(1517673600), ZoneId.systemDefault());

        SearchData searchData = tvfService.selectInM(tableList, points, start, end);

        List<String> xList = searchData.getxList();
        for(String x : xList){
            System.out.println(x);
        }

        List<SearchData2> yList = searchData.getyList();
        for(SearchData2 y : yList){
            for (Object d : y.getData()) {
                System.out.println(d);
            }
        }
    }


    @Test
    public void testH() throws IOException {
        String device = "";
        List<String> points = new ArrayList<>();
        points.add("aaa");
        List<String> tableList = new ArrayList<>();
        tableList.add("bool_826_2018_02");
        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochSecond(1517414400), ZoneId.systemDefault());
        LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochSecond(1517673600), ZoneId.systemDefault());

        SearchData searchData = tvfService.selectInH(tableList, points, start, end);

        List<String> xList = searchData.getxList();
        for(String x : xList){
            System.out.println(x);
        }

        List<SearchData2> yList = searchData.getyList();
        for(SearchData2 y : yList){
            for (Object d : y.getData()) {
                System.out.println(d);
            }
        }
    }

    @Test
    public void testD() throws IOException {
        String device = "";
        List<String> points = new ArrayList<>();
        points.add("aaa");
        List<String> tableList = new ArrayList<>();
        tableList.add("bool_826_2018_02");
        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochSecond(1517414400), ZoneId.systemDefault());
        LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochSecond(1517673600), ZoneId.systemDefault());

        SearchData searchData = tvfService.selectInD(tableList, points, start, end);

        List<String> xList = searchData.getxList();
        for(String x : xList){
            System.out.println(x);
        }

        List<SearchData2> yList = searchData.getyList();
        for(SearchData2 y : yList){
            for (Object d : y.getData()) {
                System.out.println(d);
            }
        }
    }
}
