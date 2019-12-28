package org.linlinjava.device.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.device.util.ResponseUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SearchController {
    private final Log logger = LogFactory.getLog(SearchController.class);

    /**
     * 测试数据
     *
     * @return 测试数据
     */
    @RequestMapping("/search")
    public Object search() {
        return ResponseUtil.ok("hello world, this is demo service");
    }

}