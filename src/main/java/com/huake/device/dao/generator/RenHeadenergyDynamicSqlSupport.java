package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RenHeadenergyDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RenHeadenergy renHeadenergy = new RenHeadenergy();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> unit = renHeadenergy.unit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> a = renHeadenergy.a;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> b = renHeadenergy.b;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RenHeadenergy extends SqlTable {
        public final SqlColumn<Integer> unit = column("unit", JDBCType.INTEGER);

        public final SqlColumn<Float> a = column("a", JDBCType.REAL);

        public final SqlColumn<Float> b = column("b", JDBCType.REAL);

        public RenHeadenergy() {
            super("ren_headenergy");
        }
    }
}