package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.HdbidDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.Hdbid;
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
public interface HdbidMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, hdbid.hdbid, name, datatype);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Hdbid> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Hdbid> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("HdbidResult")
    Optional<Hdbid> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="HdbidResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="hdbId", property="hdbid", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="datatype", property="datatype", jdbcType=JdbcType.INTEGER)
    })
    List<Hdbid> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, hdbid, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, hdbid, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Hdbid record) {
        return MyBatis3Utils.insert(this::insert, record, hdbid, c ->
            c.map(id).toProperty("id")
            .map(hdbid.hdbid).toProperty("hdbid")
            .map(name).toProperty("name")
            .map(datatype).toProperty("datatype")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Hdbid> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, hdbid, c ->
            c.map(id).toProperty("id")
            .map(hdbid.hdbid).toProperty("hdbid")
            .map(name).toProperty("name")
            .map(datatype).toProperty("datatype")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Hdbid record) {
        return MyBatis3Utils.insert(this::insert, record, hdbid, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(hdbid.hdbid).toPropertyWhenPresent("hdbid", record::getHdbid)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(datatype).toPropertyWhenPresent("datatype", record::getDatatype)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Hdbid> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, hdbid, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Hdbid> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, hdbid, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Hdbid> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, hdbid, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Hdbid> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, hdbid, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Hdbid record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(hdbid.hdbid).equalTo(record::getHdbid)
                .set(name).equalTo(record::getName)
                .set(datatype).equalTo(record::getDatatype);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Hdbid record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(hdbid.hdbid).equalToWhenPresent(record::getHdbid)
                .set(name).equalToWhenPresent(record::getName)
                .set(datatype).equalToWhenPresent(record::getDatatype);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Hdbid record) {
        return update(c ->
            c.set(hdbid.hdbid).equalTo(record::getHdbid)
            .set(name).equalTo(record::getName)
            .set(datatype).equalTo(record::getDatatype)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Hdbid record) {
        return update(c ->
            c.set(hdbid.hdbid).equalToWhenPresent(record::getHdbid)
            .set(name).equalToWhenPresent(record::getName)
            .set(datatype).equalToWhenPresent(record::getDatatype)
            .where(id, isEqualTo(record::getId))
        );
    }
}