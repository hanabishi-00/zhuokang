package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreeEvaCommonDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreeEvaCommon treeEvaCommon = new TreeEvaCommon();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> unitId = treeEvaCommon.unitId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> deviceId = treeEvaCommon.deviceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreeEvaCommon extends SqlTable {
        public final SqlColumn<Integer> unitId = column("unit_id", JDBCType.INTEGER);

        public final SqlColumn<String> deviceId = column("device_id", JDBCType.VARCHAR);

        public TreeEvaCommon() {
            super("tree_eva_common");
        }
    }
}