package org.linlinjava.device.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class Bool826201802DynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Bool826201802 bool826201802 = new Bool826201802();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> t = bool826201802.t;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> v = bool826201802.v;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> f = bool826201802.f;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Bool826201802 extends SqlTable {
        public final SqlColumn<Integer> t = column("t", JDBCType.INTEGER);

        public final SqlColumn<Byte> v = column("v", JDBCType.TINYINT);

        public final SqlColumn<Byte> f = column("f", JDBCType.TINYINT);

        public Bool826201802() {
            super("bool_826_2018_02");
        }
    }
}