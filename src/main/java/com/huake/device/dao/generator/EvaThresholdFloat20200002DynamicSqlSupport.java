package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaThresholdFloat20200002DynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaThresholdFloat20200002 evaThresholdFloat20200002 = new EvaThresholdFloat20200002();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = evaThresholdFloat20200002.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> threshold = evaThresholdFloat20200002.threshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaThresholdFloat20200002 extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> threshold = column("threshold", JDBCType.VARCHAR);

        public EvaThresholdFloat20200002() {
            super("eva_threshold_float_20200002");
        }
    }
}