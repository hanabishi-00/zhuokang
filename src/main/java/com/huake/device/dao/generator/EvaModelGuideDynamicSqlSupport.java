package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaModelGuideDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaModelGuide evaModelGuide = new EvaModelGuide();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> time = evaModelGuide.time;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> type = evaModelGuide.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> version = evaModelGuide.version;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaModelGuide extends SqlTable {
        public final SqlColumn<Integer> time = column("time", JDBCType.INTEGER);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<Integer> version = column("version", JDBCType.INTEGER);

        public EvaModelGuide() {
            super("eva_model_guide");
        }
    }
}