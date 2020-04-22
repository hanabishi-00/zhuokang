package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.RelevanceTestDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.RelevanceTest;
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
public interface RelevanceTestMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(seq, id, relId, relevance);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<RelevanceTest> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RelevanceTest> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RelevanceTestResult")
    Optional<RelevanceTest> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RelevanceTestResult", value = {
        @Result(column="seq", property="seq", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="REL_ID", property="relId", jdbcType=JdbcType.INTEGER),
        @Result(column="Relevance", property="relevance", jdbcType=JdbcType.REAL)
    })
    List<RelevanceTest> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, relevanceTest, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, relevanceTest, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(String seq_) {
        return delete(c -> 
            c.where(seq, isEqualTo(seq_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RelevanceTest record) {
        return MyBatis3Utils.insert(this::insert, record, relevanceTest, c ->
            c.map(seq).toProperty("seq")
            .map(id).toProperty("id")
            .map(relId).toProperty("relId")
            .map(relevance).toProperty("relevance")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<RelevanceTest> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, relevanceTest, c ->
            c.map(seq).toProperty("seq")
            .map(id).toProperty("id")
            .map(relId).toProperty("relId")
            .map(relevance).toProperty("relevance")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RelevanceTest record) {
        return MyBatis3Utils.insert(this::insert, record, relevanceTest, c ->
            c.map(seq).toPropertyWhenPresent("seq", record::getSeq)
            .map(id).toPropertyWhenPresent("id", record::getId)
            .map(relId).toPropertyWhenPresent("relId", record::getRelId)
            .map(relevance).toPropertyWhenPresent("relevance", record::getRelevance)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<RelevanceTest> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, relevanceTest, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<RelevanceTest> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, relevanceTest, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<RelevanceTest> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, relevanceTest, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<RelevanceTest> selectByPrimaryKey(String seq_) {
        return selectOne(c ->
            c.where(seq, isEqualTo(seq_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, relevanceTest, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(RelevanceTest record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(seq).equalTo(record::getSeq)
                .set(id).equalTo(record::getId)
                .set(relId).equalTo(record::getRelId)
                .set(relevance).equalTo(record::getRelevance);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RelevanceTest record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(seq).equalToWhenPresent(record::getSeq)
                .set(id).equalToWhenPresent(record::getId)
                .set(relId).equalToWhenPresent(record::getRelId)
                .set(relevance).equalToWhenPresent(record::getRelevance);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RelevanceTest record) {
        return update(c ->
            c.set(id).equalTo(record::getId)
            .set(relId).equalTo(record::getRelId)
            .set(relevance).equalTo(record::getRelevance)
            .where(seq, isEqualTo(record::getSeq))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RelevanceTest record) {
        return update(c ->
            c.set(id).equalToWhenPresent(record::getId)
            .set(relId).equalToWhenPresent(record::getRelId)
            .set(relevance).equalToWhenPresent(record::getRelevance)
            .where(seq, isEqualTo(record::getSeq))
        );
    }
}