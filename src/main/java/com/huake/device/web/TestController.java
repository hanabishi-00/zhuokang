package com.huake.device.web;

import com.huake.device.domain.generator.Test;
import com.huake.device.service.TestService;
import com.huake.device.util.MyException;
import com.huake.device.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试服务
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;
    private final Log logger = LogFactory.getLog(TestController.class);

    @RequestMapping("/demo")
    public Object demo() {
        return ResponseUtil.ok("hello world, this is demo service");
    }

    @RequestMapping("/myException")
    public Object myException() {
        throw new MyException(ResponseUtil.serious());
    }

    @RequestMapping("/getTest")
    public Object getTest(@RequestBody Test test) {
        return ResponseUtil.ok(testService.getTest(test.getName()));
    }

    @RequestMapping("/getTest2")
    public Object getTest2(String name) {
        return ResponseUtil.ok(testService.getTest(name));
    }

    @RequestMapping("/getTestList")
    public Object getTestList(@RequestBody Test test) {
        return ResponseUtil.ok(testService.getTestList(test));
    }


}