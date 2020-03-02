package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.EvaResTur20200001LeafOnlineDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.EvaResTur20200001LeafOnline;
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
public interface EvaResTur20200001LeafOnlineMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(time, unit, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23, v24);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<EvaResTur20200001LeafOnline> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<EvaResTur20200001LeafOnline> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EvaResTur20200001LeafOnlineResult")
    Optional<EvaResTur20200001LeafOnline> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EvaResTur20200001LeafOnlineResult", value = {
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="unit", property="unit", jdbcType=JdbcType.TINYINT, id=true),
        @Result(column="v1", property="v1", jdbcType=JdbcType.REAL),
        @Result(column="v2", property="v2", jdbcType=JdbcType.REAL),
        @Result(column="v3", property="v3", jdbcType=JdbcType.REAL),
        @Result(column="v4", property="v4", jdbcType=JdbcType.REAL),
        @Result(column="v5", property="v5", jdbcType=JdbcType.REAL),
        @Result(column="v6", property="v6", jdbcType=JdbcType.REAL),
        @Result(column="v7", property="v7", jdbcType=JdbcType.REAL),
        @Result(column="v8", property="v8", jdbcType=JdbcType.REAL),
        @Result(column="v9", property="v9", jdbcType=JdbcType.REAL),
        @Result(column="v10", property="v10", jdbcType=JdbcType.REAL),
        @Result(column="v11", property="v11", jdbcType=JdbcType.REAL),
        @Result(column="v12", property="v12", jdbcType=JdbcType.REAL),
        @Result(column="v13", property="v13", jdbcType=JdbcType.REAL),
        @Result(column="v14", property="v14", jdbcType=JdbcType.REAL),
        @Result(column="v15", property="v15", jdbcType=JdbcType.REAL),
        @Result(column="v16", property="v16", jdbcType=JdbcType.REAL),
        @Result(column="v17", property="v17", jdbcType=JdbcType.REAL),
        @Result(column="v18", property="v18", jdbcType=JdbcType.REAL),
        @Result(column="v19", property="v19", jdbcType=JdbcType.REAL),
        @Result(column="v20", property="v20", jdbcType=JdbcType.REAL),
        @Result(column="v21", property="v21", jdbcType=JdbcType.REAL),
        @Result(column="v22", property="v22", jdbcType=JdbcType.REAL),
        @Result(column="v23", property="v23", jdbcType=JdbcType.REAL),
        @Result(column="v24", property="v24", jdbcType=JdbcType.REAL)
    })
    List<EvaResTur20200001LeafOnline> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, evaResTur20200001LeafOnline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, evaResTur20200001LeafOnline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer time_, Byte unit_) {
        return delete(c -> 
            c.where(time, isEqualTo(time_))
            .and(unit, isEqualTo(unit_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(EvaResTur20200001LeafOnline record) {
        return MyBatis3Utils.insert(this::insert, record, evaResTur20200001LeafOnline, c ->
            c.map(time).toProperty("time")
            .map(unit).toProperty("unit")
            .map(v1).toProperty("v1")
            .map(v2).toProperty("v2")
            .map(v3).toProperty("v3")
            .map(v4).toProperty("v4")
            .map(v5).toProperty("v5")
            .map(v6).toProperty("v6")
            .map(v7).toProperty("v7")
            .map(v8).toProperty("v8")
            .map(v9).toProperty("v9")
            .map(v10).toProperty("v10")
            .map(v11).toProperty("v11")
            .map(v12).toProperty("v12")
            .map(v13).toProperty("v13")
            .map(v14).toProperty("v14")
            .map(v15).toProperty("v15")
            .map(v16).toProperty("v16")
            .map(v17).toProperty("v17")
            .map(v18).toProperty("v18")
            .map(v19).toProperty("v19")
            .map(v20).toProperty("v20")
            .map(v21).toProperty("v21")
            .map(v22).toProperty("v22")
            .map(v23).toProperty("v23")
            .map(v24).toProperty("v24")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<EvaResTur20200001LeafOnline> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, evaResTur20200001LeafOnline, c ->
            c.map(time).toProperty("time")
            .map(unit).toProperty("unit")
            .map(v1).toProperty("v1")
            .map(v2).toProperty("v2")
            .map(v3).toProperty("v3")
            .map(v4).toProperty("v4")
            .map(v5).toProperty("v5")
            .map(v6).toProperty("v6")
            .map(v7).toProperty("v7")
            .map(v8).toProperty("v8")
            .map(v9).toProperty("v9")
            .map(v10).toProperty("v10")
            .map(v11).toProperty("v11")
            .map(v12).toProperty("v12")
            .map(v13).toProperty("v13")
            .map(v14).toProperty("v14")
            .map(v15).toProperty("v15")
            .map(v16).toProperty("v16")
            .map(v17).toProperty("v17")
            .map(v18).toProperty("v18")
            .map(v19).toProperty("v19")
            .map(v20).toProperty("v20")
            .map(v21).toProperty("v21")
            .map(v22).toProperty("v22")
            .map(v23).toProperty("v23")
            .map(v24).toProperty("v24")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(EvaResTur20200001LeafOnline record) {
        return MyBatis3Utils.insert(this::insert, record, evaResTur20200001LeafOnline, c ->
            c.map(time).toPropertyWhenPresent("time", record::getTime)
            .map(unit).toPropertyWhenPresent("unit", record::getUnit)
            .map(v1).toPropertyWhenPresent("v1", record::getV1)
            .map(v2).toPropertyWhenPresent("v2", record::getV2)
            .map(v3).toPropertyWhenPresent("v3", record::getV3)
            .map(v4).toPropertyWhenPresent("v4", record::getV4)
            .map(v5).toPropertyWhenPresent("v5", record::getV5)
            .map(v6).toPropertyWhenPresent("v6", record::getV6)
            .map(v7).toPropertyWhenPresent("v7", record::getV7)
            .map(v8).toPropertyWhenPresent("v8", record::getV8)
            .map(v9).toPropertyWhenPresent("v9", record::getV9)
            .map(v10).toPropertyWhenPresent("v10", record::getV10)
            .map(v11).toPropertyWhenPresent("v11", record::getV11)
            .map(v12).toPropertyWhenPresent("v12", record::getV12)
            .map(v13).toPropertyWhenPresent("v13", record::getV13)
            .map(v14).toPropertyWhenPresent("v14", record::getV14)
            .map(v15).toPropertyWhenPresent("v15", record::getV15)
            .map(v16).toPropertyWhenPresent("v16", record::getV16)
            .map(v17).toPropertyWhenPresent("v17", record::getV17)
            .map(v18).toPropertyWhenPresent("v18", record::getV18)
            .map(v19).toPropertyWhenPresent("v19", record::getV19)
            .map(v20).toPropertyWhenPresent("v20", record::getV20)
            .map(v21).toPropertyWhenPresent("v21", record::getV21)
            .map(v22).toPropertyWhenPresent("v22", record::getV22)
            .map(v23).toPropertyWhenPresent("v23", record::getV23)
            .map(v24).toPropertyWhenPresent("v24", record::getV24)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaResTur20200001LeafOnline> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, evaResTur20200001LeafOnline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaResTur20200001LeafOnline> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, evaResTur20200001LeafOnline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaResTur20200001LeafOnline> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, evaResTur20200001LeafOnline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaResTur20200001LeafOnline> selectByPrimaryKey(Integer time_, Byte unit_) {
        return selectOne(c ->
            c.where(time, isEqualTo(time_))
            .and(unit, isEqualTo(unit_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, evaResTur20200001LeafOnline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(EvaResTur20200001LeafOnline record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(time).equalTo(record::getTime)
                .set(unit).equalTo(record::getUnit)
                .set(v1).equalTo(record::getV1)
                .set(v2).equalTo(record::getV2)
                .set(v3).equalTo(record::getV3)
                .set(v4).equalTo(record::getV4)
                .set(v5).equalTo(record::getV5)
                .set(v6).equalTo(record::getV6)
                .set(v7).equalTo(record::getV7)
                .set(v8).equalTo(record::getV8)
                .set(v9).equalTo(record::getV9)
                .set(v10).equalTo(record::getV10)
                .set(v11).equalTo(record::getV11)
                .set(v12).equalTo(record::getV12)
                .set(v13).equalTo(record::getV13)
                .set(v14).equalTo(record::getV14)
                .set(v15).equalTo(record::getV15)
                .set(v16).equalTo(record::getV16)
                .set(v17).equalTo(record::getV17)
                .set(v18).equalTo(record::getV18)
                .set(v19).equalTo(record::getV19)
                .set(v20).equalTo(record::getV20)
                .set(v21).equalTo(record::getV21)
                .set(v22).equalTo(record::getV22)
                .set(v23).equalTo(record::getV23)
                .set(v24).equalTo(record::getV24);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(EvaResTur20200001LeafOnline record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(time).equalToWhenPresent(record::getTime)
                .set(unit).equalToWhenPresent(record::getUnit)
                .set(v1).equalToWhenPresent(record::getV1)
                .set(v2).equalToWhenPresent(record::getV2)
                .set(v3).equalToWhenPresent(record::getV3)
                .set(v4).equalToWhenPresent(record::getV4)
                .set(v5).equalToWhenPresent(record::getV5)
                .set(v6).equalToWhenPresent(record::getV6)
                .set(v7).equalToWhenPresent(record::getV7)
                .set(v8).equalToWhenPresent(record::getV8)
                .set(v9).equalToWhenPresent(record::getV9)
                .set(v10).equalToWhenPresent(record::getV10)
                .set(v11).equalToWhenPresent(record::getV11)
                .set(v12).equalToWhenPresent(record::getV12)
                .set(v13).equalToWhenPresent(record::getV13)
                .set(v14).equalToWhenPresent(record::getV14)
                .set(v15).equalToWhenPresent(record::getV15)
                .set(v16).equalToWhenPresent(record::getV16)
                .set(v17).equalToWhenPresent(record::getV17)
                .set(v18).equalToWhenPresent(record::getV18)
                .set(v19).equalToWhenPresent(record::getV19)
                .set(v20).equalToWhenPresent(record::getV20)
                .set(v21).equalToWhenPresent(record::getV21)
                .set(v22).equalToWhenPresent(record::getV22)
                .set(v23).equalToWhenPresent(record::getV23)
                .set(v24).equalToWhenPresent(record::getV24);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(EvaResTur20200001LeafOnline record) {
        return update(c ->
            c.set(v1).equalTo(record::getV1)
            .set(v2).equalTo(record::getV2)
            .set(v3).equalTo(record::getV3)
            .set(v4).equalTo(record::getV4)
            .set(v5).equalTo(record::getV5)
            .set(v6).equalTo(record::getV6)
            .set(v7).equalTo(record::getV7)
            .set(v8).equalTo(record::getV8)
            .set(v9).equalTo(record::getV9)
            .set(v10).equalTo(record::getV10)
            .set(v11).equalTo(record::getV11)
            .set(v12).equalTo(record::getV12)
            .set(v13).equalTo(record::getV13)
            .set(v14).equalTo(record::getV14)
            .set(v15).equalTo(record::getV15)
            .set(v16).equalTo(record::getV16)
            .set(v17).equalTo(record::getV17)
            .set(v18).equalTo(record::getV18)
            .set(v19).equalTo(record::getV19)
            .set(v20).equalTo(record::getV20)
            .set(v21).equalTo(record::getV21)
            .set(v22).equalTo(record::getV22)
            .set(v23).equalTo(record::getV23)
            .set(v24).equalTo(record::getV24)
            .where(time, isEqualTo(record::getTime))
            .and(unit, isEqualTo(record::getUnit))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(EvaResTur20200001LeafOnline record) {
        return update(c ->
            c.set(v1).equalToWhenPresent(record::getV1)
            .set(v2).equalToWhenPresent(record::getV2)
            .set(v3).equalToWhenPresent(record::getV3)
            .set(v4).equalToWhenPresent(record::getV4)
            .set(v5).equalToWhenPresent(record::getV5)
            .set(v6).equalToWhenPresent(record::getV6)
            .set(v7).equalToWhenPresent(record::getV7)
            .set(v8).equalToWhenPresent(record::getV8)
            .set(v9).equalToWhenPresent(record::getV9)
            .set(v10).equalToWhenPresent(record::getV10)
            .set(v11).equalToWhenPresent(record::getV11)
            .set(v12).equalToWhenPresent(record::getV12)
            .set(v13).equalToWhenPresent(record::getV13)
            .set(v14).equalToWhenPresent(record::getV14)
            .set(v15).equalToWhenPresent(record::getV15)
            .set(v16).equalToWhenPresent(record::getV16)
            .set(v17).equalToWhenPresent(record::getV17)
            .set(v18).equalToWhenPresent(record::getV18)
            .set(v19).equalToWhenPresent(record::getV19)
            .set(v20).equalToWhenPresent(record::getV20)
            .set(v21).equalToWhenPresent(record::getV21)
            .set(v22).equalToWhenPresent(record::getV22)
            .set(v23).equalToWhenPresent(record::getV23)
            .set(v24).equalToWhenPresent(record::getV24)
            .where(time, isEqualTo(record::getTime))
            .and(unit, isEqualTo(record::getUnit))
        );
    }
}