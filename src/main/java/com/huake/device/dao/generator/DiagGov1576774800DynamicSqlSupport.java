package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DiagGov1576774800DynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DiagGov1576774800 diagGov1576774800 = new DiagGov1576774800();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> nodeId = diagGov1576774800.nodeId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> freq = diagGov1576774800.freq;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> suggId = diagGov1576774800.suggId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DiagGov1576774800 extends SqlTable {
        public final SqlColumn<Integer> nodeId = column("node_id", JDBCType.INTEGER);

        public final SqlColumn<Float> freq = column("freq", JDBCType.REAL);

        public final SqlColumn<Integer> suggId = column("sugg_id", JDBCType.INTEGER);

        public DiagGov1576774800() {
            super("diag_gov_1576774800");
        }
    }
}