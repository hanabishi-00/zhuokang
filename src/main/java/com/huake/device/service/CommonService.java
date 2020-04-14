package com.huake.device.service;

import com.huake.device.dao.CommonMapper;
import com.huake.device.dao.generator.ClassifiedquerytreeDynamicSqlSupport;
import com.huake.device.dao.generator.ClassifiedquerytreeMapper;
import com.huake.device.dao.generator.RenHeadenergyMapper;
import com.huake.device.domain.generator.Classifiedquerytree;
import com.huake.device.domain.generator.RenHeadenergy;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class CommonService {
    @Resource
    RenHeadenergyMapper renHeadenergyMapper;
    @Resource
    ClassifiedquerytreeMapper classifiedquerytreeMapper;
    @Resource
    CommonMapper commonMapper;

    public List<RenHeadenergy> getRenHeadenergyList(){
        List<RenHeadenergy> list = renHeadenergyMapper.select(SelectDSLCompleter.allRows());
        return list;
    }

    public List<Classifiedquerytree> getClassifiedPointList(){
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            selectModelQueryExpressionDSL.where()
                    .and(ClassifiedquerytreeDynamicSqlSupport.sort, isNotEqualTo(0))
                    .orderBy(ClassifiedquerytreeDynamicSqlSupport.sort);
            return selectModelQueryExpressionDSL;
        };
        return classifiedquerytreeMapper.select(completer);
    }

    public Set<String> getPointFullname(int unit, List<String> points){
        List<String> list = commonMapper.getPointFullname(points);
        Set<String> listReplaced = new HashSet<String>();
        for(int i = 0; i < list.size(); i++){
            listReplaced.add(list.get(i).replace("X号机组", unit + "号机组"));
        }
        return listReplaced;
    }

    public List<String> getFloatList(Set<String> points){
        return commonMapper.getFloatList(points);
    }

    public List<String> getBoolList(Set<String> points){
        return commonMapper.getBoolList(points);
    }

}
