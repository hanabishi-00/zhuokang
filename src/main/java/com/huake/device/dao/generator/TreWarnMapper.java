package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.TreWarnDynamicSqlSupport.*;

import com.huake.device.domain.generator.TreWarn;
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
public interface TreWarnMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(unit, id, type, time, name, warn);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TreWarn> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TreWarn> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TreWarnResult")
    Optional<TreWarn> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TreWarnResult", value = {
        @Result(column="unit", property="unit", jdbcType=JdbcType.INTEGER),
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="warn", property="warn", jdbcType=JdbcType.INTEGER)
    })
    List<TreWarn> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, treWarn, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, treWarn, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TreWarn record) {
        return MyBatis3Utils.insert(this::insert, record, treWarn, c ->
            c.map(unit).toProperty("unit")
            .map(id).toProperty("id")
            .map(type).toProperty("type")
            .map(time).toProperty("time")
            .map(name).toProperty("name")
            .map(warn).toProperty("warn")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TreWarn> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, treWarn, c ->
            c.map(unit).toProperty("unit")
            .map(id).toProperty("id")
            .map(type).toProperty("type")
            .map(time).toProperty("time")
            .map(name).toProperty("name")
            .map(warn).toProperty("warn")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TreWarn record) {
        return MyBatis3Utils.insert(this::insert, record, treWarn, c ->
            c.map(unit).toPropertyWhenPresent("unit", record::getUnit)
            .map(id).toPropertyWhenPresent("id", record::getId)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(time).toPropertyWhenPresent("time", record::getTime)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(warn).toPropertyWhenPresent("warn", record::getWarn)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreWarn> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, treWarn, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreWarn> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, treWarn, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreWarn> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, treWarn, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, treWarn, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TreWarn record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unit).equalTo(record::getUnit)
                .set(id).equalTo(record::getId)
                .set(type).equalTo(record::getType)
                .set(time).equalTo(record::getTime)
                .set(name).equalTo(record::getName)
                .set(warn).equalTo(record::getWarn);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TreWarn record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unit).equalToWhenPresent(record::getUnit)
                .set(id).equalToWhenPresent(record::getId)
                .set(type).equalToWhenPresent(record::getType)
                .set(time).equalToWhenPresent(record::getTime)
                .set(name).equalToWhenPresent(record::getName)
                .set(warn).equalToWhenPresent(record::getWarn);
    }
}