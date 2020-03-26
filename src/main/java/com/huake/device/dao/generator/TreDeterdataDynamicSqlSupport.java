package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreDeterdataDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreDeterdata treDeterdata = new TreDeterdata();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> runtime = treDeterdata.runtime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> time = treDeterdata.time;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> deter = treDeterdata.deter;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> idVersion = treDeterdata.idVersion;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreDeterdata extends SqlTable {
        public final SqlColumn<Integer> runtime = column("runtime", JDBCType.INTEGER);

        public final SqlColumn<Integer> time = column("time", JDBCType.INTEGER);

        public final SqlColumn<Float> deter = column("deter", JDBCType.REAL);

        public final SqlColumn<String> idVersion = column("id_version", JDBCType.VARCHAR);

        public TreDeterdata() {
            super("tre_deterdata");
        }
    }
}