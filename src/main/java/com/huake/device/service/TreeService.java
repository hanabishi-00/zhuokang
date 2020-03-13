package com.huake.device.service;

import com.huake.device.dao.generator.*;
import com.huake.device.domain.generator.*;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TreeService {

    @Resource
    private TreeDeviceMapper treeDeviceMapper;

    @Resource
    private TreeDiagUnitMapper treeDiagUnitMapper;

    @Resource
    private TreeDiagCommonMapper treeDiagCommonMapper;

    @Resource
    private TreeEvaCommonMapper treeEvaCommonMapper;

    @Resource
    private TreeEvaUnitMapper treeEvaUnitMapper;

    @Resource
    private TreeEvaTypeMapper treeEvaTypeMapper;

    @Resource
    private TreePreMeasureMapper treePreMeasureMapper;

    @Resource
    private TreePreStateMapper treePreStateMapper;

    @Resource
    private TreePreUnitMapper treePreUnitMapper;

    public List<TreeDevice> getTreeDeviceList(){
        List<TreeDevice> list = treeDeviceMapper.select(SelectDSLCompleter.allRows());
        return list;
    }

    public TreeDevice getTreeDevice(int id){
        Optional<TreeDevice> optional = treeDeviceMapper.selectByPrimaryKey(id);
        com.huake.device.domain.generator.TreeDevice treeDevice = optional.isPresent() ? optional.get() : null;
        return treeDevice;
    }

    public TreeEvaType getTreeEvaType(int id){
        Optional<TreeEvaType> optional = treeEvaTypeMapper.selectByPrimaryKey(id);
        com.huake.device.domain.generator.TreeEvaType treeEvaType = optional.isPresent() ? optional.get() : null;
        return treeEvaType;
    }

    public TreePreState getTreePreState(int id){
        Optional<TreePreState> optional = treePreStateMapper.selectByPrimaryKey(id);
        com.huake.device.domain.generator.TreePreState treePreState = optional.isPresent() ? optional.get() : null;
        return treePreState;
    }

    public TreePreMeasure getTreePreMeasure(int id){
        Optional<TreePreMeasure> optional = treePreMeasureMapper.selectByPrimaryKey(id);
        com.huake.device.domain.generator.TreePreMeasure treePreMeasure = optional.isPresent() ? optional.get() : null;
        return treePreMeasure;
    }

    public List<TreePreMeasure> getTreePreMeasureList(){
        List<TreePreMeasure> list = treePreMeasureMapper.select(SelectDSLCompleter.allRows());
        return list;
    }

//    public TreePreMeasure getTreePreMeasureByList(List<TreePreMeasure> ls){
//        Map<Integer,Object> map = new HashMap<>();
//        map = ls.stream().collect(Collectors.toMap(TreePreMeasure::getId,TreePreMeasure::getName));
//    }

    //public List<Object> GenerateTreeFormat(Object obj,)

    public Object getTreeDiagUnit(){
        List<TreeDiagUnit> list = treeDiagUnitMapper.select(SelectDSLCompleter.allRows());
        List<TreeDiagCommon> cmList = treeDiagCommonMapper.select(SelectDSLCompleter.allRows());
        List<Object> rst = new ArrayList<Object>();
        for (TreeDiagUnit treeDiagUnit : list)
        {
            Map<String,Object> treeDiagUnitMap = new HashMap<>();
            List<Object> lst = new ArrayList<Object>();
            String[] ls = treeDiagUnit.getDeviceId().split(",");
            for (String str:ls)
            {
                lst.add(getTreeDevice(Integer.parseInt(str)));
            }
            treeDiagUnitMap.put("id",treeDiagUnit.getUnitId());
            treeDiagUnitMap.put("name",treeDiagUnit.getUnitId().toString() + "#机组");
            treeDiagUnitMap.put("type","Unit");
            treeDiagUnitMap.put("children",lst);
            rst.add(treeDiagUnitMap);
        }

        for (TreeDiagCommon treeDiagCommon:cmList)
        {
            Map<String,Object> treeDiagCommonMap = new HashMap<>();
            List<Object> lst = new ArrayList<Object>();
            String[] ls = treeDiagCommon.getDeviceId().split(",");
            for (String str:ls)
            {
                lst.add(getTreeDevice(Integer.parseInt(str)));
            }
            treeDiagCommonMap.put("id",treeDiagCommon.getId());
            treeDiagCommonMap.put("name",treeDiagCommon.getId().toString() + "#机组");
            treeDiagCommonMap.put("type","Common");
            treeDiagCommonMap.put("children",lst);
            rst.add(treeDiagCommonMap);
        }
        return rst;
    }


    public Object getTreeEvaUnit(){
        List<TreeEvaUnit> list = treeEvaUnitMapper.select(SelectDSLCompleter.allRows());
        List<TreeEvaCommon> cmList = treeEvaCommonMapper.select(SelectDSLCompleter.allRows());
        List<Object> rst = new ArrayList<Object>();
        for (TreeEvaUnit treeEvaUnit : list)
        {
            Map<String,Object> treeEvaUnitMap = new HashMap<>();
            List<Object> lst = new ArrayList<Object>();
            String[] ls = treeEvaUnit.getDeviceId().split(",");
            for (String str:ls)
            {
                lst.add(getTreeDevice(Integer.parseInt(str)));
            }
            treeEvaUnitMap.put("id",treeEvaUnit.getUnitId());
            treeEvaUnitMap.put("name",treeEvaUnit.getUnitId().toString() + "#机组");
            treeEvaUnitMap.put("type","Unit");
            treeEvaUnitMap.put("DeviceType",getTreeEvaType(treeEvaUnit.getTypeId()).getName());
            treeEvaUnitMap.put("children",lst);
            rst.add(treeEvaUnitMap);
        }

        for (TreeEvaCommon treeEvaCommon:cmList)
        {
            Map<String,Object> treeEvaCommonMap = new HashMap<>();
            List<Object> lst = new ArrayList<Object>();
            String[] ls = treeEvaCommon.getDeviceId().split(",");
            for (String str:ls)
            {
                lst.add(getTreeDevice(Integer.parseInt(str)));
            }
            treeEvaCommonMap.put("id",treeEvaCommon.getUnitId());
            treeEvaCommonMap.put("name",treeEvaCommon.getUnitId().toString() + "#机组");
            treeEvaCommonMap.put("type","Common");
            treeEvaCommonMap.put("children",lst);
            rst.add(treeEvaCommonMap);
        }
        return rst;
    }

    public Object getTreePreUnit(){
        List<TreePreUnit> list = treePreUnitMapper.select(SelectDSLCompleter.allRows());
        List<TreePreMeasure> list1 = getTreePreMeasureList();
        Map<Integer,Object> map = new HashMap<>();
        map = list1.stream().collect(Collectors.toMap(TreePreMeasure::getId,TreePreMeasure::getName));
        List<Object> rst = new ArrayList<Object>();
        for (TreePreUnit treePreUnit : list)
        {
            Map<String,Object> treePreUnitMap = new HashMap<>();
            List<Object> lst = new ArrayList<Object>();
            String[] ls = treePreUnit.getMeasureId().split(",");
            for (String str:ls)
            {
                //lst.add(getTreePreMeasure(Integer.parseInt(str)));
                TreePreMeasure treePreMeasure = new TreePreMeasure();
                treePreMeasure.setId(Integer.parseInt(str));
                treePreMeasure.setName(map.get(Integer.parseInt(str)).toString());
                lst.add(treePreMeasure);
            }
            treePreUnitMap.put("id",treePreUnit.getUnitId());
            treePreUnitMap.put("name",treePreUnit.getUnitId().toString() + "#机组");
            treePreUnitMap.put("type","Unit");
            treePreUnitMap.put("state",getTreePreState(treePreUnit.getState()).getName());
            treePreUnitMap.put("device",getTreeDevice(Integer.parseInt(treePreUnit.getDeviceId())).getName());
            treePreUnitMap.put("children",lst);
            rst.add(treePreUnitMap);
        }

        return rst;
    }

}
