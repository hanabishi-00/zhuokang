package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DiagSuggDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DiagSugg diagSugg = new DiagSugg();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> suggId = diagSugg.suggId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> repairCom = diagSugg.repairCom;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> toolCom = diagSugg.toolCom;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> runCom = diagSugg.runCom;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DiagSugg extends SqlTable {
        public final SqlColumn<Integer> suggId = column("sugg_id", JDBCType.INTEGER);

        public final SqlColumn<String> repairCom = column("repair_com", JDBCType.VARCHAR);

        public final SqlColumn<String> toolCom = column("tool_com", JDBCType.VARCHAR);

        public final SqlColumn<String> runCom = column("run_com", JDBCType.VARCHAR);

        public DiagSugg() {
            super("diag_sugg");
        }
    }
}