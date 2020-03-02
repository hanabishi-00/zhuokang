package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.TreDeterpre101001DynamicSqlSupport.*;

import com.huake.device.domain.generator.TreDeterpre101001;
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
public interface TreDeterpre101001Mapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(pretime, predeter, warnsig);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<TreDeterpre101001> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TreDeterpre101001> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TreDeterpre101001Result")
    Optional<TreDeterpre101001> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TreDeterpre101001Result", value = {
        @Result(column="pretime", property="pretime", jdbcType=JdbcType.INTEGER),
        @Result(column="predeter", property="predeter", jdbcType=JdbcType.REAL),
        @Result(column="warnsig", property="warnsig", jdbcType=JdbcType.TINYINT)
    })
    List<TreDeterpre101001> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, treDeterpre101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, treDeterpre101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TreDeterpre101001 record) {
        return MyBatis3Utils.insert(this::insert, record, treDeterpre101001, c ->
            c.map(pretime).toProperty("pretime")
            .map(predeter).toProperty("predeter")
            .map(warnsig).toProperty("warnsig")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TreDeterpre101001> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, treDeterpre101001, c ->
            c.map(pretime).toProperty("pretime")
            .map(predeter).toProperty("predeter")
            .map(warnsig).toProperty("warnsig")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TreDeterpre101001 record) {
        return MyBatis3Utils.insert(this::insert, record, treDeterpre101001, c ->
            c.map(pretime).toPropertyWhenPresent("pretime", record::getPretime)
            .map(predeter).toPropertyWhenPresent("predeter", record::getPredeter)
            .map(warnsig).toPropertyWhenPresent("warnsig", record::getWarnsig)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TreDeterpre101001> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, treDeterpre101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreDeterpre101001> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, treDeterpre101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TreDeterpre101001> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, treDeterpre101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, treDeterpre101001, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TreDeterpre101001 record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(pretime).equalTo(record::getPretime)
                .set(predeter).equalTo(record::getPredeter)
                .set(warnsig).equalTo(record::getWarnsig);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TreDeterpre101001 record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(pretime).equalToWhenPresent(record::getPretime)
                .set(predeter).equalToWhenPresent(record::getPredeter)
                .set(warnsig).equalToWhenPresent(record::getWarnsig);
    }
}