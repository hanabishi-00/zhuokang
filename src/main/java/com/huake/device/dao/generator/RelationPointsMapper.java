package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.RelationPointsDynamicSqlSupport.*;

import com.huake.device.domain.generator.RelationPoints;
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
public interface RelationPointsMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, type, relationId, relationType, percent);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<RelationPoints> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RelationPoints> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RelationPointsResult")
    Optional<RelationPoints> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RelationPointsResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="relation_id", property="relationId", jdbcType=JdbcType.BIGINT),
        @Result(column="relation_type", property="relationType", jdbcType=JdbcType.VARCHAR),
        @Result(column="percent", property="percent", jdbcType=JdbcType.REAL)
    })
    List<RelationPoints> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, relationPoints, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, relationPoints, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RelationPoints record) {
        return MyBatis3Utils.insert(this::insert, record, relationPoints, c ->
            c.map(id).toProperty("id")
            .map(type).toProperty("type")
            .map(relationId).toProperty("relationId")
            .map(relationType).toProperty("relationType")
            .map(percent).toProperty("percent")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<RelationPoints> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, relationPoints, c ->
            c.map(id).toProperty("id")
            .map(type).toProperty("type")
            .map(relationId).toProperty("relationId")
            .map(relationType).toProperty("relationType")
            .map(percent).toProperty("percent")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RelationPoints record) {
        return MyBatis3Utils.insert(this::insert, record, relationPoints, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(relationId).toPropertyWhenPresent("relationId", record::getRelationId)
            .map(relationType).toPropertyWhenPresent("relationType", record::getRelationType)
            .map(percent).toPropertyWhenPresent("percent", record::getPercent)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<RelationPoints> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, relationPoints, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<RelationPoints> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, relationPoints, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<RelationPoints> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, relationPoints, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, relationPoints, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(RelationPoints record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(type).equalTo(record::getType)
                .set(relationId).equalTo(record::getRelationId)
                .set(relationType).equalTo(record::getRelationType)
                .set(percent).equalTo(record::getPercent);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RelationPoints record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(type).equalToWhenPresent(record::getType)
                .set(relationId).equalToWhenPresent(record::getRelationId)
                .set(relationType).equalToWhenPresent(record::getRelationType)
                .set(percent).equalToWhenPresent(record::getPercent);
    }
}