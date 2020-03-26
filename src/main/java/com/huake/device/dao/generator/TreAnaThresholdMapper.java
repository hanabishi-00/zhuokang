package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.TreAnaThresholdDynamicSqlSupport.*;

import com.huake.device.domain.generator.TreAnaThreshold;
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
public interface TreAnaThresholdMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(unit, id, boolIds, floatIds, name, val);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TreAnaThreshold> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TreAnaThreshold> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TreAnaThresholdResult")
    Optional<TreAnaThreshold> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TreAnaThresholdResult", value = {
        @Result(column="unit", property="unit", jdbcType=JdbcType.INTEGER),
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="bool_ids", property="boolIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="float_ids", property="floatIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="val", property="val", jdbcType=JdbcType.REAL)
    })
    List<TreAnaThreshold> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, treAnaThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, treAnaThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TreAnaThreshold record) {
        return MyBatis3Utils.insert(this::insert, record, treAnaThreshold, c ->
            c.map(unit).toProperty("unit")
            .map(id).toProperty("id")
            .map(boolIds).toProperty("boolIds")
            .map(floatIds).toProperty("floatIds")
            .map(name).toProperty("name")
            .map(val).toProperty("val")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TreAnaThreshold> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, treAnaThreshold, c ->
            c.map(unit).toProperty("unit")
            .map(id).toProperty("id")
            .map(boolIds).toProperty("boolIds")
            .map(floatIds).toProperty("floatIds")
            .map(name).toProperty("name")
            .map(val).toProperty("val")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TreAnaThreshold record) {
        return MyBatis3Utils.insert(this::insert, record, treAnaThreshold, c ->
            c.map(unit).toPropertyWhenPresent("unit", record::getUnit)
            .map(id).toPropertyWhenPresent("id", record::getId)
            .map(boolIds).toPropertyWhenPresent("boolIds", record::getBoolIds)
            .map(floatIds).toPropertyWhenPresent("floatIds", record::getFloatIds)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(val).toPropertyWhenPresent("val", record::getVal)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreAnaThreshold> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, treAnaThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreAnaThreshold> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, treAnaThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreAnaThreshold> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, treAnaThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, treAnaThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TreAnaThreshold record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unit).equalTo(record::getUnit)
                .set(id).equalTo(record::getId)
                .set(boolIds).equalTo(record::getBoolIds)
                .set(floatIds).equalTo(record::getFloatIds)
                .set(name).equalTo(record::getName)
                .set(val).equalTo(record::getVal);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TreAnaThreshold record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unit).equalToWhenPresent(record::getUnit)
                .set(id).equalToWhenPresent(record::getId)
                .set(boolIds).equalToWhenPresent(record::getBoolIds)
                .set(floatIds).equalToWhenPresent(record::getFloatIds)
                .set(name).equalToWhenPresent(record::getName)
                .set(val).equalToWhenPresent(record::getVal);
    }
}