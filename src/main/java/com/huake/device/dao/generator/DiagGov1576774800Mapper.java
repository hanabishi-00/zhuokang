package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.DiagGov1576774800DynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.DiagGov1576774800;
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
public interface DiagGov1576774800Mapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(nodeId, freq, suggId);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<DiagGov1576774800> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<DiagGov1576774800> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DiagGov1576774800Result")
    Optional<DiagGov1576774800> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DiagGov1576774800Result", value = {
        @Result(column="node_id", property="nodeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="freq", property="freq", jdbcType=JdbcType.REAL),
        @Result(column="sugg_id", property="suggId", jdbcType=JdbcType.INTEGER)
    })
    List<DiagGov1576774800> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, diagGov1576774800, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, diagGov1576774800, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer nodeId_) {
        return delete(c -> 
            c.where(nodeId, isEqualTo(nodeId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(DiagGov1576774800 record) {
        return MyBatis3Utils.insert(this::insert, record, diagGov1576774800, c ->
            c.map(nodeId).toProperty("nodeId")
            .map(freq).toProperty("freq")
            .map(suggId).toProperty("suggId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<DiagGov1576774800> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, diagGov1576774800, c ->
            c.map(nodeId).toProperty("nodeId")
            .map(freq).toProperty("freq")
            .map(suggId).toProperty("suggId")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(DiagGov1576774800 record) {
        return MyBatis3Utils.insert(this::insert, record, diagGov1576774800, c ->
            c.map(nodeId).toPropertyWhenPresent("nodeId", record::getNodeId)
            .map(freq).toPropertyWhenPresent("freq", record::getFreq)
            .map(suggId).toPropertyWhenPresent("suggId", record::getSuggId)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagGov1576774800> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, diagGov1576774800, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagGov1576774800> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, diagGov1576774800, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagGov1576774800> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, diagGov1576774800, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagGov1576774800> selectByPrimaryKey(Integer nodeId_) {
        return selectOne(c ->
            c.where(nodeId, isEqualTo(nodeId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, diagGov1576774800, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(DiagGov1576774800 record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(nodeId).equalTo(record::getNodeId)
                .set(freq).equalTo(record::getFreq)
                .set(suggId).equalTo(record::getSuggId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(DiagGov1576774800 record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(nodeId).equalToWhenPresent(record::getNodeId)
                .set(freq).equalToWhenPresent(record::getFreq)
                .set(suggId).equalToWhenPresent(record::getSuggId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(DiagGov1576774800 record) {
        return update(c ->
            c.set(freq).equalTo(record::getFreq)
            .set(suggId).equalTo(record::getSuggId)
            .where(nodeId, isEqualTo(record::getNodeId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(DiagGov1576774800 record) {
        return update(c ->
            c.set(freq).equalToWhenPresent(record::getFreq)
            .set(suggId).equalToWhenPresent(record::getSuggId)
            .where(nodeId, isEqualTo(record::getNodeId))
        );
    }
}