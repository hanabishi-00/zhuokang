package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreDeterpre101001DynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreDeterpre101001 treDeterpre101001 = new TreDeterpre101001();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> pretime = treDeterpre101001.pretime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> predeter = treDeterpre101001.predeter;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> warnsig = treDeterpre101001.warnsig;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreDeterpre101001 extends SqlTable {
        public final SqlColumn<Integer> pretime = column("pretime", JDBCType.INTEGER);

        public final SqlColumn<Float> predeter = column("predeter", JDBCType.REAL);

        public final SqlColumn<Byte> warnsig = column("warnsig", JDBCType.TINYINT);

        public TreDeterpre101001() {
            super("tre_deterpre_101001");
        }
    }
}