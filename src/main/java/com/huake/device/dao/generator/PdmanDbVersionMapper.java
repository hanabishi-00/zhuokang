package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.PdmanDbVersionDynamicSqlSupport.*;

import com.huake.device.domain.generator.PdmanDbVersion;
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
public interface PdmanDbVersionMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(dbVersion, versionDesc, createdTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<PdmanDbVersion> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<PdmanDbVersion> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PdmanDbVersionResult")
    Optional<PdmanDbVersion> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PdmanDbVersionResult", value = {
        @Result(column="DB_VERSION", property="dbVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="VERSION_DESC", property="versionDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATED_TIME", property="createdTime", jdbcType=JdbcType.VARCHAR)
    })
    List<PdmanDbVersion> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, pdmanDbVersion, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, pdmanDbVersion, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(PdmanDbVersion record) {
        return MyBatis3Utils.insert(this::insert, record, pdmanDbVersion, c ->
            c.map(dbVersion).toProperty("dbVersion")
            .map(versionDesc).toProperty("versionDesc")
            .map(createdTime).toProperty("createdTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<PdmanDbVersion> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, pdmanDbVersion, c ->
            c.map(dbVersion).toProperty("dbVersion")
            .map(versionDesc).toProperty("versionDesc")
            .map(createdTime).toProperty("createdTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(PdmanDbVersion record) {
        return MyBatis3Utils.insert(this::insert, record, pdmanDbVersion, c ->
            c.map(dbVersion).toPropertyWhenPresent("dbVersion", record::getDbVersion)
            .map(versionDesc).toPropertyWhenPresent("versionDesc", record::getVersionDesc)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<PdmanDbVersion> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, pdmanDbVersion, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<PdmanDbVersion> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, pdmanDbVersion, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<PdmanDbVersion> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, pdmanDbVersion, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, pdmanDbVersion, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(PdmanDbVersion record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(dbVersion).equalTo(record::getDbVersion)
                .set(versionDesc).equalTo(record::getVersionDesc)
                .set(createdTime).equalTo(record::getCreatedTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(PdmanDbVersion record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(dbVersion).equalToWhenPresent(record::getDbVersion)
                .set(versionDesc).equalToWhenPresent(record::getVersionDesc)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime);
    }
}