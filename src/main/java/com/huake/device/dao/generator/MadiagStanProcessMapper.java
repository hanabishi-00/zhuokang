package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.MadiagStanProcessDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.MadiagStanProcess;
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
public interface MadiagStanProcessMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(measId, unitId, equipPro, stepId, measName, proInt);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<MadiagStanProcess> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<MadiagStanProcess> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MadiagStanProcessResult")
    Optional<MadiagStanProcess> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MadiagStanProcessResult", value = {
        @Result(column="meas_id", property="measId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="unit_id", property="unitId", jdbcType=JdbcType.INTEGER),
        @Result(column="equip_pro", property="equipPro", jdbcType=JdbcType.VARCHAR),
        @Result(column="step_id", property="stepId", jdbcType=JdbcType.INTEGER),
        @Result(column="meas_name", property="measName", jdbcType=JdbcType.VARCHAR),
        @Result(column="pro_int", property="proInt", jdbcType=JdbcType.INTEGER)
    })
    List<MadiagStanProcess> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, madiagStanProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, madiagStanProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer measId_) {
        return delete(c -> 
            c.where(measId, isEqualTo(measId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(MadiagStanProcess record) {
        return MyBatis3Utils.insert(this::insert, record, madiagStanProcess, c ->
            c.map(measId).toProperty("measId")
            .map(unitId).toProperty("unitId")
            .map(equipPro).toProperty("equipPro")
            .map(stepId).toProperty("stepId")
            .map(measName).toProperty("measName")
            .map(proInt).toProperty("proInt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<MadiagStanProcess> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, madiagStanProcess, c ->
            c.map(measId).toProperty("measId")
            .map(unitId).toProperty("unitId")
            .map(equipPro).toProperty("equipPro")
            .map(stepId).toProperty("stepId")
            .map(measName).toProperty("measName")
            .map(proInt).toProperty("proInt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(MadiagStanProcess record) {
        return MyBatis3Utils.insert(this::insert, record, madiagStanProcess, c ->
            c.map(measId).toPropertyWhenPresent("measId", record::getMeasId)
            .map(unitId).toPropertyWhenPresent("unitId", record::getUnitId)
            .map(equipPro).toPropertyWhenPresent("equipPro", record::getEquipPro)
            .map(stepId).toPropertyWhenPresent("stepId", record::getStepId)
            .map(measName).toPropertyWhenPresent("measName", record::getMeasName)
            .map(proInt).toPropertyWhenPresent("proInt", record::getProInt)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<MadiagStanProcess> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, madiagStanProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<MadiagStanProcess> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, madiagStanProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<MadiagStanProcess> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, madiagStanProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<MadiagStanProcess> selectByPrimaryKey(Integer measId_) {
        return selectOne(c ->
            c.where(measId, isEqualTo(measId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, madiagStanProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(MadiagStanProcess record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(measId).equalTo(record::getMeasId)
                .set(unitId).equalTo(record::getUnitId)
                .set(equipPro).equalTo(record::getEquipPro)
                .set(stepId).equalTo(record::getStepId)
                .set(measName).equalTo(record::getMeasName)
                .set(proInt).equalTo(record::getProInt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MadiagStanProcess record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(measId).equalToWhenPresent(record::getMeasId)
                .set(unitId).equalToWhenPresent(record::getUnitId)
                .set(equipPro).equalToWhenPresent(record::getEquipPro)
                .set(stepId).equalToWhenPresent(record::getStepId)
                .set(measName).equalToWhenPresent(record::getMeasName)
                .set(proInt).equalToWhenPresent(record::getProInt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(MadiagStanProcess record) {
        return update(c ->
            c.set(unitId).equalTo(record::getUnitId)
            .set(equipPro).equalTo(record::getEquipPro)
            .set(stepId).equalTo(record::getStepId)
            .set(measName).equalTo(record::getMeasName)
            .set(proInt).equalTo(record::getProInt)
            .where(measId, isEqualTo(record::getMeasId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(MadiagStanProcess record) {
        return update(c ->
            c.set(unitId).equalToWhenPresent(record::getUnitId)
            .set(equipPro).equalToWhenPresent(record::getEquipPro)
            .set(stepId).equalToWhenPresent(record::getStepId)
            .set(measName).equalToWhenPresent(record::getMeasName)
            .set(proInt).equalToWhenPresent(record::getProInt)
            .where(measId, isEqualTo(record::getMeasId))
        );
    }
}