package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.TreDeterdataDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.TreDeterdata;
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
public interface TreDeterdataMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(runtime, time, deter, idVersion);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TreDeterdata> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TreDeterdata> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TreDeterdataResult")
    Optional<TreDeterdata> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TreDeterdataResult", value = {
        @Result(column="runtime", property="runtime", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER),
        @Result(column="deter", property="deter", jdbcType=JdbcType.REAL),
        @Result(column="id_version", property="idVersion", jdbcType=JdbcType.VARCHAR)
    })
    List<TreDeterdata> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, treDeterdata, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, treDeterdata, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer runtime_) {
        return delete(c -> 
            c.where(runtime, isEqualTo(runtime_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TreDeterdata record) {
        return MyBatis3Utils.insert(this::insert, record, treDeterdata, c ->
            c.map(runtime).toProperty("runtime")
            .map(time).toProperty("time")
            .map(deter).toProperty("deter")
            .map(idVersion).toProperty("idVersion")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TreDeterdata> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, treDeterdata, c ->
            c.map(runtime).toProperty("runtime")
            .map(time).toProperty("time")
            .map(deter).toProperty("deter")
            .map(idVersion).toProperty("idVersion")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TreDeterdata record) {
        return MyBatis3Utils.insert(this::insert, record, treDeterdata, c ->
            c.map(runtime).toPropertyWhenPresent("runtime", record::getRuntime)
            .map(time).toPropertyWhenPresent("time", record::getTime)
            .map(deter).toPropertyWhenPresent("deter", record::getDeter)
            .map(idVersion).toPropertyWhenPresent("idVersion", record::getIdVersion)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreDeterdata> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, treDeterdata, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreDeterdata> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, treDeterdata, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreDeterdata> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, treDeterdata, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreDeterdata> selectByPrimaryKey(Integer runtime_) {
        return selectOne(c ->
            c.where(runtime, isEqualTo(runtime_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, treDeterdata, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TreDeterdata record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(runtime).equalTo(record::getRuntime)
                .set(time).equalTo(record::getTime)
                .set(deter).equalTo(record::getDeter)
                .set(idVersion).equalTo(record::getIdVersion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TreDeterdata record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(runtime).equalToWhenPresent(record::getRuntime)
                .set(time).equalToWhenPresent(record::getTime)
                .set(deter).equalToWhenPresent(record::getDeter)
                .set(idVersion).equalToWhenPresent(record::getIdVersion);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TreDeterdata record) {
        return update(c ->
            c.set(time).equalTo(record::getTime)
            .set(deter).equalTo(record::getDeter)
            .set(idVersion).equalTo(record::getIdVersion)
            .where(runtime, isEqualTo(record::getRuntime))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TreDeterdata record) {
        return update(c ->
            c.set(time).equalToWhenPresent(record::getTime)
            .set(deter).equalToWhenPresent(record::getDeter)
            .set(idVersion).equalToWhenPresent(record::getIdVersion)
            .where(runtime, isEqualTo(record::getRuntime))
        );
    }
}