package org.linlinjava.device.dao;

import static org.linlinjava.device.dao.Bool826201802DynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

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
import org.linlinjava.device.domain.Bool826201802;
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
public interface Bool826201802Mapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(t, v, f);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Bool826201802> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Bool826201802> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("Bool826201802Result")
    Optional<Bool826201802> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="Bool826201802Result", value = {
        @Result(column="t", property="t", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="v", property="v", jdbcType=JdbcType.TINYINT),
        @Result(column="f", property="f", jdbcType=JdbcType.TINYINT)
    })
    List<Bool826201802> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, bool826201802, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, bool826201802, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer t_) {
        return delete(c -> 
            c.where(t, isEqualTo(t_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Bool826201802 record) {
        return MyBatis3Utils.insert(this::insert, record, bool826201802, c ->
            c.map(t).toProperty("t")
            .map(v).toProperty("v")
            .map(f).toProperty("f")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Bool826201802> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, bool826201802, c ->
            c.map(t).toProperty("t")
            .map(v).toProperty("v")
            .map(f).toProperty("f")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Bool826201802 record) {
        return MyBatis3Utils.insert(this::insert, record, bool826201802, c ->
            c.map(t).toPropertyWhenPresent("t", record::getT)
            .map(v).toPropertyWhenPresent("v", record::getV)
            .map(f).toPropertyWhenPresent("f", record::getF)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Bool826201802> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, bool826201802, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Bool826201802> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, bool826201802, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Bool826201802> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, bool826201802, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Bool826201802> selectByPrimaryKey(Integer t_) {
        return selectOne(c ->
            c.where(t, isEqualTo(t_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, bool826201802, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Bool826201802 record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(t).equalTo(record::getT)
                .set(v).equalTo(record::getV)
                .set(f).equalTo(record::getF);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Bool826201802 record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(t).equalToWhenPresent(record::getT)
                .set(v).equalToWhenPresent(record::getV)
                .set(f).equalToWhenPresent(record::getF);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Bool826201802 record) {
        return update(c ->
            c.set(v).equalTo(record::getV)
            .set(f).equalTo(record::getF)
            .where(t, isEqualTo(record::getT))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Bool826201802 record) {
        return update(c ->
            c.set(v).equalToWhenPresent(record::getV)
            .set(f).equalToWhenPresent(record::getF)
            .where(t, isEqualTo(record::getT))
        );
    }
}