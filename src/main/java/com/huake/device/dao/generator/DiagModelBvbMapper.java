package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.DiagModelBvbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.DiagModelBvb;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface DiagModelBvbMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, pid, fatherName, gatetype, points, method, threshold);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<DiagModelBvb> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<DiagModelBvb> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DiagModelBvbResult")
    Optional<DiagModelBvb> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DiagModelBvbResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER),
        @Result(column="father_name", property="fatherName", jdbcType=JdbcType.VARCHAR),
        @Result(column="gatetype", property="gatetype", jdbcType=JdbcType.VARCHAR),
        @Result(column="points", property="points", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="threshold", property="threshold", jdbcType=JdbcType.VARCHAR)
    })
    List<DiagModelBvb> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, diagModelBvb, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, diagModelBvb, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(DiagModelBvb record) {
        return MyBatis3Utils.insert(this::insert, record, diagModelBvb, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(pid).toProperty("pid")
            .map(fatherName).toProperty("fatherName")
            .map(gatetype).toProperty("gatetype")
            .map(points).toProperty("points")
            .map(method).toProperty("method")
            .map(threshold).toProperty("threshold")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<DiagModelBvb> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, diagModelBvb, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(pid).toProperty("pid")
            .map(fatherName).toProperty("fatherName")
            .map(gatetype).toProperty("gatetype")
            .map(points).toProperty("points")
            .map(method).toProperty("method")
            .map(threshold).toProperty("threshold")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(DiagModelBvb record) {
        return MyBatis3Utils.insert(this::insert, record, diagModelBvb, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(pid).toPropertyWhenPresent("pid", record::getPid)
            .map(fatherName).toPropertyWhenPresent("fatherName", record::getFatherName)
            .map(gatetype).toPropertyWhenPresent("gatetype", record::getGatetype)
            .map(points).toPropertyWhenPresent("points", record::getPoints)
            .map(method).toPropertyWhenPresent("method", record::getMethod)
            .map(threshold).toPropertyWhenPresent("threshold", record::getThreshold)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagModelBvb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, diagModelBvb, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagModelBvb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, diagModelBvb, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagModelBvb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, diagModelBvb, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagModelBvb> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, diagModelBvb, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(DiagModelBvb record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(pid).equalTo(record::getPid)
                .set(fatherName).equalTo(record::getFatherName)
                .set(gatetype).equalTo(record::getGatetype)
                .set(points).equalTo(record::getPoints)
                .set(method).equalTo(record::getMethod)
                .set(threshold).equalTo(record::getThreshold);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(DiagModelBvb record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(pid).equalToWhenPresent(record::getPid)
                .set(fatherName).equalToWhenPresent(record::getFatherName)
                .set(gatetype).equalToWhenPresent(record::getGatetype)
                .set(points).equalToWhenPresent(record::getPoints)
                .set(method).equalToWhenPresent(record::getMethod)
                .set(threshold).equalToWhenPresent(record::getThreshold);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(DiagModelBvb record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(pid).equalTo(record::getPid)
            .set(fatherName).equalTo(record::getFatherName)
            .set(gatetype).equalTo(record::getGatetype)
            .set(points).equalTo(record::getPoints)
            .set(method).equalTo(record::getMethod)
            .set(threshold).equalTo(record::getThreshold)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(DiagModelBvb record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(pid).equalToWhenPresent(record::getPid)
            .set(fatherName).equalToWhenPresent(record::getFatherName)
            .set(gatetype).equalToWhenPresent(record::getGatetype)
            .set(points).equalToWhenPresent(record::getPoints)
            .set(method).equalToWhenPresent(record::getMethod)
            .set(threshold).equalToWhenPresent(record::getThreshold)
            .where(id, isEqualTo(record::getId))
        );
    }
}