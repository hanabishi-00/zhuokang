package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RelevanceTestDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RelevanceTest relevanceTest = new RelevanceTest();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> seq = relevanceTest.seq;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = relevanceTest.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> relId = relevanceTest.relId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> relevance = relevanceTest.relevance;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RelevanceTest extends SqlTable {
        public final SqlColumn<String> seq = column("seq", JDBCType.VARCHAR);

        public final SqlColumn<Integer> id = column("ID", JDBCType.INTEGER);

        public final SqlColumn<Integer> relId = column("REL_ID", JDBCType.INTEGER);

        public final SqlColumn<Float> relevance = column("Relevance", JDBCType.REAL);

        public RelevanceTest() {
            super("relevance_test");
        }
    }
}