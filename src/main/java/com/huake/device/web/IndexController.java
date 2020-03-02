package com.huake.device.web;

import com.huake.device.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试服务
 */
@RestController
@RequestMapping("/api/v1")
public class IndexController {
    private final Log logger = LogFactory.getLog(IndexController.class);

    /**
     * 测试数据
     *
     * @return 测试数据
     */
    @RequestMapping("/index")
    public Object index() {
        return ResponseUtil.ok("hello world, this is demo service");
    }

}