package com.huake.device.web;

import com.huake.device.domain.dto.HistoryEvent;
import com.huake.device.domain.dto.Redis;
import com.huake.device.domain.generator.Test;
import com.huake.device.domain.generator.TreAnaThreshold;
import com.huake.device.domain.generator.TreDeterThreshold;
import com.huake.device.service.IRedisService;
import com.huake.device.service.TestService;
import com.huake.device.service.TrendService;
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

import javax.annotation.Resource;
import java.util.Map;

/**
 * 测试服务
 */
@RestController
@Api(tags = "趋势分析")
@RequestMapping("/trend")
public class TrendController {
    @Resource
    private TrendService trendService;
    private final Log logger = LogFactory.getLog(TrendController.class);

    @RequestMapping(value = "/getTreDeterThresholdList", method = RequestMethod.GET)
    @ApiOperation("获取劣化预测阈值表")
    public Object getTreDeterThresholdList() {
        return ResponseUtil.ok(trendService.getTreDeterThresholdList());
    }

    @RequestMapping(value = "/updateTreDeterThreshold", method = RequestMethod.POST)
    @ApiOperation("修改劣化预测阈值")
    public Object updateTreDeterThreshold(@RequestBody TreDeterThreshold treDeterThreshold) {
        return ResponseUtil.ok(trendService.updateTreDeterThreshold(treDeterThreshold));
    }

    @RequestMapping(value = "/getTreAnaThresholdList", method = RequestMethod.GET)
    @ApiOperation("获取分析模型阈值表")
    public Object getTreAnaThresholdList() {
        return ResponseUtil.ok(trendService.getTreAnaThresholdList());
    }

    @RequestMapping(value = "/updateTreAnaThreshold", method = RequestMethod.POST)
    @ApiOperation("修改分析模型阈值")
    public Object updateTreAnaThreshold(@RequestBody TreAnaThreshold treAnaThreshold) {
        return ResponseUtil.ok(trendService.updateTreAnaThreshold(treAnaThreshold));
    }

    @RequestMapping(value = "/get24HWarnEventSummary", method = RequestMethod.GET)
    @ApiOperation("获取24小时内事件统计(报警/预警)")
    public Object get24HWarnEventSummary() {
        return ResponseUtil.ok(trendService.get24HWarnEventSummary());
    }

    @RequestMapping(value = "/get24HWarnEventList", method = RequestMethod.GET)
    @ApiOperation("获取24小时内事件列表(报警/预警)")
    public Object get24HWarnEventList() {
        return ResponseUtil.ok(trendService.get24HWarnEventList());
    }

    @RequestMapping(value = "/selectHistoryWarnEventList", method = RequestMethod.POST)
    @ApiOperation("查询历史事件列表")
    public Object selectHistoryWarnEventList(@RequestBody HistoryEvent historyEvent) {
        return ResponseUtil.ok(trendService.selectHistoryWarnEventList(historyEvent));
    }
}