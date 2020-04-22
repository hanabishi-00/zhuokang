package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaResOfflineDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaResOffline evaResOffline = new EvaResOffline();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> modelTime = evaResOffline.modelTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> type = evaResOffline.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> time = evaResOffline.time;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> unit = evaResOffline.unit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> res = evaResOffline.res;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaResOffline extends SqlTable {
        public final SqlColumn<Integer> modelTime = column("model_time", JDBCType.INTEGER);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<Integer> time = column("time", JDBCType.INTEGER);

        public final SqlColumn<Byte> unit = column("unit", JDBCType.TINYINT);

        public final SqlColumn<String> res = column("res", JDBCType.LONGVARCHAR);

        public EvaResOffline() {
            super("eva_res_offline");
        }
    }
}