package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DiagResGuideDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DiagResGuide diagResGuide = new DiagResGuide();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> recordId = diagResGuide.recordId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> time = diagResGuide.time;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DiagResGuide extends SqlTable {
        public final SqlColumn<Integer> recordId = column("record_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> time = column("time", JDBCType.INTEGER);

        public DiagResGuide() {
            super("diag_res_guide");
        }
    }
}