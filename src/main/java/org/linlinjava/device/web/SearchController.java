package org.linlinjava.device.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.device.dto.SearchQuery;
import org.linlinjava.device.service.TvfService;
import org.linlinjava.device.util.RandomUtil;
import org.linlinjava.device.util.ResponseUtil;
import org.linlinjava.device.vo.SearchData;
import org.linlinjava.device.vo.SearchData2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SearchController {
    private final Log logger = LogFactory.getLog(SearchController.class);
    @Autowired
    private TvfService tvfService;

    @RequestMapping("/search")
    public Object search(@RequestBody SearchQuery searchQuery) {
        logger.debug(searchQuery);

        String device = searchQuery.getDevice();
        List<String> points = searchQuery.getPoints();
        LocalDateTime start = searchQuery.getStart();
        LocalDateTime end = searchQuery.getEnd();
        String level = searchQuery.getLevel();

        List<String> tableList = getTables(device, points, start, end);
        SearchData searchData = null;
        if(level.equals("s")){
            searchData = tvfService.selectInS(tableList, points, start, end);
        }
        else if(level.equals("m")){
            searchData = tvfService.selectInM(tableList, points, start, end);
        }
        else if(level.equals("h")){
            searchData = tvfService.selectInH(tableList, points, start, end);
        }
        else if(level.equals("d")){
            searchData = tvfService.selectInD(tableList, points, start, end);
        }
        else {
            searchData = selectTest(device, points, start, end);
        }

        return ResponseUtil.ok(searchData);
    }

    private List<String> getTables(String device, List<String> points,LocalDateTime start, LocalDateTime end) {
        List<String> tableList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd HH:mm:ss");

        for(LocalDateTime s = start; !s.isAfter(end); s = s.plusMonths(1).withDayOfMonth(1)){
            String table = "bool_826_" + s.format(formatter).substring(0, 7);
            if(tvfService.tableExist(table)) {
                tableList.add(table);
            }
        }
        return tableList;
    }

    private SearchData selectTest(String device, List<String> points,LocalDateTime start, LocalDateTime end) {
        SearchData searchData = new SearchData();
        for(int i = 0; i < 100; i++) {
            searchData.getxList().add(String.valueOf(i));
        }

        for(String point : points){
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point);
            for(int i = 0; i < 100; i++){
                searchData2.getData().add(RandomUtil.next100());
            }
            searchData.getyList().add(searchData2);
        }
        return searchData;
    }

}