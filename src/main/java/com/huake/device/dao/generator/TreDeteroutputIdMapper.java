package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.TreDeteroutputIdDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.TreDeteroutputId;
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
public interface TreDeteroutputIdMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(modelNum, output1, output2, output3, output4, output5, output6, output7, output8, output9, output10, output11, output12, output13, output14, output15, output16, output17, output18, output19, output20);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TreDeteroutputId> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TreDeteroutputId> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TreDeteroutputIdResult")
    Optional<TreDeteroutputId> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TreDeteroutputIdResult", value = {
        @Result(column="model_num", property="modelNum", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="output1", property="output1", jdbcType=JdbcType.INTEGER),
        @Result(column="output2", property="output2", jdbcType=JdbcType.INTEGER),
        @Result(column="output3", property="output3", jdbcType=JdbcType.INTEGER),
        @Result(column="output4", property="output4", jdbcType=JdbcType.INTEGER),
        @Result(column="output5", property="output5", jdbcType=JdbcType.INTEGER),
        @Result(column="output6", property="output6", jdbcType=JdbcType.INTEGER),
        @Result(column="output7", property="output7", jdbcType=JdbcType.INTEGER),
        @Result(column="output8", property="output8", jdbcType=JdbcType.INTEGER),
        @Result(column="output9", property="output9", jdbcType=JdbcType.INTEGER),
        @Result(column="output10", property="output10", jdbcType=JdbcType.INTEGER),
        @Result(column="output11", property="output11", jdbcType=JdbcType.INTEGER),
        @Result(column="output12", property="output12", jdbcType=JdbcType.INTEGER),
        @Result(column="output13", property="output13", jdbcType=JdbcType.INTEGER),
        @Result(column="output14", property="output14", jdbcType=JdbcType.INTEGER),
        @Result(column="output15", property="output15", jdbcType=JdbcType.INTEGER),
        @Result(column="output16", property="output16", jdbcType=JdbcType.INTEGER),
        @Result(column="output17", property="output17", jdbcType=JdbcType.INTEGER),
        @Result(column="output18", property="output18", jdbcType=JdbcType.INTEGER),
        @Result(column="output19", property="output19", jdbcType=JdbcType.INTEGER),
        @Result(column="output20", property="output20", jdbcType=JdbcType.INTEGER)
    })
    List<TreDeteroutputId> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, treDeteroutputId, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, treDeteroutputId, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer modelNum_) {
        return delete(c -> 
            c.where(modelNum, isEqualTo(modelNum_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TreDeteroutputId record) {
        return MyBatis3Utils.insert(this::insert, record, treDeteroutputId, c ->
            c.map(modelNum).toProperty("modelNum")
            .map(output1).toProperty("output1")
            .map(output2).toProperty("output2")
            .map(output3).toProperty("output3")
            .map(output4).toProperty("output4")
            .map(output5).toProperty("output5")
            .map(output6).toProperty("output6")
            .map(output7).toProperty("output7")
            .map(output8).toProperty("output8")
            .map(output9).toProperty("output9")
            .map(output10).toProperty("output10")
            .map(output11).toProperty("output11")
            .map(output12).toProperty("output12")
            .map(output13).toProperty("output13")
            .map(output14).toProperty("output14")
            .map(output15).toProperty("output15")
            .map(output16).toProperty("output16")
            .map(output17).toProperty("output17")
            .map(output18).toProperty("output18")
            .map(output19).toProperty("output19")
            .map(output20).toProperty("output20")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TreDeteroutputId> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, treDeteroutputId, c ->
            c.map(modelNum).toProperty("modelNum")
            .map(output1).toProperty("output1")
            .map(output2).toProperty("output2")
            .map(output3).toProperty("output3")
            .map(output4).toProperty("output4")
            .map(output5).toProperty("output5")
            .map(output6).toProperty("output6")
            .map(output7).toProperty("output7")
            .map(output8).toProperty("output8")
            .map(output9).toProperty("output9")
            .map(output10).toProperty("output10")
            .map(output11).toProperty("output11")
            .map(output12).toProperty("output12")
            .map(output13).toProperty("output13")
            .map(output14).toProperty("output14")
            .map(output15).toProperty("output15")
            .map(output16).toProperty("output16")
            .map(output17).toProperty("output17")
            .map(output18).toProperty("output18")
            .map(output19).toProperty("output19")
            .map(output20).toProperty("output20")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TreDeteroutputId record) {
        return MyBatis3Utils.insert(this::insert, record, treDeteroutputId, c ->
            c.map(modelNum).toPropertyWhenPresent("modelNum", record::getModelNum)
            .map(output1).toPropertyWhenPresent("output1", record::getOutput1)
            .map(output2).toPropertyWhenPresent("output2", record::getOutput2)
            .map(output3).toPropertyWhenPresent("output3", record::getOutput3)
            .map(output4).toPropertyWhenPresent("output4", record::getOutput4)
            .map(output5).toPropertyWhenPresent("output5", record::getOutput5)
            .map(output6).toPropertyWhenPresent("output6", record::getOutput6)
            .map(output7).toPropertyWhenPresent("output7", record::getOutput7)
            .map(output8).toPropertyWhenPresent("output8", record::getOutput8)
            .map(output9).toPropertyWhenPresent("output9", record::getOutput9)
            .map(output10).toPropertyWhenPresent("output10", record::getOutput10)
            .map(output11).toPropertyWhenPresent("output11", record::getOutput11)
            .map(output12).toPropertyWhenPresent("output12", record::getOutput12)
            .map(output13).toPropertyWhenPresent("output13", record::getOutput13)
            .map(output14).toPropertyWhenPresent("output14", record::getOutput14)
            .map(output15).toPropertyWhenPresent("output15", record::getOutput15)
            .map(output16).toPropertyWhenPresent("output16", record::getOutput16)
            .map(output17).toPropertyWhenPresent("output17", record::getOutput17)
            .map(output18).toPropertyWhenPresent("output18", record::getOutput18)
            .map(output19).toPropertyWhenPresent("output19", record::getOutput19)
            .map(output20).toPropertyWhenPresent("output20", record::getOutput20)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreDeteroutputId> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, treDeteroutputId, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreDeteroutputId> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, treDeteroutputId, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreDeteroutputId> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, treDeteroutputId, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreDeteroutputId> selectByPrimaryKey(Integer modelNum_) {
        return selectOne(c ->
            c.where(modelNum, isEqualTo(modelNum_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, treDeteroutputId, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TreDeteroutputId record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(modelNum).equalTo(record::getModelNum)
                .set(output1).equalTo(record::getOutput1)
                .set(output2).equalTo(record::getOutput2)
                .set(output3).equalTo(record::getOutput3)
                .set(output4).equalTo(record::getOutput4)
                .set(output5).equalTo(record::getOutput5)
                .set(output6).equalTo(record::getOutput6)
                .set(output7).equalTo(record::getOutput7)
                .set(output8).equalTo(record::getOutput8)
                .set(output9).equalTo(record::getOutput9)
                .set(output10).equalTo(record::getOutput10)
                .set(output11).equalTo(record::getOutput11)
                .set(output12).equalTo(record::getOutput12)
                .set(output13).equalTo(record::getOutput13)
                .set(output14).equalTo(record::getOutput14)
                .set(output15).equalTo(record::getOutput15)
                .set(output16).equalTo(record::getOutput16)
                .set(output17).equalTo(record::getOutput17)
                .set(output18).equalTo(record::getOutput18)
                .set(output19).equalTo(record::getOutput19)
                .set(output20).equalTo(record::getOutput20);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TreDeteroutputId record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(modelNum).equalToWhenPresent(record::getModelNum)
                .set(output1).equalToWhenPresent(record::getOutput1)
                .set(output2).equalToWhenPresent(record::getOutput2)
                .set(output3).equalToWhenPresent(record::getOutput3)
                .set(output4).equalToWhenPresent(record::getOutput4)
                .set(output5).equalToWhenPresent(record::getOutput5)
                .set(output6).equalToWhenPresent(record::getOutput6)
                .set(output7).equalToWhenPresent(record::getOutput7)
                .set(output8).equalToWhenPresent(record::getOutput8)
                .set(output9).equalToWhenPresent(record::getOutput9)
                .set(output10).equalToWhenPresent(record::getOutput10)
                .set(output11).equalToWhenPresent(record::getOutput11)
                .set(output12).equalToWhenPresent(record::getOutput12)
                .set(output13).equalToWhenPresent(record::getOutput13)
                .set(output14).equalToWhenPresent(record::getOutput14)
                .set(output15).equalToWhenPresent(record::getOutput15)
                .set(output16).equalToWhenPresent(record::getOutput16)
                .set(output17).equalToWhenPresent(record::getOutput17)
                .set(output18).equalToWhenPresent(record::getOutput18)
                .set(output19).equalToWhenPresent(record::getOutput19)
                .set(output20).equalToWhenPresent(record::getOutput20);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TreDeteroutputId record) {
        return update(c ->
            c.set(output1).equalTo(record::getOutput1)
            .set(output2).equalTo(record::getOutput2)
            .set(output3).equalTo(record::getOutput3)
            .set(output4).equalTo(record::getOutput4)
            .set(output5).equalTo(record::getOutput5)
            .set(output6).equalTo(record::getOutput6)
            .set(output7).equalTo(record::getOutput7)
            .set(output8).equalTo(record::getOutput8)
            .set(output9).equalTo(record::getOutput9)
            .set(output10).equalTo(record::getOutput10)
            .set(output11).equalTo(record::getOutput11)
            .set(output12).equalTo(record::getOutput12)
            .set(output13).equalTo(record::getOutput13)
            .set(output14).equalTo(record::getOutput14)
            .set(output15).equalTo(record::getOutput15)
            .set(output16).equalTo(record::getOutput16)
            .set(output17).equalTo(record::getOutput17)
            .set(output18).equalTo(record::getOutput18)
            .set(output19).equalTo(record::getOutput19)
            .set(output20).equalTo(record::getOutput20)
            .where(modelNum, isEqualTo(record::getModelNum))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TreDeteroutputId record) {
        return update(c ->
            c.set(output1).equalToWhenPresent(record::getOutput1)
            .set(output2).equalToWhenPresent(record::getOutput2)
            .set(output3).equalToWhenPresent(record::getOutput3)
            .set(output4).equalToWhenPresent(record::getOutput4)
            .set(output5).equalToWhenPresent(record::getOutput5)
            .set(output6).equalToWhenPresent(record::getOutput6)
            .set(output7).equalToWhenPresent(record::getOutput7)
            .set(output8).equalToWhenPresent(record::getOutput8)
            .set(output9).equalToWhenPresent(record::getOutput9)
            .set(output10).equalToWhenPresent(record::getOutput10)
            .set(output11).equalToWhenPresent(record::getOutput11)
            .set(output12).equalToWhenPresent(record::getOutput12)
            .set(output13).equalToWhenPresent(record::getOutput13)
            .set(output14).equalToWhenPresent(record::getOutput14)
            .set(output15).equalToWhenPresent(record::getOutput15)
            .set(output16).equalToWhenPresent(record::getOutput16)
            .set(output17).equalToWhenPresent(record::getOutput17)
            .set(output18).equalToWhenPresent(record::getOutput18)
            .set(output19).equalToWhenPresent(record::getOutput19)
            .set(output20).equalToWhenPresent(record::getOutput20)
            .where(modelNum, isEqualTo(record::getModelNum))
        );
    }
}