package com.huake.device.service;

import com.huake.device.dao.generator.*;
import com.huake.device.domain.generator.MadiagConRes;
import com.huake.device.domain.generator.MadiagHisProcess;
import com.huake.device.domain.generator.MadiagSingHis;
import com.huake.device.util.CharUtil;
import com.huake.device.util.MyException;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;
import com.huake.device.domain.generator.MadiagStanProcess;

import javax.annotation.Resource;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.isBetween;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;

@Service
public class MaDiagProcessService {
    @Resource
    private MadiagStanProcessMapper madiagStanProcessMapper;

    @Resource
    private MadiagHisProcessMapper madiagHisProcessMapper;

    @Resource
    private MadiagSingHisMapper madiagSingHisMapper;

    @Resource
    private MadiagConResMapper madiagConResMapper;

    public ArrayList<ArrayList<String>> getEquipList(Integer uid) {
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            selectModelQueryExpressionDSL.where()
                    .and(MadiagStanProcessDynamicSqlSupport.unitId, isEqualToWhenPresent(uid))
                    .groupBy(MadiagStanProcessDynamicSqlSupport.unitId, MadiagStanProcessDynamicSqlSupport.equipPro);
            return selectModelQueryExpressionDSL;
        };

        List<MadiagStanProcess> list = madiagStanProcessMapper.select(completer);
        //List<T> myLis =new ArrayList<T>();
        ArrayList<ArrayList<String>> myLis = new ArrayList<ArrayList<String>>();
        for (MadiagStanProcess madiagStanProcess : list) {
            ArrayList<String> list1 = new ArrayList<String>();
            list1.add(madiagStanProcess.getUnitId().toString());
            list1.add(madiagStanProcess.getEquipPro());
            myLis.add(list1);
        }
        return myLis;
    }

    public List<MadiagStanProcess> getStdProcess(Integer uid, String equip_pro) {
        if (uid == null || equip_pro == null)
            throw new MyException(-1, "机组号和设备流程名称都不能为空！");
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            selectModelQueryExpressionDSL.where()
                    .and(MadiagStanProcessDynamicSqlSupport.unitId, isEqualToWhenPresent(uid))
                    .and(MadiagStanProcessDynamicSqlSupport.equipPro, isEqualToWhenPresent(equip_pro))
                    .orderBy(MadiagStanProcessDynamicSqlSupport.stepId, MadiagStanProcessDynamicSqlSupport.measName);
            return selectModelQueryExpressionDSL;
        };
        return madiagStanProcessMapper.select(completer);

    }

    public List<MadiagHisProcess> queryHisProcess(Integer uid, String equip_pro, String stime, String etime) {
        if (uid == null || equip_pro == null || stime == null || etime == null)
            throw new MyException(-1, "机组号、设备流程名称、开始时间和结束时间均不能为空！");
        if (Long.valueOf(stime) > Long.valueOf(etime))
            throw new MyException(-1, "开始时间不能大于结束时间！");
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            selectModelQueryExpressionDSL.where()
                    .and(MadiagHisProcessDynamicSqlSupport.unitId, isEqualToWhenPresent(uid))
                    .and(MadiagHisProcessDynamicSqlSupport.equipPro, isEqualToWhenPresent(equip_pro))
                    .and(MadiagHisProcessDynamicSqlSupport.time, isBetween(CharUtil.fillTimestamp(stime)).and(CharUtil.fillTimestamp(etime)));
            return selectModelQueryExpressionDSL;
        };
        return madiagHisProcessMapper.select(completer);
    }

    public List<MadiagSingHis> viewHisProcess(Integer id) {
        if (id == null)
            throw new MyException(-1, "id不能为空！");
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            selectModelQueryExpressionDSL.where()
                    .and(MadiagSingHisDynamicSqlSupport.id, isEqualToWhenPresent(id))
                    .orderBy(MadiagSingHisDynamicSqlSupport.histtepId, MadiagSingHisDynamicSqlSupport.hismeasName);
            return selectModelQueryExpressionDSL;
        };
        return madiagSingHisMapper.select(completer);
    }

    public List<MadiagConRes> viewConRes(Integer id) {
        if (id == null)
            throw new MyException(-1, "id不能为空！");
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            selectModelQueryExpressionDSL.where()
                    .and(MadiagConResDynamicSqlSupport.id, isEqualToWhenPresent(id));
            return selectModelQueryExpressionDSL;
        };
        return madiagConResMapper.select(completer);
    }

}
