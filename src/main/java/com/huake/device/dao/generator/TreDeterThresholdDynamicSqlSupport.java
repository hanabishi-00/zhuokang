package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreDeterThresholdDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreDeterThreshold treDeterThreshold = new TreDeterThreshold();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> modelNumber = treDeterThreshold.modelNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> modelName = treDeterThreshold.modelName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> deterdataTable = treDeterThreshold.deterdataTable;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> preResultTable = treDeterThreshold.preResultTable;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> warnThreshold = treDeterThreshold.warnThreshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreDeterThreshold extends SqlTable {
        public final SqlColumn<Integer> modelNumber = column("model_number", JDBCType.INTEGER);

        public final SqlColumn<String> modelName = column("model_name", JDBCType.CHAR);

        public final SqlColumn<String> deterdataTable = column("deterdata_table", JDBCType.CHAR);

        public final SqlColumn<String> preResultTable = column("pre_result_table", JDBCType.CHAR);

        public final SqlColumn<Float> warnThreshold = column("warn_threshold", JDBCType.REAL);

        public TreDeterThreshold() {
            super("tre_deter_threshold");
        }
    }
}