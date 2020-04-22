package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.EvaResOfflineDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.EvaResOffline;
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
public interface EvaResOfflineMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(modelTime, type, time, unit, res);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<EvaResOffline> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<EvaResOffline> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EvaResOfflineResult")
    Optional<EvaResOffline> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EvaResOfflineResult", value = {
        @Result(column="model_time", property="modelTime", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="unit", property="unit", jdbcType=JdbcType.TINYINT, id=true),
        @Result(column="res", property="res", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<EvaResOffline> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, evaResOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, evaResOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer modelTime_, String type_, Integer time_, Byte unit_) {
        return delete(c -> 
            c.where(modelTime, isEqualTo(modelTime_))
            .and(type, isEqualTo(type_))
            .and(time, isEqualTo(time_))
            .and(unit, isEqualTo(unit_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(EvaResOffline record) {
        return MyBatis3Utils.insert(this::insert, record, evaResOffline, c ->
            c.map(modelTime).toProperty("modelTime")
            .map(type).toProperty("type")
            .map(time).toProperty("time")
            .map(unit).toProperty("unit")
            .map(res).toProperty("res")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<EvaResOffline> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, evaResOffline, c ->
            c.map(modelTime).toProperty("modelTime")
            .map(type).toProperty("type")
            .map(time).toProperty("time")
            .map(unit).toProperty("unit")
            .map(res).toProperty("res")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(EvaResOffline record) {
        return MyBatis3Utils.insert(this::insert, record, evaResOffline, c ->
            c.map(modelTime).toPropertyWhenPresent("modelTime", record::getModelTime)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(time).toPropertyWhenPresent("time", record::getTime)
            .map(unit).toPropertyWhenPresent("unit", record::getUnit)
            .map(res).toPropertyWhenPresent("res", record::getRes)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaResOffline> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, evaResOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaResOffline> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, evaResOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaResOffline> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, evaResOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaResOffline> selectByPrimaryKey(Integer modelTime_, String type_, Integer time_, Byte unit_) {
        return selectOne(c ->
            c.where(modelTime, isEqualTo(modelTime_))
            .and(type, isEqualTo(type_))
            .and(time, isEqualTo(time_))
            .and(unit, isEqualTo(unit_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, evaResOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(EvaResOffline record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(modelTime).equalTo(record::getModelTime)
                .set(type).equalTo(record::getType)
                .set(time).equalTo(record::getTime)
                .set(unit).equalTo(record::getUnit)
                .set(res).equalTo(record::getRes);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(EvaResOffline record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(modelTime).equalToWhenPresent(record::getModelTime)
                .set(type).equalToWhenPresent(record::getType)
                .set(time).equalToWhenPresent(record::getTime)
                .set(unit).equalToWhenPresent(record::getUnit)
                .set(res).equalToWhenPresent(record::getRes);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(EvaResOffline record) {
        return update(c ->
            c.set(res).equalTo(record::getRes)
            .where(modelTime, isEqualTo(record::getModelTime))
            .and(type, isEqualTo(record::getType))
            .and(time, isEqualTo(record::getTime))
            .and(unit, isEqualTo(record::getUnit))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(EvaResOffline record) {
        return update(c ->
            c.set(res).equalToWhenPresent(record::getRes)
            .where(modelTime, isEqualTo(record::getModelTime))
            .and(type, isEqualTo(record::getType))
            .and(time, isEqualTo(record::getTime))
            .and(unit, isEqualTo(record::getUnit))
        );
    }
}