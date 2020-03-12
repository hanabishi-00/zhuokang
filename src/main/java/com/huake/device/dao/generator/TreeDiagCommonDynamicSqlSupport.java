package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreeDiagCommonDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreeDiagCommon treeDiagCommon = new TreeDiagCommon();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = treeDiagCommon.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> deviceId = treeDiagCommon.deviceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreeDiagCommon extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> deviceId = column("device_id", JDBCType.VARCHAR);

        public TreeDiagCommon() {
            super("tree_diag_common");
        }
    }
}