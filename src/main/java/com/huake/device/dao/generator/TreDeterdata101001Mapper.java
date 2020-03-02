package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.TreDeterdata101001DynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.TreDeterdata101001;
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
public interface TreDeterdata101001Mapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(time, deter);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TreDeterdata101001> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TreDeterdata101001> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TreDeterdata101001Result")
    Optional<TreDeterdata101001> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TreDeterdata101001Result", value = {
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="deter", property="deter", jdbcType=JdbcType.REAL)
    })
    List<TreDeterdata101001> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, treDeterdata101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, treDeterdata101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer time_) {
        return delete(c -> 
            c.where(time, isEqualTo(time_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TreDeterdata101001 record) {
        return MyBatis3Utils.insert(this::insert, record, treDeterdata101001, c ->
            c.map(time).toProperty("time")
            .map(deter).toProperty("deter")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TreDeterdata101001> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, treDeterdata101001, c ->
            c.map(time).toProperty("time")
            .map(deter).toProperty("deter")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TreDeterdata101001 record) {
        return MyBatis3Utils.insert(this::insert, record, treDeterdata101001, c ->
            c.map(time).toPropertyWhenPresent("time", record::getTime)
            .map(deter).toPropertyWhenPresent("deter", record::getDeter)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreDeterdata101001> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, treDeterdata101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreDeterdata101001> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, treDeterdata101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreDeterdata101001> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, treDeterdata101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreDeterdata101001> selectByPrimaryKey(Integer time_) {
        return selectOne(c ->
            c.where(time, isEqualTo(time_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, treDeterdata101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TreDeterdata101001 record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(time).equalTo(record::getTime)
                .set(deter).equalTo(record::getDeter);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TreDeterdata101001 record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(time).equalToWhenPresent(record::getTime)
                .set(deter).equalToWhenPresent(record::getDeter);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TreDeterdata101001 record) {
        return update(c ->
            c.set(deter).equalTo(record::getDeter)
            .where(time, isEqualTo(record::getTime))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TreDeterdata101001 record) {
        return update(c ->
            c.set(deter).equalToWhenPresent(record::getDeter)
            .where(time, isEqualTo(record::getTime))
        );
    }
}