package com.huake.device.service;

import com.huake.device.dao.CommonMapper;
import com.huake.device.dao.generator.*;
import com.huake.device.domain.dto.HistoryEvent;
import com.huake.device.domain.generator.RenHeadenergy;
import com.huake.device.domain.generator.TreAnaThreshold;
import com.huake.device.domain.generator.TreDeterThreshold;
import com.huake.device.domain.generator.TreWarn;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class TrendService {
    @Resource
    TreDeterThresholdMapper treDeterThresholdMapper;
    @Resource
    TreAnaThresholdMapper treAnaThresholdMapper;
    @Resource
    TreWarnMapper treWarnMapper;
    @Resource
    CommonMapper commonMapper;

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

    // 获取24小时内事件统计
    public List<Map> get24HWarnEventSummary() {
        return commonMapper.get24HWarnEventSummary();
    }

    // 获取24小时内事件列表
    public List<Map> get24HWarnEventList() {
        return commonMapper.get24HWarnEventList();
    }

    // 获取历史事件列表
    public List<TreWarn> selectHistoryWarnEventList(HistoryEvent historyEvent) {
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            selectModelQueryExpressionDSL.where()
                    .and(TreWarnDynamicSqlSupport.warn, isEqualTo(1))
                    .and(TreWarnDynamicSqlSupport.time,
                            isBetweenWhenPresent(historyEvent.getStart())
                                    .and(historyEvent.getEnd()))
                    .and(TreWarnDynamicSqlSupport.type, isInWhenPresent(historyEvent.getType()))
                    .and(TreWarnDynamicSqlSupport.name, isLikeWhenPresent(historyEvent.getKeyword()).then(s -> "%" + s + "%"))
                    .orderBy(TreWarnDynamicSqlSupport.time);
            return selectModelQueryExpressionDSL;
        };
        return treWarnMapper.select(completer);
    }
}
