package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RelationPointsDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final RelationPoints relationPoints = new RelationPoints();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = relationPoints.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> type = relationPoints.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> relationId = relationPoints.relationId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> relationType = relationPoints.relationType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> percent = relationPoints.percent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class RelationPoints extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<Long> relationId = column("relation_id", JDBCType.BIGINT);

        public final SqlColumn<String> relationType = column("relation_type", JDBCType.VARCHAR);

        public final SqlColumn<Float> percent = column("percent", JDBCType.REAL);

        public RelationPoints() {
            super("relation_points");
        }
    }
}