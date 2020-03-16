package com.huake.device.web;

import com.huake.device.domain.generator.TreeDevice;
import com.huake.device.domain.generator.TreeDiagUnit;
import com.huake.device.service.TreeService;
import com.huake.device.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Tree")
@RequestMapping("/tree")
public class TreeController {
    private final Log logger = LogFactory.getLog(SearchController.class);
    @Autowired
    private TreeService treeService;

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    @ApiOperation("demo")
    public Object demo() {
        return ResponseUtil.ok("hello world, this is demo service");
    }

    @RequestMapping(value = "/getTreeDeviceList", method = RequestMethod.GET)
    @ApiOperation("getTreeDeviceList")
    public Object getDeviceList() {
        return ResponseUtil.ok(treeService.getTreeDeviceList());
    }

    @RequestMapping(value = "/getTreeDevice", method = RequestMethod.GET)
    @ApiOperation("getTreeDevice")
    public Object getDevice(int id) {
        return ResponseUtil.ok(treeService.getTreeDevice(id));
    }

    @RequestMapping(value = "/getTreeDiagUnit", method = RequestMethod.GET)
    @ApiOperation("getTreeDiagUnit")
    public Object getDiagUnit() {
        return ResponseUtil.ok(treeService.getTreeDiagUnit());
    }

    @RequestMapping(value = "/getTreeEvaUnit", method = RequestMethod.GET)
    @ApiOperation("getTreeEvaUnit")
    public Object getTreeEvaUnit() {
        return ResponseUtil.ok(treeService.getTreeEvaUnit());
    }

    @RequestMapping(value = "/getTreePreUnit", method = RequestMethod.GET)
    @ApiOperation("getTreePreUnit")
    public Object getTreePreUnit() {
        return ResponseUtil.ok(treeService.getTreePreUnit());
    }
}
