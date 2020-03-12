package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreePreUnitDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreePreUnit treePreUnit = new TreePreUnit();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> unitId = treePreUnit.unitId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> deviceId = treePreUnit.deviceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> state = treePreUnit.state;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> measureId = treePreUnit.measureId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreePreUnit extends SqlTable {
        public final SqlColumn<Integer> unitId = column("unit_id", JDBCType.INTEGER);

        public final SqlColumn<String> deviceId = column("device_id", JDBCType.VARCHAR);

        public final SqlColumn<Integer> state = column("state", JDBCType.INTEGER);

        public final SqlColumn<String> measureId = column("measure_id", JDBCType.VARCHAR);

        public TreePreUnit() {
            super("tree_pre_unit");
        }
    }
}