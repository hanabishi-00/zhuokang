package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ClassifiedqueryprefixDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Classifiedqueryprefix classifiedqueryprefix = new Classifiedqueryprefix();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = classifiedqueryprefix.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> classifiedid = classifiedqueryprefix.classifiedid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> prefix = classifiedqueryprefix.prefix;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Classifiedqueryprefix extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> classifiedid = column("classifiedid", JDBCType.INTEGER);

        public final SqlColumn<String> prefix = column("prefix", JDBCType.VARCHAR);

        public Classifiedqueryprefix() {
            super("classifiedqueryprefix");
        }
    }
}