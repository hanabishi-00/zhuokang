package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreDeterdata101001DynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreDeterdata101001 treDeterdata101001 = new TreDeterdata101001();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> time = treDeterdata101001.time;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> deter = treDeterdata101001.deter;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreDeterdata101001 extends SqlTable {
        public final SqlColumn<Integer> time = column("time", JDBCType.INTEGER);

        public final SqlColumn<Float> deter = column("deter", JDBCType.REAL);

        public TreDeterdata101001() {
            super("tre_deterdata_101001");
        }
    }
}