package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaResOnlineDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaResOnline evaResOnline = new EvaResOnline();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> modelTime = evaResOnline.modelTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> type = evaResOnline.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> time = evaResOnline.time;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> unit = evaResOnline.unit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> offlineTime = evaResOnline.offlineTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> res = evaResOnline.res;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaResOnline extends SqlTable {
        public final SqlColumn<Integer> modelTime = column("model_time", JDBCType.INTEGER);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<Integer> time = column("time", JDBCType.INTEGER);

        public final SqlColumn<Byte> unit = column("unit", JDBCType.TINYINT);

        public final SqlColumn<Integer> offlineTime = column("offline_time", JDBCType.INTEGER);

        public final SqlColumn<String> res = column("res", JDBCType.LONGVARCHAR);

        public EvaResOnline() {
            super("eva_res_online");
        }
    }
}