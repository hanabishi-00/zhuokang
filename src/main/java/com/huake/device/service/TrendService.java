package com.huake.device.service;

import com.huake.device.dao.generator.*;
import com.huake.device.domain.generator.RenHeadenergy;
import com.huake.device.domain.generator.TreAnaThreshold;
import com.huake.device.domain.generator.TreDeterThreshold;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class TrendService {
    @Resource
    TreDeterThresholdMapper treDeterThresholdMapper;
    @Resource
    TreAnaThresholdMapper treAnaThresholdMapper;

    // 获取劣化预测阈值表
    public List<TreDeterThreshold> getTreDeterThresholdList() {
        List<TreDeterThreshold> list = treDeterThresholdMapper.select(SelectDSLCompleter.allRows());
        return list;
    }

    // 修改劣化预测阈值
    public int updateTreDeterThreshold(TreDeterThreshold record) {
        return treDeterThresholdMapper.update(c ->
                c.set(TreDeterThresholdDynamicSqlSupport.val).equalToWhenPresent(record::getVal)
                        .where(TreDeterThresholdDynamicSqlSupport.unit, isEqualTo(record::getUnit))
                        .and(TreDeterThresholdDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }

    // 获取分析模型阈值表
    public List<TreAnaThreshold> getTreAnaThresholdList() {
        List<TreAnaThreshold> list = treAnaThresholdMapper.select(SelectDSLCompleter.allRows());
        return list;
    }

    // 修改分析模型阈值
    public int updateTreAnaThreshold(TreAnaThreshold record) {
        return treAnaThresholdMapper.update(c ->
                c.set(TreAnaThresholdDynamicSqlSupport.val).equalToWhenPresent(record::getVal)
                        .where(TreAnaThresholdDynamicSqlSupport.unit, isEqualTo(record::getUnit))
                        .and(TreAnaThresholdDynamicSqlSupport.id, isEqualTo(record::getId))
        );
    }
}
