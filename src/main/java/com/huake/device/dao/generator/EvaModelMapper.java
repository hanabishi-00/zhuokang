package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.EvaModelDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.EvaModel;
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
public interface EvaModelMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(time, type, id, name, father, weight, state, stanDescription, points);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<EvaModel> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<EvaModel> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EvaModelResult")
    Optional<EvaModel> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EvaModelResult", value = {
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="father", property="father", jdbcType=JdbcType.INTEGER),
        @Result(column="weight", property="weight", jdbcType=JdbcType.TINYINT),
        @Result(column="state", property="state", jdbcType=JdbcType.BIT),
        @Result(column="stan_description", property="stanDescription", jdbcType=JdbcType.VARCHAR),
        @Result(column="points", property="points", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<EvaModel> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, evaModel, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, evaModel, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer time_, String type_, Integer id_) {
        return delete(c -> 
            c.where(time, isEqualTo(time_))
            .and(type, isEqualTo(type_))
            .and(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(EvaModel record) {
        return MyBatis3Utils.insert(this::insert, record, evaModel, c ->
            c.map(time).toProperty("time")
            .map(type).toProperty("type")
            .map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(father).toProperty("father")
            .map(weight).toProperty("weight")
            .map(state).toProperty("state")
            .map(stanDescription).toProperty("stanDescription")
            .map(points).toProperty("points")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<EvaModel> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, evaModel, c ->
            c.map(time).toProperty("time")
            .map(type).toProperty("type")
            .map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(father).toProperty("father")
            .map(weight).toProperty("weight")
            .map(state).toProperty("state")
            .map(stanDescription).toProperty("stanDescription")
            .map(points).toProperty("points")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(EvaModel record) {
        return MyBatis3Utils.insert(this::insert, record, evaModel, c ->
            c.map(time).toPropertyWhenPresent("time", record::getTime)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(id).toPropertyWhenPresent("id", record::getId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(father).toPropertyWhenPresent("father", record::getFather)
            .map(weight).toPropertyWhenPresent("weight", record::getWeight)
            .map(state).toPropertyWhenPresent("state", record::getState)
            .map(stanDescription).toPropertyWhenPresent("stanDescription", record::getStanDescription)
            .map(points).toPropertyWhenPresent("points", record::getPoints)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaModel> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, evaModel, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaModel> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, evaModel, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaModel> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, evaModel, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaModel> selectByPrimaryKey(Integer time_, String type_, Integer id_) {
        return selectOne(c ->
            c.where(time, isEqualTo(time_))
            .and(type, isEqualTo(type_))
            .and(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, evaModel, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(EvaModel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(time).equalTo(record::getTime)
                .set(type).equalTo(record::getType)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(father).equalTo(record::getFather)
                .set(weight).equalTo(record::getWeight)
                .set(state).equalTo(record::getState)
                .set(stanDescription).equalTo(record::getStanDescription)
                .set(points).equalTo(record::getPoints);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(EvaModel record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(time).equalToWhenPresent(record::getTime)
                .set(type).equalToWhenPresent(record::getType)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(father).equalToWhenPresent(record::getFather)
                .set(weight).equalToWhenPresent(record::getWeight)
                .set(state).equalToWhenPresent(record::getState)
                .set(stanDescription).equalToWhenPresent(record::getStanDescription)
                .set(points).equalToWhenPresent(record::getPoints);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(EvaModel record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(father).equalTo(record::getFather)
            .set(weight).equalTo(record::getWeight)
            .set(state).equalTo(record::getState)
            .set(stanDescription).equalTo(record::getStanDescription)
            .set(points).equalTo(record::getPoints)
            .where(time, isEqualTo(record::getTime))
            .and(type, isEqualTo(record::getType))
            .and(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(EvaModel record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(father).equalToWhenPresent(record::getFather)
            .set(weight).equalToWhenPresent(record::getWeight)
            .set(state).equalToWhenPresent(record::getState)
            .set(stanDescription).equalToWhenPresent(record::getStanDescription)
            .set(points).equalToWhenPresent(record::getPoints)
            .where(time, isEqualTo(record::getTime))
            .and(type, isEqualTo(record::getType))
            .and(id, isEqualTo(record::getId))
        );
    }
}