package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.DiagTreeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.DiagTree;
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
public interface DiagTreeMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, des, pid, nodetype, gatetype, isleaf, precent, method, points, threshold);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<DiagTree> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<DiagTree> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DiagTreeResult")
    Optional<DiagTree> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DiagTreeResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="des", property="des", jdbcType=JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.VARCHAR),
        @Result(column="nodetype", property="nodetype", jdbcType=JdbcType.VARCHAR),
        @Result(column="gatetype", property="gatetype", jdbcType=JdbcType.VARCHAR),
        @Result(column="isleaf", property="isleaf", jdbcType=JdbcType.VARCHAR),
        @Result(column="precent", property="precent", jdbcType=JdbcType.REAL),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="points", property="points", jdbcType=JdbcType.VARCHAR),
        @Result(column="threshold", property="threshold", jdbcType=JdbcType.REAL)
    })
    List<DiagTree> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, diagTree, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, diagTree, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(DiagTree record) {
        return MyBatis3Utils.insert(this::insert, record, diagTree, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(des).toProperty("des")
            .map(pid).toProperty("pid")
            .map(nodetype).toProperty("nodetype")
            .map(gatetype).toProperty("gatetype")
            .map(isleaf).toProperty("isleaf")
            .map(precent).toProperty("precent")
            .map(method).toProperty("method")
            .map(points).toProperty("points")
            .map(threshold).toProperty("threshold")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<DiagTree> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, diagTree, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(des).toProperty("des")
            .map(pid).toProperty("pid")
            .map(nodetype).toProperty("nodetype")
            .map(gatetype).toProperty("gatetype")
            .map(isleaf).toProperty("isleaf")
            .map(precent).toProperty("precent")
            .map(method).toProperty("method")
            .map(points).toProperty("points")
            .map(threshold).toProperty("threshold")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(DiagTree record) {
        return MyBatis3Utils.insert(this::insert, record, diagTree, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(des).toPropertyWhenPresent("des", record::getDes)
            .map(pid).toPropertyWhenPresent("pid", record::getPid)
            .map(nodetype).toPropertyWhenPresent("nodetype", record::getNodetype)
            .map(gatetype).toPropertyWhenPresent("gatetype", record::getGatetype)
            .map(isleaf).toPropertyWhenPresent("isleaf", record::getIsleaf)
            .map(precent).toPropertyWhenPresent("precent", record::getPrecent)
            .map(method).toPropertyWhenPresent("method", record::getMethod)
            .map(points).toPropertyWhenPresent("points", record::getPoints)
            .map(threshold).toPropertyWhenPresent("threshold", record::getThreshold)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagTree> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, diagTree, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagTree> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, diagTree, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<DiagTree> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, diagTree, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<DiagTree> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, diagTree, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(DiagTree record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(des).equalTo(record::getDes)
                .set(pid).equalTo(record::getPid)
                .set(nodetype).equalTo(record::getNodetype)
                .set(gatetype).equalTo(record::getGatetype)
                .set(isleaf).equalTo(record::getIsleaf)
                .set(precent).equalTo(record::getPrecent)
                .set(method).equalTo(record::getMethod)
                .set(points).equalTo(record::getPoints)
                .set(threshold).equalTo(record::getThreshold);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(DiagTree record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(des).equalToWhenPresent(record::getDes)
                .set(pid).equalToWhenPresent(record::getPid)
                .set(nodetype).equalToWhenPresent(record::getNodetype)
                .set(gatetype).equalToWhenPresent(record::getGatetype)
                .set(isleaf).equalToWhenPresent(record::getIsleaf)
                .set(precent).equalToWhenPresent(record::getPrecent)
                .set(method).equalToWhenPresent(record::getMethod)
                .set(points).equalToWhenPresent(record::getPoints)
                .set(threshold).equalToWhenPresent(record::getThreshold);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(DiagTree record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(des).equalTo(record::getDes)
            .set(pid).equalTo(record::getPid)
            .set(nodetype).equalTo(record::getNodetype)
            .set(gatetype).equalTo(record::getGatetype)
            .set(isleaf).equalTo(record::getIsleaf)
            .set(precent).equalTo(record::getPrecent)
            .set(method).equalTo(record::getMethod)
            .set(points).equalTo(record::getPoints)
            .set(threshold).equalTo(record::getThreshold)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(DiagTree record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(des).equalToWhenPresent(record::getDes)
            .set(pid).equalToWhenPresent(record::getPid)
            .set(nodetype).equalToWhenPresent(record::getNodetype)
            .set(gatetype).equalToWhenPresent(record::getGatetype)
            .set(isleaf).equalToWhenPresent(record::getIsleaf)
            .set(precent).equalToWhenPresent(record::getPrecent)
            .set(method).equalToWhenPresent(record::getMethod)
            .set(points).equalToWhenPresent(record::getPoints)
            .set(threshold).equalToWhenPresent(record::getThreshold)
            .where(id, isEqualTo(record::getId))
        );
    }
}