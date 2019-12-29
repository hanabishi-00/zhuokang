package org.linlinjava.device.service;

import org.linlinjava.device.dao.TvfMapper;
import org.linlinjava.device.domain.Tvf;
import org.linlinjava.device.vo.SearchData;
import org.linlinjava.device.vo.SearchData2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TvfService {
    @Resource
    private TvfMapper tvfMapper;

    public SearchData selectInS(List<String> tableList, List<Map<String, String>> points, LocalDateTime start, LocalDateTime end) {
        SearchData searchData = new SearchData();

        java.time.Duration duration = java.time.Duration.between(start, end);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        long seconds = duration.getSeconds();
        for(int i = 0; i <= seconds; i++) {
            LocalDateTime dateTime = start.plusSeconds(i);
            searchData.getxList().add(dateTime.format(formatter));
//            searchData.getxList().add(dateTime.toString());
        }

        if(tableList.isEmpty()){
            return searchData;
        }

        for(Map<String, String> point : points){
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point.get("label").toString());

            long startS = start.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
            long endS = end.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
            for(String table : tableList) {
                String _table = point.get("key").toString()+table;
                List<Tvf> tvfList = new ArrayList<Tvf>();
                if(tableExist(_table)) {
                    tvfList = tvfMapper.selectBetween(_table, startS, endS);
                }
                Map<String, Object> tvfMap = new HashMap<>();
                for (Tvf tvf : tvfList) {
                    tvfMap.put(tvf.getT().toString(), tvf.getV());
                }

                for (int i = 0; i <= seconds; i++) {
                    String s = searchData.getxList().get(i);
                    LocalDateTime d = LocalDateTime.parse(s, formatter);
                    long sec = d.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();

                    Object v = tvfMap.get(sec + "");
                    if (v != null) {
                        searchData2.getData().add(v);
                    } else {
                        searchData2.getData().add(null);
                    }
                }
            }

            searchData.getyList().add(searchData2);
        }

        return searchData;
    }

    public SearchData selectInM(List<String> tableList, List<Map<String, String>> points, LocalDateTime start, LocalDateTime end) {
        SearchData searchData = new SearchData();

        java.time.Duration duration = java.time.Duration.between(start, end);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        long minutes = duration.toMinutes();
        for(int i = 0; i <= minutes; i++) {
            LocalDateTime dateTime = start.plusMinutes(i);
            searchData.getxList().add(dateTime.format(formatter));
        }

        if(tableList.isEmpty()){
            return searchData;
        }

        for(Map<String, String> point : points){
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point.get("label").toString());

            long startS = start.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
            long endS = end.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
            for(String table : tableList) {

                String _table = point.get("key").toString()+table;
                List<Map> list = new ArrayList<Map>();
                if(tableExist(_table)) {
                    list = tvfMapper.avgBetweenInM(_table, startS, endS);
                }
                Map<Object, Object> minuteMap = new HashMap<>();
                for (Map map : list) {
                    String s = (String)map.get("minute");
                    LocalDateTime d = LocalDateTime.parse(s ,formatter);
                    long m = d.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
                    minuteMap.put(m + "", map.get("avg"));
                }

                for (int i = 0; i <= minutes; i++) {
                    String s = searchData.getxList().get(i);
                    LocalDateTime d = LocalDateTime.parse(s, formatter);
                    long min = d.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();

                    Object v = minuteMap.get(min + "");
                    if (v != null) {
                        searchData2.getData().add(v);
                    } else {
                        searchData2.getData().add(null);
                    }
                }
            }

            searchData.getyList().add(searchData2);
        }

        return searchData;
    }

    public SearchData selectInH(List<String> tableList, List<Map<String, String>> points, LocalDateTime start, LocalDateTime end) {
        SearchData searchData = new SearchData();

        java.time.Duration duration = java.time.Duration.between(start, end);
        long hours = duration.toHours();
        for(int i = 0; i <= hours; i++) {
            LocalDateTime dateTime = start.plusHours(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
            searchData.getxList().add(dateTime.format(formatter));
        }

        if(tableList.isEmpty()){
            return searchData;
        }

        for(Map<String, String> point : points){
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point.get("label").toString());

            for(String table : tableList) {

                for (int i = 0; i <= hours; i++) {
                    LocalDateTime s = start.plusHours(i);
                    LocalDateTime e = start.plusHours(i + 1);
                    long startH = s.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
                    long endH = e.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();

                    Float v = null;
                    String _table = point.get("key").toString()+table;
                    if(tableExist(_table)) {
                        v = tvfMapper.avgBetween(point.get("key").toString()+table, startH, endH);
                    }
                    searchData2.getData().add(v);
                }
            }
            searchData.getyList().add(searchData2);
        }

        return searchData;
    }

    public SearchData selectInD(List<String> tableList, List<Map<String, String>> points, LocalDateTime start, LocalDateTime end) {
        SearchData searchData = new SearchData();

        java.time.Duration duration = java.time.Duration.between(start, end);
        long days = duration.toDays();
        for(int i = 0; i <= days; i++) {
            LocalDateTime dateTime = start.plusDays(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            searchData.getxList().add(dateTime.format(formatter));
        }

        if(tableList.isEmpty()){
            return searchData;
        }

        for(Map<String, String> point : points){
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point.get("label").toString());

            for(String table : tableList) {
                for (int i = 0; i <= days; i++) {
                    LocalDateTime s = start.plusDays(i);
                    LocalDateTime e = start.plusDays(i + 1);
                    long startD = s.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
                    long endD = e.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();

                    Float v = null;
                    String _table = point.get("key").toString()+table;
                    if(tableExist(_table)) {
                        v = tvfMapper.avgBetween(point.get("key").toString()+table, startD, endD);
                    }
                    searchData2.getData().add(v);
                }
            }
            searchData.getyList().add(searchData2);
        }

        return searchData;
    }

    public boolean tableExist(String table) {
        return tvfMapper.exist(table) == 1;
    }
}
