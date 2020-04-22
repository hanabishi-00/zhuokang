package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DiagResultDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DiagResult diagResult = new DiagResult();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = diagResult.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> recordId = diagResult.recordId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> nodeId = diagResult.nodeId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> freq = diagResult.freq;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> suggId = diagResult.suggId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DiagResult extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> recordId = column("record_id", JDBCType.CHAR);

        public final SqlColumn<Integer> nodeId = column("node_id", JDBCType.INTEGER);

        public final SqlColumn<Float> freq = column("freq", JDBCType.REAL);

        public final SqlColumn<Integer> suggId = column("sugg_id", JDBCType.INTEGER);

        public DiagResult() {
            super("diag_result");
        }
    }
}