package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaThresholdBool20200002DynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaThresholdBool20200002 evaThresholdBool20200002 = new EvaThresholdBool20200002();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = evaThresholdBool20200002.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> threshold = evaThresholdBool20200002.threshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaThresholdBool20200002 extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> threshold = column("threshold", JDBCType.VARCHAR);

        public EvaThresholdBool20200002() {
            super("eva_threshold_bool_20200002");
        }
    }
}