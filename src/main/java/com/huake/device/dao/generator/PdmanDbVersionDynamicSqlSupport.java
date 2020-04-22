package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PdmanDbVersionDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final PdmanDbVersion pdmanDbVersion = new PdmanDbVersion();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> dbVersion = pdmanDbVersion.dbVersion;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> versionDesc = pdmanDbVersion.versionDesc;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> createdTime = pdmanDbVersion.createdTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class PdmanDbVersion extends SqlTable {
        public final SqlColumn<String> dbVersion = column("DB_VERSION", JDBCType.VARCHAR);

        public final SqlColumn<String> versionDesc = column("VERSION_DESC", JDBCType.VARCHAR);

        public final SqlColumn<String> createdTime = column("CREATED_TIME", JDBCType.VARCHAR);

        public PdmanDbVersion() {
            super("pdman_db_version");
        }
    }
}