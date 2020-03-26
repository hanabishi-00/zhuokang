package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreAnaThresholdDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreAnaThreshold treAnaThreshold = new TreAnaThreshold();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> unit = treAnaThreshold.unit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = treAnaThreshold.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> boolIds = treAnaThreshold.boolIds;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> floatIds = treAnaThreshold.floatIds;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = treAnaThreshold.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> val = treAnaThreshold.val;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreAnaThreshold extends SqlTable {
        public final SqlColumn<Integer> unit = column("unit", JDBCType.INTEGER);

        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> boolIds = column("bool_ids", JDBCType.VARCHAR);

        public final SqlColumn<String> floatIds = column("float_ids", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Float> val = column("val", JDBCType.REAL);

        public TreAnaThreshold() {
            super("tre_ana_threshold");
        }
    }
}