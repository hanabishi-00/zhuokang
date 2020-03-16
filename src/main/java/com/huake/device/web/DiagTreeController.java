package com.huake.device.web;

import com.huake.device.domain.generator.DiagTree;
import com.huake.device.service.DiagTreeService;
import com.huake.device.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@Api(tags = "DiagTree")
@RequestMapping("/diagTree")
public class DiagTreeController {

    @Autowired
    private DiagTreeService diagTreeService;

    @GetMapping("/getDiagTree")
    @ApiOperation("getDiagTree")
    public  Object getDiagTree(String id)
    {
        if (id.isEmpty())
            return  ResponseUtil.fail(-1,"id不能为空！");
        return ResponseUtil.ok(diagTreeService.getDiagTree(id,null,null));
    }

    @PostMapping("/addDiagTree")
    @ApiOperation("addDiagTree")
    public  Object addDiagTree(String id,String name,String des)
    {
        if (id.isEmpty()||name.isEmpty())
            return  ResponseUtil.fail(-1,"id、name不能为空！");
        return ResponseUtil.ok(diagTreeService.addDiagTree(id,name,des));
    }

    @PostMapping("/modifyDiagTreeNode")
    @ApiOperation("modifyDiagTreeNode")
    public  Object modifyDiagTreeNode(@RequestBody DiagTree diagTree)
    {
        if (diagTree.getId().isEmpty()||diagTree.getName().isEmpty())
            return  ResponseUtil.fail(-1,"id、name不能为空！");
        return ResponseUtil.ok(diagTreeService.modifyDiagTreeNode(diagTree));
    }

    @PostMapping("/addDiagTreeChildrenNode")
    @ApiOperation("addDiagTreeChildrenNode")
    public Object addDiagTreeChildrenNode(@RequestBody DiagTree diagTree)
    {
        if (diagTree.getPid().isEmpty()||diagTree.getName().isEmpty())
            return  ResponseUtil.fail(-1,"pid、name不能为空！");
        return ResponseUtil.ok(diagTreeService.addDiagTreeChildrenNode(diagTree));
    }

    @PostMapping("/addDiagTreeBrotherNode")
    @ApiOperation("addDiagTreeBrotherNode")
    public Object addDiagTreeBrotherNode(@RequestBody DiagTree diagTree)
    {
        if (diagTree.getId().isEmpty()||diagTree.getName().isEmpty())
            return  ResponseUtil.fail(-1,"id、name不能为空！");
        return ResponseUtil.ok(diagTreeService.addDiagTreeBrotherNode(diagTree));
    }

    @GetMapping("/deleteDiagTreeNode")
    @ApiOperation("deleteDiagTreeNode")
    public  Object deleteDiagTreeNode(String id)
    {
        if (id.isEmpty())
            return  ResponseUtil.fail(-1,"id不能为空！");
        return ResponseUtil.ok(diagTreeService.deleteDiagTreeNode(id));
    }

    @GetMapping("/getDiagTreeRootNodes")
    @ApiOperation("getDiagTreeRootNodes")
    public  Object getDiagTreeRootNodes()
    {
        return  ResponseUtil.ok(diagTreeService.getDiagTreeRootNodes());
    }

}
