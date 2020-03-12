package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreeDiagUnitDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreeDiagUnit treeDiagUnit = new TreeDiagUnit();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> unitId = treeDiagUnit.unitId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> deviceId = treeDiagUnit.deviceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreeDiagUnit extends SqlTable {
        public final SqlColumn<Integer> unitId = column("unit_id", JDBCType.INTEGER);

        public final SqlColumn<String> deviceId = column("device_id", JDBCType.VARCHAR);

        public TreeDiagUnit() {
            super("tree_diag_unit");
        }
    }
}