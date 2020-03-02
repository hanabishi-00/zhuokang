package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.TreDeterThresholdDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

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
    BasicColumn[] selectList = BasicColumn.columnList(modelNumber, modelName, deterdataTable, preResultTable, warnThreshold);

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
        @Result(column="model_number", property="modelNumber", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="model_name", property="modelName", jdbcType=JdbcType.CHAR),
        @Result(column="deterdata_table", property="deterdataTable", jdbcType=JdbcType.CHAR),
        @Result(column="pre_result_table", property="preResultTable", jdbcType=JdbcType.CHAR),
        @Result(column="warn_threshold", property="warnThreshold", jdbcType=JdbcType.REAL)
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
    default int deleteByPrimaryKey(Integer modelNumber_) {
        return delete(c -> 
            c.where(modelNumber, isEqualTo(modelNumber_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TreDeterThreshold record) {
        return MyBatis3Utils.insert(this::insert, record, treDeterThreshold, c ->
            c.map(modelNumber).toProperty("modelNumber")
            .map(modelName).toProperty("modelName")
            .map(deterdataTable).toProperty("deterdataTable")
            .map(preResultTable).toProperty("preResultTable")
            .map(warnThreshold).toProperty("warnThreshold")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TreDeterThreshold> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, treDeterThreshold, c ->
            c.map(modelNumber).toProperty("modelNumber")
            .map(modelName).toProperty("modelName")
            .map(deterdataTable).toProperty("deterdataTable")
            .map(preResultTable).toProperty("preResultTable")
            .map(warnThreshold).toProperty("warnThreshold")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TreDeterThreshold record) {
        return MyBatis3Utils.insert(this::insert, record, treDeterThreshold, c ->
            c.map(modelNumber).toPropertyWhenPresent("modelNumber", record::getModelNumber)
            .map(modelName).toPropertyWhenPresent("modelName", record::getModelName)
            .map(deterdataTable).toPropertyWhenPresent("deterdataTable", record::getDeterdataTable)
            .map(preResultTable).toPropertyWhenPresent("preResultTable", record::getPreResultTable)
            .map(warnThreshold).toPropertyWhenPresent("warnThreshold", record::getWarnThreshold)
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
    default Optional<TreDeterThreshold> selectByPrimaryKey(Integer modelNumber_) {
        return selectOne(c ->
            c.where(modelNumber, isEqualTo(modelNumber_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, treDeterThreshold, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TreDeterThreshold record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(modelNumber).equalTo(record::getModelNumber)
                .set(modelName).equalTo(record::getModelName)
                .set(deterdataTable).equalTo(record::getDeterdataTable)
                .set(preResultTable).equalTo(record::getPreResultTable)
                .set(warnThreshold).equalTo(record::getWarnThreshold);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TreDeterThreshold record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(modelNumber).equalToWhenPresent(record::getModelNumber)
                .set(modelName).equalToWhenPresent(record::getModelName)
                .set(deterdataTable).equalToWhenPresent(record::getDeterdataTable)
                .set(preResultTable).equalToWhenPresent(record::getPreResultTable)
                .set(warnThreshold).equalToWhenPresent(record::getWarnThreshold);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TreDeterThreshold record) {
        return update(c ->
            c.set(modelName).equalTo(record::getModelName)
            .set(deterdataTable).equalTo(record::getDeterdataTable)
            .set(preResultTable).equalTo(record::getPreResultTable)
            .set(warnThreshold).equalTo(record::getWarnThreshold)
            .where(modelNumber, isEqualTo(record::getModelNumber))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TreDeterThreshold record) {
        return update(c ->
            c.set(modelName).equalToWhenPresent(record::getModelName)
            .set(deterdataTable).equalToWhenPresent(record::getDeterdataTable)
            .set(preResultTable).equalToWhenPresent(record::getPreResultTable)
            .set(warnThreshold).equalToWhenPresent(record::getWarnThreshold)
            .where(modelNumber, isEqualTo(record::getModelNumber))
        );
    }
}