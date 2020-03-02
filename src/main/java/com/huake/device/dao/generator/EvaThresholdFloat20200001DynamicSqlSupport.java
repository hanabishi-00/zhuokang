package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaThresholdFloat20200001DynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaThresholdFloat20200001 evaThresholdFloat20200001 = new EvaThresholdFloat20200001();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = evaThresholdFloat20200001.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> threshold = evaThresholdFloat20200001.threshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaThresholdFloat20200001 extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> threshold = column("threshold", JDBCType.VARCHAR);

        public EvaThresholdFloat20200001() {
            super("eva_threshold_float_20200001");
        }
    }
}