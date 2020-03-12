package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.TreeEvaUnitDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.TreeEvaUnit;
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
public interface TreeEvaUnitMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(unitId, typeId, deviceId);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TreeEvaUnit> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TreeEvaUnit> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TreeEvaUnitResult")
    Optional<TreeEvaUnit> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TreeEvaUnitResult", value = {
        @Result(column="unit_id", property="unitId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="device_id", property="deviceId", jdbcType=JdbcType.VARCHAR)
    })
    List<TreeEvaUnit> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, treeEvaUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, treeEvaUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer unitId_, Integer typeId_) {
        return delete(c -> 
            c.where(unitId, isEqualTo(unitId_))
            .and(typeId, isEqualTo(typeId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TreeEvaUnit record) {
        return MyBatis3Utils.insert(this::insert, record, treeEvaUnit, c ->
            c.map(unitId).toProperty("unitId")
            .map(typeId).toProperty("typeId")
            .map(deviceId).toProperty("deviceId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TreeEvaUnit> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, treeEvaUnit, c ->
            c.map(unitId).toProperty("unitId")
            .map(typeId).toProperty("typeId")
            .map(deviceId).toProperty("deviceId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TreeEvaUnit record) {
        return MyBatis3Utils.insert(this::insert, record, treeEvaUnit, c ->
            c.map(unitId).toPropertyWhenPresent("unitId", record::getUnitId)
            .map(typeId).toPropertyWhenPresent("typeId", record::getTypeId)
            .map(deviceId).toPropertyWhenPresent("deviceId", record::getDeviceId)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreeEvaUnit> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, treeEvaUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreeEvaUnit> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, treeEvaUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreeEvaUnit> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, treeEvaUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreeEvaUnit> selectByPrimaryKey(Integer unitId_, Integer typeId_) {
        return selectOne(c ->
            c.where(unitId, isEqualTo(unitId_))
            .and(typeId, isEqualTo(typeId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, treeEvaUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TreeEvaUnit record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unitId).equalTo(record::getUnitId)
                .set(typeId).equalTo(record::getTypeId)
                .set(deviceId).equalTo(record::getDeviceId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TreeEvaUnit record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unitId).equalToWhenPresent(record::getUnitId)
                .set(typeId).equalToWhenPresent(record::getTypeId)
                .set(deviceId).equalToWhenPresent(record::getDeviceId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TreeEvaUnit record) {
        return update(c ->
            c.set(deviceId).equalTo(record::getDeviceId)
            .where(unitId, isEqualTo(record::getUnitId))
            .and(typeId, isEqualTo(record::getTypeId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TreeEvaUnit record) {
        return update(c ->
            c.set(deviceId).equalToWhenPresent(record::getDeviceId)
            .where(unitId, isEqualTo(record::getUnitId))
            .and(typeId, isEqualTo(record::getTypeId))
        );
    }
}