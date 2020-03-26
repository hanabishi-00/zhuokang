package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreDeterThresholdDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreDeterThreshold treDeterThreshold = new TreDeterThreshold();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> unit = treDeterThreshold.unit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = treDeterThreshold.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> version = treDeterThreshold.version;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = treDeterThreshold.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> val = treDeterThreshold.val;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> relativeIds = treDeterThreshold.relativeIds;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreDeterThreshold extends SqlTable {
        public final SqlColumn<Integer> unit = column("unit", JDBCType.INTEGER);

        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> version = column("version", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.CHAR);

        public final SqlColumn<Float> val = column("val", JDBCType.REAL);

        public final SqlColumn<String> relativeIds = column("relative_ids", JDBCType.VARCHAR);

        public TreDeterThreshold() {
            super("tre_deter_threshold");
        }
    }
}