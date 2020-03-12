package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.TreeDiagUnitDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.TreeDiagUnit;
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
public interface TreeDiagUnitMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(unitId, deviceId);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TreeDiagUnit> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TreeDiagUnit> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TreeDiagUnitResult")
    Optional<TreeDiagUnit> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TreeDiagUnitResult", value = {
        @Result(column="unit_id", property="unitId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="device_id", property="deviceId", jdbcType=JdbcType.VARCHAR)
    })
    List<TreeDiagUnit> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, treeDiagUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, treeDiagUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer unitId_) {
        return delete(c -> 
            c.where(unitId, isEqualTo(unitId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TreeDiagUnit record) {
        return MyBatis3Utils.insert(this::insert, record, treeDiagUnit, c ->
            c.map(unitId).toProperty("unitId")
            .map(deviceId).toProperty("deviceId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TreeDiagUnit> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, treeDiagUnit, c ->
            c.map(unitId).toProperty("unitId")
            .map(deviceId).toProperty("deviceId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TreeDiagUnit record) {
        return MyBatis3Utils.insert(this::insert, record, treeDiagUnit, c ->
            c.map(unitId).toPropertyWhenPresent("unitId", record::getUnitId)
            .map(deviceId).toPropertyWhenPresent("deviceId", record::getDeviceId)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreeDiagUnit> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, treeDiagUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreeDiagUnit> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, treeDiagUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreeDiagUnit> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, treeDiagUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreeDiagUnit> selectByPrimaryKey(Integer unitId_) {
        return selectOne(c ->
            c.where(unitId, isEqualTo(unitId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, treeDiagUnit, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TreeDiagUnit record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unitId).equalTo(record::getUnitId)
                .set(deviceId).equalTo(record::getDeviceId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TreeDiagUnit record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unitId).equalToWhenPresent(record::getUnitId)
                .set(deviceId).equalToWhenPresent(record::getDeviceId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TreeDiagUnit record) {
        return update(c ->
            c.set(deviceId).equalTo(record::getDeviceId)
            .where(unitId, isEqualTo(record::getUnitId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TreeDiagUnit record) {
        return update(c ->
            c.set(deviceId).equalToWhenPresent(record::getDeviceId)
            .where(unitId, isEqualTo(record::getUnitId))
        );
    }
}