package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaTagDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaTag evaTag = new EvaTag();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> key = evaTag.key;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> type = evaTag.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = evaTag.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaTag extends SqlTable {
        public final SqlColumn<String> key = column("key", JDBCType.VARCHAR);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public EvaTag() {
            super("eva_tag");
        }
    }
}