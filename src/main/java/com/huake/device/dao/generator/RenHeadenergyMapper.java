package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.RenHeadenergyDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.RenHeadenergy;
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
public interface RenHeadenergyMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(unit, a, b);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<RenHeadenergy> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RenHeadenergy> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RenHeadenergyResult")
    Optional<RenHeadenergy> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RenHeadenergyResult", value = {
        @Result(column="unit", property="unit", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="a", property="a", jdbcType=JdbcType.REAL),
        @Result(column="b", property="b", jdbcType=JdbcType.REAL)
    })
    List<RenHeadenergy> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, renHeadenergy, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, renHeadenergy, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer unit_) {
        return delete(c -> 
            c.where(unit, isEqualTo(unit_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RenHeadenergy record) {
        return MyBatis3Utils.insert(this::insert, record, renHeadenergy, c ->
            c.map(unit).toProperty("unit")
            .map(a).toProperty("a")
            .map(b).toProperty("b")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<RenHeadenergy> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, renHeadenergy, c ->
            c.map(unit).toProperty("unit")
            .map(a).toProperty("a")
            .map(b).toProperty("b")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RenHeadenergy record) {
        return MyBatis3Utils.insert(this::insert, record, renHeadenergy, c ->
            c.map(unit).toPropertyWhenPresent("unit", record::getUnit)
            .map(a).toPropertyWhenPresent("a", record::getA)
            .map(b).toPropertyWhenPresent("b", record::getB)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<RenHeadenergy> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, renHeadenergy, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<RenHeadenergy> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, renHeadenergy, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<RenHeadenergy> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, renHeadenergy, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<RenHeadenergy> selectByPrimaryKey(Integer unit_) {
        return selectOne(c ->
            c.where(unit, isEqualTo(unit_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, renHeadenergy, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(RenHeadenergy record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unit).equalTo(record::getUnit)
                .set(a).equalTo(record::getA)
                .set(b).equalTo(record::getB);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RenHeadenergy record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(unit).equalToWhenPresent(record::getUnit)
                .set(a).equalToWhenPresent(record::getA)
                .set(b).equalToWhenPresent(record::getB);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RenHeadenergy record) {
        return update(c ->
            c.set(a).equalTo(record::getA)
            .set(b).equalTo(record::getB)
            .where(unit, isEqualTo(record::getUnit))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RenHeadenergy record) {
        return update(c ->
            c.set(a).equalToWhenPresent(record::getA)
            .set(b).equalToWhenPresent(record::getB)
            .where(unit, isEqualTo(record::getUnit))
        );
    }
}