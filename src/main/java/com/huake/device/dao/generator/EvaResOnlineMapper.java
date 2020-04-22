package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.EvaResOnlineDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.EvaResOnline;
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
public interface EvaResOnlineMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(modelTime, type, time, unit, offlineTime, res);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<EvaResOnline> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<EvaResOnline> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EvaResOnlineResult")
    Optional<EvaResOnline> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EvaResOnlineResult", value = {
        @Result(column="model_time", property="modelTime", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="unit", property="unit", jdbcType=JdbcType.TINYINT, id=true),
        @Result(column="offline_time", property="offlineTime", jdbcType=JdbcType.INTEGER),
        @Result(column="res", property="res", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<EvaResOnline> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, evaResOnline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, evaResOnline, completer);
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
    default int insert(EvaResOnline record) {
        return MyBatis3Utils.insert(this::insert, record, evaResOnline, c ->
            c.map(modelTime).toProperty("modelTime")
            .map(type).toProperty("type")
            .map(time).toProperty("time")
            .map(unit).toProperty("unit")
            .map(offlineTime).toProperty("offlineTime")
            .map(res).toProperty("res")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<EvaResOnline> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, evaResOnline, c ->
            c.map(modelTime).toProperty("modelTime")
            .map(type).toProperty("type")
            .map(time).toProperty("time")
            .map(unit).toProperty("unit")
            .map(offlineTime).toProperty("offlineTime")
            .map(res).toProperty("res")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(EvaResOnline record) {
        return MyBatis3Utils.insert(this::insert, record, evaResOnline, c ->
            c.map(modelTime).toPropertyWhenPresent("modelTime", record::getModelTime)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(time).toPropertyWhenPresent("time", record::getTime)
            .map(unit).toPropertyWhenPresent("unit", record::getUnit)
            .map(offlineTime).toPropertyWhenPresent("offlineTime", record::getOfflineTime)
            .map(res).toPropertyWhenPresent("res", record::getRes)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaResOnline> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, evaResOnline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaResOnline> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, evaResOnline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaResOnline> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, evaResOnline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaResOnline> selectByPrimaryKey(Integer modelTime_, String type_, Integer time_, Byte unit_) {
        return selectOne(c ->
            c.where(modelTime, isEqualTo(modelTime_))
            .and(type, isEqualTo(type_))
            .and(time, isEqualTo(time_))
            .and(unit, isEqualTo(unit_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, evaResOnline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(EvaResOnline record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(modelTime).equalTo(record::getModelTime)
                .set(type).equalTo(record::getType)
                .set(time).equalTo(record::getTime)
                .set(unit).equalTo(record::getUnit)
                .set(offlineTime).equalTo(record::getOfflineTime)
                .set(res).equalTo(record::getRes);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(EvaResOnline record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(modelTime).equalToWhenPresent(record::getModelTime)
                .set(type).equalToWhenPresent(record::getType)
                .set(time).equalToWhenPresent(record::getTime)
                .set(unit).equalToWhenPresent(record::getUnit)
                .set(offlineTime).equalToWhenPresent(record::getOfflineTime)
                .set(res).equalToWhenPresent(record::getRes);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(EvaResOnline record) {
        return update(c ->
            c.set(offlineTime).equalTo(record::getOfflineTime)
            .set(res).equalTo(record::getRes)
            .where(modelTime, isEqualTo(record::getModelTime))
            .and(type, isEqualTo(record::getType))
            .and(time, isEqualTo(record::getTime))
            .and(unit, isEqualTo(record::getUnit))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(EvaResOnline record) {
        return update(c ->
            c.set(offlineTime).equalToWhenPresent(record::getOfflineTime)
            .set(res).equalToWhenPresent(record::getRes)
            .where(modelTime, isEqualTo(record::getModelTime))
            .and(type, isEqualTo(record::getType))
            .and(time, isEqualTo(record::getTime))
            .and(unit, isEqualTo(record::getUnit))
        );
    }
}