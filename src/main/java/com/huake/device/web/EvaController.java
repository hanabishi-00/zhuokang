package com.huake.device.web;

import com.huake.device.service.TestService;
import com.huake.device.util.ResponseUtil;
import execution.eva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;

@RestController
@Api(tags = "健康评价")
@RequestMapping("/eva")
public class EvaController {
    @Resource
    private TestService testService;
    private final Log logger = LogFactory.getLog(EvaController.class);

    @RequestMapping(value = "/calc", method = RequestMethod.GET)
    @ApiOperation("每日后台运行模型")
    public Object cal(String jstr) throws Exception {
        eva.run_model(jstr);
        return ResponseUtil.ok("执行成功");
    }

    @RequestMapping(value = "/click_cal", method = RequestMethod.GET)
    @ApiOperation("点击“开始评价”按钮即时运行模型")
    public Object click_cal(String jstr) throws Exception {
        eva.click_run_model(jstr);
        return ResponseUtil.ok("执行成功");
    }

    // 载入设备评价页面时显示的最新结果
    @RequestMapping(value = "/load_res", method = RequestMethod.GET)
    @ApiOperation("载入设备评价页面时显示的最新结果")
    public Object load_res(String jstr) throws ParseException {
        return ResponseUtil.ok(eva.get_res(jstr));
    }

    @RequestMapping(value = "/timeRange_res", method = RequestMethod.GET)
    @ApiOperation("搜索时段内评价结果")
    public Object timeRange_res(String jstr) throws ParseException {
        return ResponseUtil.ok(eva.search_res(jstr));
    }

    @RequestMapping(value = "/load_model", method = RequestMethod.GET)
    @ApiOperation("进行模型编辑时载入模型")
    public Object load_model(String jstr){
        return ResponseUtil.ok(eva.get_model(jstr));
    }

    @RequestMapping(value = "/save_model", method = RequestMethod.GET)
    @ApiOperation("保存模型")
    public Object save_model(String jstr){
        eva.save_model(jstr);
        return ResponseUtil.ok("执行成功");
    }

    @RequestMapping(value = "/load_offres", method = RequestMethod.GET)
    @ApiOperation("点击“离线评价”后载入最新离线评价结果")
    public Object load_offres(String jstr){
        return ResponseUtil.ok(eva.get_off_res(jstr));
    }

    @RequestMapping(value = "/init_offres", method = RequestMethod.GET)
    @ApiOperation("新建模型后初始化离线评价结果")
    public Object init_offres(String jstr) throws ParseException {
        return ResponseUtil.ok(eva.get_init_off_res(jstr));
    }

    @RequestMapping(value = "/save_offres", method = RequestMethod.GET)
    @ApiOperation("保存修改后的离线评价结果")
    public Object save_offres(String jstr){
        eva.save_off_res(jstr);
        return ResponseUtil.ok("执行成功");
    }

    @RequestMapping(value = "/draw_info", method = RequestMethod.GET)
    @ApiOperation("根据一条计算规则及评价时间得到应查询的测点集及时段集")
    public Object draw_info(String jstr) throws ParseException {
        return ResponseUtil.ok(eva.get_draw_info(jstr));
    }

    @RequestMapping(value = "/rela_time", method = RequestMethod.GET)
    @ApiOperation("返回一条计算规则的查询时段")
    public Object rela_time(String jstr) throws ParseException {
        return ResponseUtil.ok(eva.get_rela_time(jstr));
    }

    @RequestMapping(value = "/rela_points", method = RequestMethod.GET)
    @ApiOperation("返回一条计算规则的相关测点及类型")
    public Object rela_points(String jstr){
        return ResponseUtil.ok(eva.get_rela_points(jstr));
    }

    @RequestMapping(value = "/point_tag", method = RequestMethod.GET)
    @ApiOperation("返回评价在线叶子节点编辑界面中左下角不同计算规则显示的文字标签")
    public Object point_tag(String jstr){
        return ResponseUtil.ok(eva.get_tag(jstr));
    }
}
