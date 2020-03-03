package com.huake.device.web;

import com.huake.device.domain.generator.Test;
import com.huake.device.service.TestService;
import com.huake.device.util.MyException;
import com.huake.device.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试服务
 */
@RestController
@Api(tags = "测试接口")
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;
    private final Log logger = LogFactory.getLog(TestController.class);

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    @ApiOperation("demo")
    public Object demo() {
        return ResponseUtil.ok("hello world, this is demo service");
    }

    @RequestMapping(value = "/myException", method = RequestMethod.GET)
    @ApiOperation("异常")
    public Object myException() {
        throw new MyException(ResponseUtil.serious());
    }

    @RequestMapping(value = "/getTest", method = RequestMethod.POST)
    @ApiOperation("获取")
    public Object getTest(@RequestBody Test test) {
        return ResponseUtil.ok(testService.getTest(test.getName()));
    }

    @RequestMapping(value = "/getTest2", method = RequestMethod.GET)
    @ApiOperation("获取2")
    public Object getTest2(String name) {
        return ResponseUtil.ok(testService.getTest(name));
    }

    @RequestMapping(value = "/getTestList", method = RequestMethod.POST)
    @ApiOperation("列表")
    public Object getTestList(@RequestBody Test test) {
        return ResponseUtil.ok(testService.getTestList(test));
    }

    @RequestMapping(value = "/updateTest", method = RequestMethod.POST)
    @ApiOperation("修改")
    public Object updateTest(@RequestBody Test test) {
        return ResponseUtil.ok(testService.updateTest(test));
    }

    @RequestMapping(value = "/insertTest", method = RequestMethod.POST)
    @ApiOperation("添加")
    public Object insertTest(@RequestBody Test test) {
        return ResponseUtil.ok(testService.insertTest(test));
    }

    @RequestMapping(value = "/deleteTest", method = RequestMethod.GET)
    @ApiOperation("删除")
    public Object deleteTest(String name) {
        return ResponseUtil.ok(testService.deleteTest(name));
    }


}