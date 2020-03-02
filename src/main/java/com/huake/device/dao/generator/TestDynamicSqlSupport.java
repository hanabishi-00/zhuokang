package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TestDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Test test = new Test();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = test.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> age = test.age;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> des = test.des;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Test extends SqlTable {
        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> age = column("age", JDBCType.VARCHAR);

        public final SqlColumn<String> des = column("des", JDBCType.VARCHAR);

        public Test() {
            super("test");
        }
    }
}