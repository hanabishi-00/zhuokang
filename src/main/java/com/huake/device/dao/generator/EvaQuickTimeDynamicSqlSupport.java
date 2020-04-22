package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaQuickTimeDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaQuickTime evaQuickTime = new EvaQuickTime();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> timeType = evaQuickTime.timeType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> unitSet = evaQuickTime.unitSet;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> timeGap = evaQuickTime.timeGap;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> splitIndex = evaQuickTime.splitIndex;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> timePsg = evaQuickTime.timePsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaQuickTime extends SqlTable {
        public final SqlColumn<String> timeType = column("time_type", JDBCType.VARCHAR);

        public final SqlColumn<String> unitSet = column("unit_set", JDBCType.VARCHAR);

        public final SqlColumn<String> timeGap = column("time_gap", JDBCType.VARCHAR);

        public final SqlColumn<Integer> splitIndex = column("split_index", JDBCType.INTEGER);

        public final SqlColumn<String> timePsg = column("time_psg", JDBCType.LONGVARCHAR);

        public EvaQuickTime() {
            super("eva_quick_time");
        }
    }
}