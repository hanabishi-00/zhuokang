package com.huake.device.web;

import com.huake.device.dto.SearchQuery;
import com.huake.device.service.TvfService;
import com.huake.device.util.RandomUtil;
import com.huake.device.vo.SearchData2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.huake.device.util.ResponseUtil;
import com.huake.device.vo.SearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/device")
public class SearchController {
    private final Log logger = LogFactory.getLog(SearchController.class);
    @Autowired
    private TvfService tvfService;

    // 测点查询
    @RequestMapping("/search")
    public Object search(@RequestBody SearchQuery searchQuery) {
        logger.debug(searchQuery);

        String device = searchQuery.getDevice();
        List<Map<String, String>> points = searchQuery.getPoints();

        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(searchQuery.getStart())), ZoneId.systemDefault());
        LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(searchQuery.getEnd())), ZoneId.systemDefault());
//        LocalDateTime start = searchQuery.getStart();
//        LocalDateTime end = searchQuery.getEnd();
        String level = searchQuery.getLevel();

        List<String> tableList = getTables(device, start, end);
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

    // 查询劣化趋势预测结果
    @RequestMapping("/getTreDeterpre101001")
    public Object getTreDeterpre101001(String preTime){
        return ResponseUtil.ok(tvfService.getTreDeterpre101001(preTime));
    }

    // 查询健康趋势分析
    @RequestMapping("/selectEvaResTurModel201912010100")
    public Object selectEvaResTurModel201912010100(@RequestBody Map<String, String> map){
        return ResponseUtil.ok(tvfService.selectEvaResTurModel201912010100(map.get("start").toString(), map.get("end").toString()));
    }

    private List<String> getTables(String device,LocalDateTime start, LocalDateTime end) {
        List<String> tableList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd HH:mm:ss");

        for(LocalDateTime s = start; !s.isAfter(end); s = s.plusMonths(1).withDayOfMonth(1)){
            String table = "_" + s.format(formatter).substring(0, 7);
            tableList.add(table);
        }
        return tableList;
    }

    private SearchData selectTest(String device, List<Map<String, String>> points,LocalDateTime start, LocalDateTime end) {
        SearchData searchData = new SearchData();
        for(int i = 0; i < 100; i++) {
            searchData.getxList().add(String.valueOf(i));
        }

        for(Map<String, String> point : points){
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point.get("label").toString());
            for(int i = 0; i < 100; i++){
                searchData2.getData().add(RandomUtil.next100());
            }
            searchData.getyList().add(searchData2);
        }
        return searchData;
    }

}