package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.MadiagSingHisDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.MadiagSingHis;
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
public interface MadiagSingHisMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, hisId, histtepId, hismeasName, hisproInt);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<MadiagSingHis> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<MadiagSingHis> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MadiagSingHisResult")
    Optional<MadiagSingHis> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MadiagSingHisResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="his_id", property="hisId", jdbcType=JdbcType.INTEGER),
        @Result(column="histtep_id", property="histtepId", jdbcType=JdbcType.INTEGER),
        @Result(column="hismeas_name", property="hismeasName", jdbcType=JdbcType.VARCHAR),
        @Result(column="hispro_int", property="hisproInt", jdbcType=JdbcType.INTEGER)
    })
    List<MadiagSingHis> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, madiagSingHis, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, madiagSingHis, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(MadiagSingHis record) {
        return MyBatis3Utils.insert(this::insert, record, madiagSingHis, c ->
            c.map(id).toProperty("id")
            .map(hisId).toProperty("hisId")
            .map(histtepId).toProperty("histtepId")
            .map(hismeasName).toProperty("hismeasName")
            .map(hisproInt).toProperty("hisproInt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<MadiagSingHis> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, madiagSingHis, c ->
            c.map(id).toProperty("id")
            .map(hisId).toProperty("hisId")
            .map(histtepId).toProperty("histtepId")
            .map(hismeasName).toProperty("hismeasName")
            .map(hisproInt).toProperty("hisproInt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(MadiagSingHis record) {
        return MyBatis3Utils.insert(this::insert, record, madiagSingHis, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(hisId).toPropertyWhenPresent("hisId", record::getHisId)
            .map(histtepId).toPropertyWhenPresent("histtepId", record::getHisttepId)
            .map(hismeasName).toPropertyWhenPresent("hismeasName", record::getHismeasName)
            .map(hisproInt).toPropertyWhenPresent("hisproInt", record::getHisproInt)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<MadiagSingHis> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, madiagSingHis, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<MadiagSingHis> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, madiagSingHis, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<MadiagSingHis> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, madiagSingHis, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<MadiagSingHis> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, madiagSingHis, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(MadiagSingHis record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(hisId).equalTo(record::getHisId)
                .set(histtepId).equalTo(record::getHisttepId)
                .set(hismeasName).equalTo(record::getHismeasName)
                .set(hisproInt).equalTo(record::getHisproInt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MadiagSingHis record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(hisId).equalToWhenPresent(record::getHisId)
                .set(histtepId).equalToWhenPresent(record::getHisttepId)
                .set(hismeasName).equalToWhenPresent(record::getHismeasName)
                .set(hisproInt).equalToWhenPresent(record::getHisproInt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(MadiagSingHis record) {
        return update(c ->
            c.set(hisId).equalTo(record::getHisId)
            .set(histtepId).equalTo(record::getHisttepId)
            .set(hismeasName).equalTo(record::getHismeasName)
            .set(hisproInt).equalTo(record::getHisproInt)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(MadiagSingHis record) {
        return update(c ->
            c.set(hisId).equalToWhenPresent(record::getHisId)
            .set(histtepId).equalToWhenPresent(record::getHisttepId)
            .set(hismeasName).equalToWhenPresent(record::getHismeasName)
            .set(hisproInt).equalToWhenPresent(record::getHisproInt)
            .where(id, isEqualTo(record::getId))
        );
    }
}