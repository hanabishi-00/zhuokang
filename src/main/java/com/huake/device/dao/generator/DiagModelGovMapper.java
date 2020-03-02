package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.DiagModelGovDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.DiagModelGov;
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
public interface DiagModelGovMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(nodeId, fatherId, gate);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<DiagModelGov> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<DiagModelGov> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DiagModelGovResult")
    Optional<DiagModelGov> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DiagModelGovResult", value = {
        @Result(column="node_id", property="nodeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="father_id", property="fatherId", jdbcType=JdbcType.INTEGER),
        @Result(column="gate", property="gate", jdbcType=JdbcType.VARCHAR)
    })
    List<DiagModelGov> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, diagModelGov, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, diagModelGov, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer nodeId_) {
        return delete(c -> 
            c.where(nodeId, isEqualTo(nodeId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(DiagModelGov record) {
        return MyBatis3Utils.insert(this::insert, record, diagModelGov, c ->
            c.map(nodeId).toProperty("nodeId")
            .map(fatherId).toProperty("fatherId")
            .map(gate).toProperty("gate")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<DiagModelGov> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, diagModelGov, c ->
            c.map(nodeId).toProperty("nodeId")
            .map(fatherId).toProperty("fatherId")
            .map(gate).toProperty("gate")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(DiagModelGov record) {
        return MyBatis3Utils.insert(this::insert, record, diagModelGov, c ->
            c.map(nodeId).toPropertyWhenPresent("nodeId", record::getNodeId)
            .map(fatherId).toPropertyWhenPresent("fatherId", record::getFatherId)
            .map(gate).toPropertyWhenPresent("gate", record::getGate)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagModelGov> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, diagModelGov, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagModelGov> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, diagModelGov, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagModelGov> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, diagModelGov, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagModelGov> selectByPrimaryKey(Integer nodeId_) {
        return selectOne(c ->
            c.where(nodeId, isEqualTo(nodeId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, diagModelGov, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(DiagModelGov record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(nodeId).equalTo(record::getNodeId)
                .set(fatherId).equalTo(record::getFatherId)
                .set(gate).equalTo(record::getGate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(DiagModelGov record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(nodeId).equalToWhenPresent(record::getNodeId)
                .set(fatherId).equalToWhenPresent(record::getFatherId)
                .set(gate).equalToWhenPresent(record::getGate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(DiagModelGov record) {
        return update(c ->
            c.set(fatherId).equalTo(record::getFatherId)
            .set(gate).equalTo(record::getGate)
            .where(nodeId, isEqualTo(record::getNodeId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(DiagModelGov record) {
        return update(c ->
            c.set(fatherId).equalToWhenPresent(record::getFatherId)
            .set(gate).equalToWhenPresent(record::getGate)
            .where(nodeId, isEqualTo(record::getNodeId))
        );
    }
}