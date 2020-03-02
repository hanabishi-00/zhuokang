package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaThresholdGuideDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaThresholdGuide evaThresholdGuide = new EvaThresholdGuide();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> time = evaThresholdGuide.time;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> type = evaThresholdGuide.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> version = evaThresholdGuide.version;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaThresholdGuide extends SqlTable {
        public final SqlColumn<Integer> time = column("time", JDBCType.INTEGER);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<Integer> version = column("version", JDBCType.INTEGER);

        public EvaThresholdGuide() {
            super("eva_threshold_guide");
        }
    }
}