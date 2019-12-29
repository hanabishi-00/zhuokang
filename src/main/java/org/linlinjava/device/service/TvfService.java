package org.linlinjava.device.service;

import org.linlinjava.device.dao.TvfMapper;
import org.linlinjava.device.domain.Tvf;
import org.linlinjava.device.vo.SearchData;
import org.linlinjava.device.vo.SearchData2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TvfService {
    @Autowired
    private TvfMapper tvfMapper;

    public SearchData selectInS(String device, List<String> points, LocalDateTime start, LocalDateTime end) {
        SearchData searchData = new SearchData();

        java.time.Duration duration = java.time.Duration.between(start, end);
        long seconds = duration.getSeconds();
        for(int i = 0; i <= seconds; i++) {
            LocalDateTime dateTime = start.plusSeconds(1);
            searchData.getxList().add(dateTime.toString());
        }

        for(String point : points){
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point);

            long startS = start.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
            long endS = end.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
            String table = "bool_826_2018_02";
            List<Tvf> tvfList = tvfMapper.selectBetween(table, startS, endS);
            Map<Integer, Object> tvfMap = new HashMap<>();
            for(Tvf tvf : tvfList){
                tvfMap.put(tvf.getT(), tvf.getV());
            }

            for(int i = 0; i <= seconds; i++) {
                Object v = tvfMap.get(i);
                if(v != null){
                    searchData2.getData().add(v);
                }
                else{
                    searchData2.getData().add(null);
                }
            }

            searchData.getyList().add(searchData2);
        }

        return searchData;
    }

    public SearchData selectInM(String device, List<String> points, LocalDateTime start, LocalDateTime end) {
        SearchData searchData = new SearchData();

        java.time.Duration duration = java.time.Duration.between(start, end);
        long minutes = duration.toMinutes();
        for(int i = 0; i <= minutes; i++) {
            LocalDateTime dateTime = start.plusMinutes(i);
            searchData.getxList().add(dateTime.toString());
        }

        for(String point : points){
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point);

            for(int i = 0; i <= minutes; i++) {
                LocalDateTime s = start.plusMinutes(i);
                LocalDateTime e = start.plusMinutes(i+1);
                long startM = s.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
                long endM = e.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();

                String table = "bool_826_2018_02";
                Float v = tvfMapper.avgBetween(table, startM, endM);
                searchData2.getData().add(v);
            }

            searchData.getyList().add(searchData2);
        }

        return searchData;
    }

    public SearchData selectInH(String device, List<String> points, LocalDateTime start, LocalDateTime end) {
        SearchData searchData = new SearchData();

        java.time.Duration duration = java.time.Duration.between(start, end);
        long hours = duration.toHours();
        for(int i = 0; i <= hours; i++) {
            LocalDateTime dateTime = start.plusHours(i);
            searchData.getxList().add(dateTime.toString());
        }

        for(String point : points){
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point);

            for(int i = 0; i <= hours; i++) {
                LocalDateTime s = start.plusHours(i);
                LocalDateTime e = start.plusHours(i+1);
                long startH = s.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
                long endH = e.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();

                String table = "bool_826_2018_02";
                Float v = tvfMapper.avgBetween(table, startH, endH);
                searchData2.getData().add(v);
            }

            searchData.getyList().add(searchData2);
        }

        return searchData;
    }

    public SearchData selectInD(String device, List<String> points, LocalDateTime start, LocalDateTime end) {
        SearchData searchData = new SearchData();

        java.time.Duration duration = java.time.Duration.between(start, end);
        long days = duration.toDays();
        for(int i = 0; i <= days; i++) {
            LocalDateTime dateTime = start.plusDays(i);
            searchData.getxList().add(dateTime.toString());
        }

        for(String point : points){
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point);

            for(int i = 0; i <= days; i++) {
                LocalDateTime s = start.plusDays(i);
                LocalDateTime e = start.plusDays(i+1);
                long startD = s.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
                long endD = e.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();

                String table = "bool_826_2018_02";
                Float v = tvfMapper.avgBetween(table, startD, endD);
                searchData2.getData().add(v);
            }

            searchData.getyList().add(searchData2);
        }

        return searchData;
    }
}
