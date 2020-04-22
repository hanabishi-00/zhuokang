package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.ClassifiedqueryprefixDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.Classifiedqueryprefix;
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
public interface ClassifiedqueryprefixMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, classifiedid, prefix);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Classifiedqueryprefix> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Classifiedqueryprefix> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ClassifiedqueryprefixResult")
    Optional<Classifiedqueryprefix> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ClassifiedqueryprefixResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="classifiedid", property="classifiedid", jdbcType=JdbcType.INTEGER),
        @Result(column="prefix", property="prefix", jdbcType=JdbcType.VARCHAR)
    })
    List<Classifiedqueryprefix> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, classifiedqueryprefix, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, classifiedqueryprefix, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Classifiedqueryprefix record) {
        return MyBatis3Utils.insert(this::insert, record, classifiedqueryprefix, c ->
            c.map(id).toProperty("id")
            .map(classifiedid).toProperty("classifiedid")
            .map(prefix).toProperty("prefix")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Classifiedqueryprefix> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, classifiedqueryprefix, c ->
            c.map(id).toProperty("id")
            .map(classifiedid).toProperty("classifiedid")
            .map(prefix).toProperty("prefix")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Classifiedqueryprefix record) {
        return MyBatis3Utils.insert(this::insert, record, classifiedqueryprefix, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(classifiedid).toPropertyWhenPresent("classifiedid", record::getClassifiedid)
            .map(prefix).toPropertyWhenPresent("prefix", record::getPrefix)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Classifiedqueryprefix> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, classifiedqueryprefix, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Classifiedqueryprefix> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, classifiedqueryprefix, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Classifiedqueryprefix> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, classifiedqueryprefix, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Classifiedqueryprefix> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, classifiedqueryprefix, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Classifiedqueryprefix record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(classifiedid).equalTo(record::getClassifiedid)
                .set(prefix).equalTo(record::getPrefix);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Classifiedqueryprefix record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(classifiedid).equalToWhenPresent(record::getClassifiedid)
                .set(prefix).equalToWhenPresent(record::getPrefix);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Classifiedqueryprefix record) {
        return update(c ->
            c.set(classifiedid).equalTo(record::getClassifiedid)
            .set(prefix).equalTo(record::getPrefix)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Classifiedqueryprefix record) {
        return update(c ->
            c.set(classifiedid).equalToWhenPresent(record::getClassifiedid)
            .set(prefix).equalToWhenPresent(record::getPrefix)
            .where(id, isEqualTo(record::getId))
        );
    }
}