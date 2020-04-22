package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.EvaQuickTimeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.EvaQuickTime;
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
public interface EvaQuickTimeMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(timeType, unitSet, timeGap, splitIndex, timePsg);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<EvaQuickTime> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<EvaQuickTime> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EvaQuickTimeResult")
    Optional<EvaQuickTime> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EvaQuickTimeResult", value = {
        @Result(column="time_type", property="timeType", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="unit_set", property="unitSet", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="time_gap", property="timeGap", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="split_index", property="splitIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="time_psg", property="timePsg", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<EvaQuickTime> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, evaQuickTime, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, evaQuickTime, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(String timeType_, String unitSet_, String timeGap_) {
        return delete(c -> 
            c.where(timeType, isEqualTo(timeType_))
            .and(unitSet, isEqualTo(unitSet_))
            .and(timeGap, isEqualTo(timeGap_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(EvaQuickTime record) {
        return MyBatis3Utils.insert(this::insert, record, evaQuickTime, c ->
            c.map(timeType).toProperty("timeType")
            .map(unitSet).toProperty("unitSet")
            .map(timeGap).toProperty("timeGap")
            .map(splitIndex).toProperty("splitIndex")
            .map(timePsg).toProperty("timePsg")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<EvaQuickTime> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, evaQuickTime, c ->
            c.map(timeType).toProperty("timeType")
            .map(unitSet).toProperty("unitSet")
            .map(timeGap).toProperty("timeGap")
            .map(splitIndex).toProperty("splitIndex")
            .map(timePsg).toProperty("timePsg")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(EvaQuickTime record) {
        return MyBatis3Utils.insert(this::insert, record, evaQuickTime, c ->
            c.map(timeType).toPropertyWhenPresent("timeType", record::getTimeType)
            .map(unitSet).toPropertyWhenPresent("unitSet", record::getUnitSet)
            .map(timeGap).toPropertyWhenPresent("timeGap", record::getTimeGap)
            .map(splitIndex).toPropertyWhenPresent("splitIndex", record::getSplitIndex)
            .map(timePsg).toPropertyWhenPresent("timePsg", record::getTimePsg)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaQuickTime> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, evaQuickTime, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaQuickTime> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, evaQuickTime, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaQuickTime> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, evaQuickTime, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaQuickTime> selectByPrimaryKey(String timeType_, String unitSet_, String timeGap_) {
        return selectOne(c ->
            c.where(timeType, isEqualTo(timeType_))
            .and(unitSet, isEqualTo(unitSet_))
            .and(timeGap, isEqualTo(timeGap_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, evaQuickTime, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(EvaQuickTime record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(timeType).equalTo(record::getTimeType)
                .set(unitSet).equalTo(record::getUnitSet)
                .set(timeGap).equalTo(record::getTimeGap)
                .set(splitIndex).equalTo(record::getSplitIndex)
                .set(timePsg).equalTo(record::getTimePsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(EvaQuickTime record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(timeType).equalToWhenPresent(record::getTimeType)
                .set(unitSet).equalToWhenPresent(record::getUnitSet)
                .set(timeGap).equalToWhenPresent(record::getTimeGap)
                .set(splitIndex).equalToWhenPresent(record::getSplitIndex)
                .set(timePsg).equalToWhenPresent(record::getTimePsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(EvaQuickTime record) {
        return update(c ->
            c.set(splitIndex).equalTo(record::getSplitIndex)
            .set(timePsg).equalTo(record::getTimePsg)
            .where(timeType, isEqualTo(record::getTimeType))
            .and(unitSet, isEqualTo(record::getUnitSet))
            .and(timeGap, isEqualTo(record::getTimeGap))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(EvaQuickTime record) {
        return update(c ->
            c.set(splitIndex).equalToWhenPresent(record::getSplitIndex)
            .set(timePsg).equalToWhenPresent(record::getTimePsg)
            .where(timeType, isEqualTo(record::getTimeType))
            .and(unitSet, isEqualTo(record::getUnitSet))
            .and(timeGap, isEqualTo(record::getTimeGap))
        );
    }
}