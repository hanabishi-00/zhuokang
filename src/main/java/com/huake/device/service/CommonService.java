package com.huake.device.service;

import com.huake.device.dao.generator.ClassifiedquerytreeDynamicSqlSupport;
import com.huake.device.dao.generator.ClassifiedquerytreeMapper;
import com.huake.device.dao.generator.RenHeadenergyMapper;
import com.huake.device.domain.generator.Classifiedquerytree;
import com.huake.device.domain.generator.RenHeadenergy;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class CommonService {
    @Resource
    RenHeadenergyMapper renHeadenergyMapper;
    @Resource
    ClassifiedquerytreeMapper classifiedquerytreeMapper;

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

}
