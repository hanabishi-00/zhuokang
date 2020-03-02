package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.EvaModelGuideDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.EvaModelGuide;
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
public interface EvaModelGuideMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(time, type, version);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<EvaModelGuide> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<EvaModelGuide> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EvaModelGuideResult")
    Optional<EvaModelGuide> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EvaModelGuideResult", value = {
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER)
    })
    List<EvaModelGuide> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, evaModelGuide, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, evaModelGuide, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer time_, String type_) {
        return delete(c -> 
            c.where(time, isEqualTo(time_))
            .and(type, isEqualTo(type_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(EvaModelGuide record) {
        return MyBatis3Utils.insert(this::insert, record, evaModelGuide, c ->
            c.map(time).toProperty("time")
            .map(type).toProperty("type")
            .map(version).toProperty("version")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<EvaModelGuide> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, evaModelGuide, c ->
            c.map(time).toProperty("time")
            .map(type).toProperty("type")
            .map(version).toProperty("version")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(EvaModelGuide record) {
        return MyBatis3Utils.insert(this::insert, record, evaModelGuide, c ->
            c.map(time).toPropertyWhenPresent("time", record::getTime)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(version).toPropertyWhenPresent("version", record::getVersion)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaModelGuide> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, evaModelGuide, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaModelGuide> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, evaModelGuide, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaModelGuide> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, evaModelGuide, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaModelGuide> selectByPrimaryKey(Integer time_, String type_) {
        return selectOne(c ->
            c.where(time, isEqualTo(time_))
            .and(type, isEqualTo(type_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, evaModelGuide, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(EvaModelGuide record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(time).equalTo(record::getTime)
                .set(type).equalTo(record::getType)
                .set(version).equalTo(record::getVersion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(EvaModelGuide record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(time).equalToWhenPresent(record::getTime)
                .set(type).equalToWhenPresent(record::getType)
                .set(version).equalToWhenPresent(record::getVersion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(EvaModelGuide record) {
        return update(c ->
            c.set(version).equalTo(record::getVersion)
            .where(time, isEqualTo(record::getTime))
            .and(type, isEqualTo(record::getType))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(EvaModelGuide record) {
        return update(c ->
            c.set(version).equalToWhenPresent(record::getVersion)
            .where(time, isEqualTo(record::getTime))
            .and(type, isEqualTo(record::getType))
        );
    }
}