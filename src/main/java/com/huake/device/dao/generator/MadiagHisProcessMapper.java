package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.MadiagHisProcessDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.MadiagHisProcess;
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
public interface MadiagHisProcessMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, unitId, equipPro, time);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<MadiagHisProcess> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<MadiagHisProcess> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MadiagHisProcessResult")
    Optional<MadiagHisProcess> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MadiagHisProcessResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="unit_id", property="unitId", jdbcType=JdbcType.INTEGER),
        @Result(column="equip_pro", property="equipPro", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.VARCHAR)
    })
    List<MadiagHisProcess> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, madiagHisProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, madiagHisProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(MadiagHisProcess record) {
        return MyBatis3Utils.insert(this::insert, record, madiagHisProcess, c ->
            c.map(id).toProperty("id")
            .map(unitId).toProperty("unitId")
            .map(equipPro).toProperty("equipPro")
            .map(time).toProperty("time")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<MadiagHisProcess> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, madiagHisProcess, c ->
            c.map(id).toProperty("id")
            .map(unitId).toProperty("unitId")
            .map(equipPro).toProperty("equipPro")
            .map(time).toProperty("time")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(MadiagHisProcess record) {
        return MyBatis3Utils.insert(this::insert, record, madiagHisProcess, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(unitId).toPropertyWhenPresent("unitId", record::getUnitId)
            .map(equipPro).toPropertyWhenPresent("equipPro", record::getEquipPro)
            .map(time).toPropertyWhenPresent("time", record::getTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<MadiagHisProcess> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, madiagHisProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<MadiagHisProcess> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, madiagHisProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<MadiagHisProcess> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, madiagHisProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<MadiagHisProcess> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, madiagHisProcess, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(MadiagHisProcess record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(unitId).equalTo(record::getUnitId)
                .set(equipPro).equalTo(record::getEquipPro)
                .set(time).equalTo(record::getTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MadiagHisProcess record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(unitId).equalToWhenPresent(record::getUnitId)
                .set(equipPro).equalToWhenPresent(record::getEquipPro)
                .set(time).equalToWhenPresent(record::getTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(MadiagHisProcess record) {
        return update(c ->
            c.set(unitId).equalTo(record::getUnitId)
            .set(equipPro).equalTo(record::getEquipPro)
            .set(time).equalTo(record::getTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(MadiagHisProcess record) {
        return update(c ->
            c.set(unitId).equalToWhenPresent(record::getUnitId)
            .set(equipPro).equalToWhenPresent(record::getEquipPro)
            .set(time).equalToWhenPresent(record::getTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}