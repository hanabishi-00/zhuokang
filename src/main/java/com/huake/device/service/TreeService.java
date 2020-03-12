package com.huake.device.service;

import com.huake.device.dao.generator.TreeDeviceMapper;
import com.huake.device.dao.generator.TreeDiagUnitMapper;
import com.huake.device.domain.generator.TreeDevice;
import com.huake.device.domain.generator.TreeDiagUnit;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class TreeService {

    @Resource
    private TreeDeviceMapper treeDeviceMapper;

    @Resource
    private TreeDiagUnitMapper treeDiagUnitMapper;

    public List<TreeDevice> getDeviceList(){
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            selectModelQueryExpressionDSL.where();
            return selectModelQueryExpressionDSL;
        };
        List<TreeDevice> list = treeDeviceMapper.select(completer);
        return list;
    }

    public TreeDevice getDevice(int id){
        Optional<TreeDevice> optional = treeDeviceMapper.selectByPrimaryKey(id);
        com.huake.device.domain.generator.TreeDevice treeDevice = optional.isPresent() ? optional.get() : null;
        return treeDevice;
    }

    public Object getDiagUnit(){
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            selectModelQueryExpressionDSL.where();
            return selectModelQueryExpressionDSL;
        };
        List<TreeDiagUnit> list = treeDiagUnitMapper.select(completer);
        //Map<String,Map<String,Object>> treeDiagUnitMap = new HashMap<>();
        Map<String,List<Object>> treeDiagUnitMap = new HashMap<>();
        for (TreeDiagUnit treeDiagUnit : list)
        {
            //Map<String,Object> tempMap = new HashMap<>();
            List<Object> lst = new ArrayList<Object>();
            String[] ls = treeDiagUnit.getDeviceId().split(",");
            for (String str:ls)
            {
                //tempMap.put(str,getDevice(Integer.parseInt(str)));
                lst.add(getDevice(Integer.parseInt(str)));
            }
            treeDiagUnitMap.put(treeDiagUnit.getUnitId().toString() + "#机组",lst);

        }

        return treeDiagUnitMap;
    }
}
