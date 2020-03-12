package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.TreePreUnitDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.TreePreUnit;
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
public interface TreePreUnitMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(unitId, deviceId, state, measureId);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TreePreUnit> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TreePreUnit> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TreePreUnitResult")
    Optional<TreePreUnit> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TreePreUnitResult", value = {
        @Result(column="unit_id", property="unitId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="device_id", property="deviceId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="measure_id", property="measureId", jdbcType=JdbcType.VARCHAR)
    })
    List<TreePreUnit> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, treePreUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, treePreUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer unitId_, String deviceId_, Integer state_) {
        return delete(c -> 
            c.where(unitId, isEqualTo(unitId_))
            .and(deviceId, isEqualTo(deviceId_))
            .and(state, isEqualTo(state_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TreePreUnit record) {
        return MyBatis3Utils.insert(this::insert, record, treePreUnit, c ->
            c.map(unitId).toProperty("unitId")
            .map(deviceId).toProperty("deviceId")
            .map(state).toProperty("state")
            .map(measureId).toProperty("measureId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TreePreUnit> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, treePreUnit, c ->
            c.map(unitId).toProperty("unitId")
            .map(deviceId).toProperty("deviceId")
            .map(state).toProperty("state")
            .map(measureId).toProperty("measureId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TreePreUnit record) {
        return MyBatis3Utils.insert(this::insert, record, treePreUnit, c ->
            c.map(unitId).toPropertyWhenPresent("unitId", record::getUnitId)
            .map(deviceId).toPropertyWhenPresent("deviceId", record::getDeviceId)
            .map(state).toPropertyWhenPresent("state", record::getState)
            .map(measureId).toPropertyWhenPresent("measureId", record::getMeasureId)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreePreUnit> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, treePreUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreePreUnit> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, treePreUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreePreUnit> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, treePreUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreePreUnit> selectByPrimaryKey(Integer unitId_, String deviceId_, Integer state_) {
        return selectOne(c ->
            c.where(unitId, isEqualTo(unitId_))
            .and(deviceId, isEqualTo(deviceId_))
            .and(state, isEqualTo(state_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, treePreUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TreePreUnit record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unitId).equalTo(record::getUnitId)
                .set(deviceId).equalTo(record::getDeviceId)
                .set(state).equalTo(record::getState)
                .set(measureId).equalTo(record::getMeasureId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TreePreUnit record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unitId).equalToWhenPresent(record::getUnitId)
                .set(deviceId).equalToWhenPresent(record::getDeviceId)
                .set(state).equalToWhenPresent(record::getState)
                .set(measureId).equalToWhenPresent(record::getMeasureId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TreePreUnit record) {
        return update(c ->
            c.set(measureId).equalTo(record::getMeasureId)
            .where(unitId, isEqualTo(record::getUnitId))
            .and(deviceId, isEqualTo(record::getDeviceId))
            .and(state, isEqualTo(record::getState))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TreePreUnit record) {
        return update(c ->
            c.set(measureId).equalToWhenPresent(record::getMeasureId)
            .where(unitId, isEqualTo(record::getUnitId))
            .and(deviceId, isEqualTo(record::getDeviceId))
            .and(state, isEqualTo(record::getState))
        );
    }
}