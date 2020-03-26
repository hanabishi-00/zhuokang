package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.MadiagConResDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.MadiagConRes;
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
public interface MadiagConResMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, openCon, operPro, conproInt);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<MadiagConRes> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<MadiagConRes> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MadiagConResResult")
    Optional<MadiagConRes> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MadiagConResResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="open_con", property="openCon", jdbcType=JdbcType.VARCHAR),
        @Result(column="oper_pro", property="operPro", jdbcType=JdbcType.VARCHAR),
        @Result(column="conpro_int", property="conproInt", jdbcType=JdbcType.VARCHAR)
    })
    List<MadiagConRes> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, madiagConRes, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, madiagConRes, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(MadiagConRes record) {
        return MyBatis3Utils.insert(this::insert, record, madiagConRes, c ->
            c.map(id).toProperty("id")
            .map(openCon).toProperty("openCon")
            .map(operPro).toProperty("operPro")
            .map(conproInt).toProperty("conproInt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<MadiagConRes> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, madiagConRes, c ->
            c.map(id).toProperty("id")
            .map(openCon).toProperty("openCon")
            .map(operPro).toProperty("operPro")
            .map(conproInt).toProperty("conproInt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(MadiagConRes record) {
        return MyBatis3Utils.insert(this::insert, record, madiagConRes, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(openCon).toPropertyWhenPresent("openCon", record::getOpenCon)
            .map(operPro).toPropertyWhenPresent("operPro", record::getOperPro)
            .map(conproInt).toPropertyWhenPresent("conproInt", record::getConproInt)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<MadiagConRes> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, madiagConRes, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<MadiagConRes> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, madiagConRes, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<MadiagConRes> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, madiagConRes, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<MadiagConRes> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, madiagConRes, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(MadiagConRes record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(openCon).equalTo(record::getOpenCon)
                .set(operPro).equalTo(record::getOperPro)
                .set(conproInt).equalTo(record::getConproInt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MadiagConRes record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(openCon).equalToWhenPresent(record::getOpenCon)
                .set(operPro).equalToWhenPresent(record::getOperPro)
                .set(conproInt).equalToWhenPresent(record::getConproInt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(MadiagConRes record) {
        return update(c ->
            c.set(openCon).equalTo(record::getOpenCon)
            .set(operPro).equalTo(record::getOperPro)
            .set(conproInt).equalTo(record::getConproInt)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(MadiagConRes record) {
        return update(c ->
            c.set(openCon).equalToWhenPresent(record::getOpenCon)
            .set(operPro).equalToWhenPresent(record::getOperPro)
            .set(conproInt).equalToWhenPresent(record::getConproInt)
            .where(id, isEqualTo(record::getId))
        );
    }
}