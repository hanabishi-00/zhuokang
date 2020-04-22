package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.DiagResultDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.DiagResult;
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
public interface DiagResultMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, recordId, nodeId, freq, suggId);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<DiagResult> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<DiagResult> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DiagResultResult")
    Optional<DiagResult> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DiagResultResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="record_id", property="recordId", jdbcType=JdbcType.CHAR),
        @Result(column="node_id", property="nodeId", jdbcType=JdbcType.INTEGER),
        @Result(column="freq", property="freq", jdbcType=JdbcType.REAL),
        @Result(column="sugg_id", property="suggId", jdbcType=JdbcType.INTEGER)
    })
    List<DiagResult> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, diagResult, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, diagResult, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(DiagResult record) {
        return MyBatis3Utils.insert(this::insert, record, diagResult, c ->
            c.map(id).toProperty("id")
            .map(recordId).toProperty("recordId")
            .map(nodeId).toProperty("nodeId")
            .map(freq).toProperty("freq")
            .map(suggId).toProperty("suggId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<DiagResult> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, diagResult, c ->
            c.map(id).toProperty("id")
            .map(recordId).toProperty("recordId")
            .map(nodeId).toProperty("nodeId")
            .map(freq).toProperty("freq")
            .map(suggId).toProperty("suggId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(DiagResult record) {
        return MyBatis3Utils.insert(this::insert, record, diagResult, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(recordId).toPropertyWhenPresent("recordId", record::getRecordId)
            .map(nodeId).toPropertyWhenPresent("nodeId", record::getNodeId)
            .map(freq).toPropertyWhenPresent("freq", record::getFreq)
            .map(suggId).toPropertyWhenPresent("suggId", record::getSuggId)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagResult> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, diagResult, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagResult> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, diagResult, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagResult> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, diagResult, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagResult> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, diagResult, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(DiagResult record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(recordId).equalTo(record::getRecordId)
                .set(nodeId).equalTo(record::getNodeId)
                .set(freq).equalTo(record::getFreq)
                .set(suggId).equalTo(record::getSuggId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(DiagResult record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(recordId).equalToWhenPresent(record::getRecordId)
                .set(nodeId).equalToWhenPresent(record::getNodeId)
                .set(freq).equalToWhenPresent(record::getFreq)
                .set(suggId).equalToWhenPresent(record::getSuggId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(DiagResult record) {
        return update(c ->
            c.set(recordId).equalTo(record::getRecordId)
            .set(nodeId).equalTo(record::getNodeId)
            .set(freq).equalTo(record::getFreq)
            .set(suggId).equalTo(record::getSuggId)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(DiagResult record) {
        return update(c ->
            c.set(recordId).equalToWhenPresent(record::getRecordId)
            .set(nodeId).equalToWhenPresent(record::getNodeId)
            .set(freq).equalToWhenPresent(record::getFreq)
            .set(suggId).equalToWhenPresent(record::getSuggId)
            .where(id, isEqualTo(record::getId))
        );
    }
}