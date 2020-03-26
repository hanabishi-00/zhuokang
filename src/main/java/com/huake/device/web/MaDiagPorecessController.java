package com.huake.device.web;

import com.huake.device.service.MaDiagProcessService;
import com.huake.device.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "简报对比")
@RequestMapping("maDiagProcess")
public class MaDiagPorecessController {

    @Autowired
    private MaDiagProcessService maDiagProcessService;

    //GET
    //表：标准流程表(madiag_stan_process)
    //⼊参：机组（可选）
    //返回：[{机组、设备流程名称}]
    //备注：Group by 机组、设备流程名称
    @GetMapping("/getEquipList")
    @ApiOperation("getEquipList")
    public Object getEquipList(Integer uid) {
        return ResponseUtil.ok(maDiagProcessService.getEquipList(uid));
    }

    //GET
    //表：标准流程表(madiag_stan_process)
    //⼊参：机组（必填）、设备流程名称（必填）
    //返回：数组
    @GetMapping("/getStdProcess")
    @ApiOperation("getStdProcess")
    public Object getStdProcess(Integer uid, String equip_pro) {
        return ResponseUtil.ok(maDiagProcessService.getStdProcess(uid, equip_pro));
    }

    //GET
    //表：历史流程表(madiag_his_process)
    //⼊参：机组（必填）、设备流程名称（必填）、时间范围（必填）
    //返回：数组
    //备注：⼊参的时间格式后端来定
    @GetMapping("/queryHisProcess")
    @ApiOperation("queryHisProcess")
    public Object queryHisProcess(Integer uid, String equip_pro, String stime, String etime) {
        return ResponseUtil.ok(maDiagProcessService.queryHisProcess(uid, equip_pro, stime, etime));
    }

    //GET
    //表：单次历史流程表(madiag_sing_his)
    //⼊参：id（必填）
    //返回：数组
    //备注：单次历史流程表.his_id = 历史流程表.id
    @GetMapping("/viewHisProcess")
    @ApiOperation("viewHisProcess")
    public Object viewHisProcess(Integer id) {
        return ResponseUtil.ok(maDiagProcessService.viewHisProcess(id));
    }

    //GET
    //表：对⽐结果表(madiag_con_res)
    //⼊参：id（必填）
    //返回：数组
    //备注：对⽐结果表.id = 历史流程表.id
    @GetMapping("/viewConRes")
    @ApiOperation("viewConRes")
    public Object viewConRes(Integer id) {
        return ResponseUtil.ok(maDiagProcessService.viewConRes(id));
    }

}
