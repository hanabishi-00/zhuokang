package org.linlinjava.device.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.device.dto.SearchQuery;
import org.linlinjava.device.util.JacksonUtil;
import org.linlinjava.device.util.RandomUtil;
import org.linlinjava.device.util.ResponseUtil;
import org.linlinjava.device.vo.SearchData;
import org.linlinjava.device.vo.SearchData2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1")
public class SearchController {
    private final Log logger = LogFactory.getLog(SearchController.class);

    @RequestMapping("/search")
    public Object search(@RequestBody SearchQuery searchQuery) {
        logger.debug(searchQuery);

        SearchData searchData = new SearchData();
        for(int i = 0; i < 100; i++) {
            searchData.getxList().add(String.valueOf(i));
        }

        List<String> points = searchQuery.getPoints();
        for(String point : points){
            SearchData2 searchData2 = new SearchData2();
            searchData2.setLabel(point);
            for(int i = 0; i < 100; i++){
                searchData2.getValue().add(RandomUtil.next100());
            }
            searchData.getyList().add(searchData2);
        }
        return ResponseUtil.ok(searchData);
    }

}