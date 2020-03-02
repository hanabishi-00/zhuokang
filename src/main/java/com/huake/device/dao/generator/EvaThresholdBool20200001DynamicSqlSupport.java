package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaThresholdBool20200001DynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaThresholdBool20200001 evaThresholdBool20200001 = new EvaThresholdBool20200001();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = evaThresholdBool20200001.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> threshold = evaThresholdBool20200001.threshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaThresholdBool20200001 extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> threshold = column("threshold", JDBCType.VARCHAR);

        public EvaThresholdBool20200001() {
            super("eva_threshold_bool_20200001");
        }
    }
}