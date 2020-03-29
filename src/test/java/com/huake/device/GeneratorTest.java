package com.huake.device;

import com.alibaba.fastjson.JSON;
import com.huake.device.dao.generator.TestDynamicSqlSupport;
import com.huake.device.dao.generator.TestMapper;
import com.huake.device.util.CharUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneratorTest {
    @Resource
    private TestMapper mapper;

    @Test
    public void testCount() throws IOException {
        long count = mapper.count(CountDSLCompleter.allRows());
        System.out.println(count);
    }

    @Test
    public void insert() throws IOException {
        com.huake.device.domain.generator.Test test = new com.huake.device.domain.generator.Test();
        test.setName("张三2");
        test.setAge("14");
        test.setDes("test 222");
        int num = mapper.insert(test);
        System.out.println("插入（条数据）：" + num);
    }

    @Test
    public void selectOne() throws IOException {
        com.huake.device.domain.generator.Test test = mapper.selectOne(
                c -> c.where(TestDynamicSqlSupport.name, isEqualTo("张三"))
        ).get();
        System.out.println("查询数据：" + JSON.toJSONString(test));
    }

    @Test
    public void selectByPrimaryKey() throws IOException {
        Optional<com.huake.device.domain.generator.Test> optional = mapper.selectByPrimaryKey("张三");
        com.huake.device.domain.generator.Test test = optional.isPresent() ? optional.get() : null;
        System.out.println("查询数据：" + JSON.toJSONString(test));
    }

    @Test
    public void select() throws IOException {
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            WhereApplier ss = abstractWhereDSL -> abstractWhereDSL.and(TestDynamicSqlSupport.name, isEqualTo("张三"));
            selectModelQueryExpressionDSL.where(TestDynamicSqlSupport.name, isLike("%三"));
            selectModelQueryExpressionDSL.applyWhere(ss);
            return selectModelQueryExpressionDSL;
        };
        List<com.huake.device.domain.generator.Test> list = mapper.select(completer);
        System.out.println("查询数据：" + JSON.toJSONString(list));
    }

    @Test
    public void select2() throws IOException {
        List<com.huake.device.domain.generator.Test> list =
                mapper.select(c -> c.where(TestDynamicSqlSupport.name, isLike("%三")));
        System.out.println("查询数据：" + JSON.toJSONString(list));
    }

    @Test
    public void updateByPrimaryKey() throws IOException {
        com.huake.device.domain.generator.Test test = new com.huake.device.domain.generator.Test();
        test.setName("张三");
        test.setAge("12");

        int num = mapper.updateByPrimaryKey(test);
        System.out.println("更新（条数据）：" + num);
    }

    @Test
    public void updateByPrimaryKeySelective() throws IOException {
        com.huake.device.domain.generator.Test test = new com.huake.device.domain.generator.Test();
        test.setName("张三");
        test.setAge("120");

        int num = mapper.updateByPrimaryKeySelective(test);
        System.out.println("更新（条数据）：" + num);
    }

    @Test
    public void update() throws IOException {
        int num = mapper.update(
                c -> c.set(TestDynamicSqlSupport.des).equalTo("ddd")
                        .where(TestDynamicSqlSupport.name, isEqualTo("张三"))
        );
        System.out.println("更新（条数据）：" + num);
    }

    @Test
    public void delete() throws IOException {
        int num = mapper.deleteByPrimaryKey("张三2");
        System.out.println("删除（条数据）：" + num);
    }

    @Test
    public void test_fillTimestamp() throws IOException {
        System.out.println(CharUtil.fillTimestamp("987654321"));
    }

}
