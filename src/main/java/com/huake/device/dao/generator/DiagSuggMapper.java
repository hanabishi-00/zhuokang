package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.DiagSuggDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.DiagSugg;
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
public interface DiagSuggMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(suggId, repairCom, toolCom, runCom);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<DiagSugg> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<DiagSugg> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DiagSuggResult")
    Optional<DiagSugg> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DiagSuggResult", value = {
        @Result(column="sugg_id", property="suggId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="repair_com", property="repairCom", jdbcType=JdbcType.VARCHAR),
        @Result(column="tool_com", property="toolCom", jdbcType=JdbcType.VARCHAR),
        @Result(column="run_com", property="runCom", jdbcType=JdbcType.VARCHAR)
    })
    List<DiagSugg> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, diagSugg, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, diagSugg, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer suggId_) {
        return delete(c -> 
            c.where(suggId, isEqualTo(suggId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(DiagSugg record) {
        return MyBatis3Utils.insert(this::insert, record, diagSugg, c ->
            c.map(suggId).toProperty("suggId")
            .map(repairCom).toProperty("repairCom")
            .map(toolCom).toProperty("toolCom")
            .map(runCom).toProperty("runCom")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<DiagSugg> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, diagSugg, c ->
            c.map(suggId).toProperty("suggId")
            .map(repairCom).toProperty("repairCom")
            .map(toolCom).toProperty("toolCom")
            .map(runCom).toProperty("runCom")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(DiagSugg record) {
        return MyBatis3Utils.insert(this::insert, record, diagSugg, c ->
            c.map(suggId).toPropertyWhenPresent("suggId", record::getSuggId)
            .map(repairCom).toPropertyWhenPresent("repairCom", record::getRepairCom)
            .map(toolCom).toPropertyWhenPresent("toolCom", record::getToolCom)
            .map(runCom).toPropertyWhenPresent("runCom", record::getRunCom)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagSugg> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, diagSugg, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagSugg> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, diagSugg, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagSugg> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, diagSugg, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagSugg> selectByPrimaryKey(Integer suggId_) {
        return selectOne(c ->
            c.where(suggId, isEqualTo(suggId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, diagSugg, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(DiagSugg record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(suggId).equalTo(record::getSuggId)
                .set(repairCom).equalTo(record::getRepairCom)
                .set(toolCom).equalTo(record::getToolCom)
                .set(runCom).equalTo(record::getRunCom);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(DiagSugg record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(suggId).equalToWhenPresent(record::getSuggId)
                .set(repairCom).equalToWhenPresent(record::getRepairCom)
                .set(toolCom).equalToWhenPresent(record::getToolCom)
                .set(runCom).equalToWhenPresent(record::getRunCom);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(DiagSugg record) {
        return update(c ->
            c.set(repairCom).equalTo(record::getRepairCom)
            .set(toolCom).equalTo(record::getToolCom)
            .set(runCom).equalTo(record::getRunCom)
            .where(suggId, isEqualTo(record::getSuggId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(DiagSugg record) {
        return update(c ->
            c.set(repairCom).equalToWhenPresent(record::getRepairCom)
            .set(toolCom).equalToWhenPresent(record::getToolCom)
            .set(runCom).equalToWhenPresent(record::getRunCom)
            .where(suggId, isEqualTo(record::getSuggId))
        );
    }
}