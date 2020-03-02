package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DiagModelGovDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DiagModelGov diagModelGov = new DiagModelGov();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> nodeId = diagModelGov.nodeId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> fatherId = diagModelGov.fatherId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> gate = diagModelGov.gate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DiagModelGov extends SqlTable {
        public final SqlColumn<Integer> nodeId = column("node_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> fatherId = column("father_id", JDBCType.INTEGER);

        public final SqlColumn<String> gate = column("gate", JDBCType.VARCHAR);

        public DiagModelGov() {
            super("diag_model_gov");
        }
    }
}