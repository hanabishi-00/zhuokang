package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.TreDeterThresholdDynamicSqlSupport.*;

import com.huake.device.domain.generator.TreDeterThreshold;
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
public interface TreDeterThresholdMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(unit, id, version, name, val, relativeIds);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TreDeterThreshold> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TreDeterThreshold> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TreDeterThresholdResult")
    Optional<TreDeterThreshold> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TreDeterThresholdResult", value = {
        @Result(column="unit", property="unit", jdbcType=JdbcType.INTEGER),
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="val", property="val", jdbcType=JdbcType.REAL),
        @Result(column="relative_ids", property="relativeIds", jdbcType=JdbcType.VARCHAR)
    })
    List<TreDeterThreshold> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, treDeterThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, treDeterThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TreDeterThreshold record) {
        return MyBatis3Utils.insert(this::insert, record, treDeterThreshold, c ->
            c.map(unit).toProperty("unit")
            .map(id).toProperty("id")
            .map(version).toProperty("version")
            .map(name).toProperty("name")
            .map(val).toProperty("val")
            .map(relativeIds).toProperty("relativeIds")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TreDeterThreshold> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, treDeterThreshold, c ->
            c.map(unit).toProperty("unit")
            .map(id).toProperty("id")
            .map(version).toProperty("version")
            .map(name).toProperty("name")
            .map(val).toProperty("val")
            .map(relativeIds).toProperty("relativeIds")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TreDeterThreshold record) {
        return MyBatis3Utils.insert(this::insert, record, treDeterThreshold, c ->
            c.map(unit).toPropertyWhenPresent("unit", record::getUnit)
            .map(id).toPropertyWhenPresent("id", record::getId)
            .map(version).toPropertyWhenPresent("version", record::getVersion)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(val).toPropertyWhenPresent("val", record::getVal)
            .map(relativeIds).toPropertyWhenPresent("relativeIds", record::getRelativeIds)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreDeterThreshold> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, treDeterThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreDeterThreshold> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, treDeterThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreDeterThreshold> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, treDeterThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, treDeterThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TreDeterThreshold record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unit).equalTo(record::getUnit)
                .set(id).equalTo(record::getId)
                .set(version).equalTo(record::getVersion)
                .set(name).equalTo(record::getName)
                .set(val).equalTo(record::getVal)
                .set(relativeIds).equalTo(record::getRelativeIds);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TreDeterThreshold record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unit).equalToWhenPresent(record::getUnit)
                .set(id).equalToWhenPresent(record::getId)
                .set(version).equalToWhenPresent(record::getVersion)
                .set(name).equalToWhenPresent(record::getName)
                .set(val).equalToWhenPresent(record::getVal)
                .set(relativeIds).equalToWhenPresent(record::getRelativeIds);
    }
}