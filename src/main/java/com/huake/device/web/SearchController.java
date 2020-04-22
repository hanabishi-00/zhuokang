package com.huake.device.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huake.device.domain.dto.SearchQuery;
import com.huake.device.domain.generator.Classifiedquerytree;
import com.huake.device.domain.generator.Hdbid;
import com.huake.device.service.CommonService;
import com.huake.device.service.TvfService;
import com.huake.device.util.RandomUtil;
import com.huake.device.domain.vo.SearchData2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.huake.device.util.ResponseUtil;
import com.huake.device.domain.vo.SearchData;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@Api(tags = "查询接口")
@RequestMapping("/search")
public class SearchController {
    private final Log logger = LogFactory.getLog(SearchController.class);
    @Resource
    private TvfService tvfService;

    @Resource
    private CommonService commonService;

    // 测点查询
    @RequestMapping(value = "/getPoint", method = RequestMethod.POST)
    @ApiOperation("测点查询")
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
        if (level.equals("s")) {
            searchData = tvfService.selectInS(tableList, points, start, end);
        } else if (level.equals("m")) {
            searchData = tvfService.selectInM(tableList, points, start, end);
        } else if (level.equals("h")) {
            searchData = tvfService.selectInH(tableList, points, start, end);
        } else if (level.equals("d")) {
            searchData = tvfService.selectInD(tableList, points, start, end);
        } else {
            searchData = selectTest(device, points, start, end);
        }

        return ResponseUtil.ok(searchData);
    }

    // 查询劣化趋势预测结果
    @RequestMapping(value = "/getTreDeterpre101001", method = RequestMethod.GET)
    @ApiOperation("查询劣化趋势预测结果")
    public Object getTreDeterpre101001(String preTime) {
        return ResponseUtil.ok(tvfService.getTreDeterpre101001(preTime));
    }

    // 查询健康趋势分析
    @RequestMapping(value = "/selectEvaResTurModel201912010100", method = RequestMethod.POST)
    @ApiOperation("查询健康趋势分析")
    public Object selectEvaResTurModel201912010100(@RequestBody Map<String, String> map) {
        return ResponseUtil.ok(tvfService.selectEvaResTurModel201912010100(map.get("start").toString(), map.get("end").toString()));
    }

    // 获取发电量规划数据
    @RequestMapping(value = "/getRenHeadenergyList", method = RequestMethod.GET)
    @ApiOperation("获取发电量规划数据")
    public Object getRenHeadenergyList() {
        return ResponseUtil.ok(commonService.getRenHeadenergyList());
    }

    // 获取测点列表（用于分组）
    @RequestMapping(value = "/getClassifiedPointList", method = RequestMethod.GET)
    @ApiOperation("获取测点列表（用于分组）")
    public Object getClassifiedPointList() {
        List<Classifiedquerytree> list = commonService.getClassifiedPointList();
        JSONArray array = new JSONArray();
        JSONObject map = new JSONObject();
        for (Classifiedquerytree classifiedquerytree : list) {
            JSONObject item = new JSONObject();
            item.put("key", classifiedquerytree.getId());
            item.put("label", classifiedquerytree.getName());
            if(classifiedquerytree.getIsParentid() == 1){
                item.put("children", new JSONArray());
            }
            map.put(classifiedquerytree.getId().toString(), item);
            if(classifiedquerytree.getSort() == 1){
                array.add(map.get(classifiedquerytree.getId().toString()));
            } else {
                map.getJSONObject(classifiedquerytree.getParentId().toString()).getJSONArray("children").add(item);
            }
        }
        return ResponseUtil.ok(array);
    }

    // 获取测点完整名称
    @RequestMapping(value = "/getPointIdByName", method = RequestMethod.GET)
    @ApiOperation("根据机组和名称获取测点id")
    public Object getPointIdByName(int unit, String points) {
        String[] ss = points.split(",");
        List<String> pointList = new ArrayList<String>();
        for(int i = 0; i < ss.length; i++){
            pointList.add(ss[i]);
        }
        // 根据机组和测点名称，获取测点全称
        Set<String> list = commonService.getPointFullname(unit, pointList);
        Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();
        map.put("float", commonService.getFloatList(list));// 获取测点id
        map.put("bool", commonService.getBoolList(list));// 获取测点id
        return ResponseUtil.ok(map);
    }

    // 获取所有测点列表
    @RequestMapping(value = "/getPointList", method = RequestMethod.GET)
    @ApiOperation("获取所有测点列表")
    public Object getPointList() {
        List<Hdbid> list = commonService.getPointList();
        return ResponseUtil.ok(list);
    }

    // 获取测点名称以及hdbId
    @RequestMapping(value = "/getPointFullName", method = RequestMethod.POST)
    @ApiOperation("获取测点名称以及hdbId")
    public Object getPointFullName(@RequestBody SearchQuery searchQuery) {
        logger.debug(searchQuery);

        String device = searchQuery.getDevice();
        List<Map<String, String>> points = searchQuery.getPoints();
        List<Hdbid> list = new ArrayList<>();
        for (Map<String, String> map : points){
            String prefix = this.commonService.getPointFullName(map.get("key"));
            if(prefix.contains("X")){
                prefix = prefix.replace("X", searchQuery.getDevice());
            }
            prefix = prefix+map.get("label");
            list.add(commonService.getPointListByName(prefix));
        }
        return ResponseUtil.ok(list);
    }


    private List<String> getTables(String device, LocalDateTime start, LocalDateTime end) {
        List<String> tableList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd HH:mm:ss");

        for (LocalDateTime s = start; !s.isAfter(end); s = s.plusMonths(1).withDayOfMonth(1)) {
            String table = "_" + s.format(formatter).substring(0, 7);
            tableList.add(table);
        }
        return tableList;
    }

    private SearchData selectTest(String device, List<Map<String, String>> points, LocalDateTime start, LocalDateTime end) {
        SearchData searchData = new SearchData();
        for (int i = 0; i < 100; i++) {
            searchData.getxList().add(String.valueOf(i));
        }

        for (Map<String, String> point : points) {
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point.get("label").toString());
            for (int i = 0; i < 100; i++) {
                searchData2.getData().add(RandomUtil.next100());
            }
            searchData.getyList().add(searchData2);
        }
        return searchData;
    }

}