package com.huake.device.service;

import com.huake.device.dao.generator.TestDynamicSqlSupport;
import com.huake.device.dao.generator.TestMapper;
import com.huake.device.domain.generator.Test;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;

@Service
public class TestService {
    @Resource
    TestMapper testMapper;

    public Test getTest(String name) {
        Optional<Test> optional = testMapper.selectByPrimaryKey(name);
        com.huake.device.domain.generator.Test test = optional.isPresent() ? optional.get() : null;
        return test;
    }

    public List<Test> getTestList(Test test) {
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            selectModelQueryExpressionDSL.where()
                    .and(TestDynamicSqlSupport.name, isEqualToWhenPresent(test.getName()))
                    .and(TestDynamicSqlSupport.des, isLikeWhenPresent(test.getDes()).then(s -> "%" + s + "%"));
            return selectModelQueryExpressionDSL;
        };
        List<Test> list = testMapper.select(completer);
        return list;
    }
}
