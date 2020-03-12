package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreeEvaUnitDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreeEvaUnit treeEvaUnit = new TreeEvaUnit();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> unitId = treeEvaUnit.unitId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> typeId = treeEvaUnit.typeId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> deviceId = treeEvaUnit.deviceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreeEvaUnit extends SqlTable {
        public final SqlColumn<Integer> unitId = column("unit_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> typeId = column("type_id", JDBCType.INTEGER);

        public final SqlColumn<String> deviceId = column("device_id", JDBCType.VARCHAR);

        public TreeEvaUnit() {
            super("tree_eva_unit");
        }
    }
}