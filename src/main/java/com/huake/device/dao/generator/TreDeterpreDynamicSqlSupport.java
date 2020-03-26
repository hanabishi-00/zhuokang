package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TreDeterpreDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TreDeterpre treDeterpre = new TreDeterpre();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> pretime = treDeterpre.pretime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> predeter = treDeterpre.predeter;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> warnsig = treDeterpre.warnsig;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> idVersion = treDeterpre.idVersion;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TreDeterpre extends SqlTable {
        public final SqlColumn<Integer> pretime = column("pretime", JDBCType.INTEGER);

        public final SqlColumn<Float> predeter = column("predeter", JDBCType.REAL);

        public final SqlColumn<Byte> warnsig = column("warnsig", JDBCType.TINYINT);

        public final SqlColumn<String> idVersion = column("id_version", JDBCType.VARCHAR);

        public TreDeterpre() {
            super("tre_deterpre");
        }
    }
}