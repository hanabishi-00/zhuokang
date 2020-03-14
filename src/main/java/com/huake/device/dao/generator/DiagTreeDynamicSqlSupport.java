package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DiagTreeDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DiagTree diagTree = new DiagTree();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> id = diagTree.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = diagTree.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> des = diagTree.des;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> pid = diagTree.pid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> nodetype = diagTree.nodetype;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> gatetype = diagTree.gatetype;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> isleaf = diagTree.isleaf;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> precent = diagTree.precent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> method = diagTree.method;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> points = diagTree.points;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Float> threshold = diagTree.threshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DiagTree extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> des = column("des", JDBCType.VARCHAR);

        public final SqlColumn<String> pid = column("pid", JDBCType.VARCHAR);

        public final SqlColumn<String> nodetype = column("nodetype", JDBCType.VARCHAR);

        public final SqlColumn<String> gatetype = column("gatetype", JDBCType.VARCHAR);

        public final SqlColumn<String> isleaf = column("isleaf", JDBCType.VARCHAR);

        public final SqlColumn<Float> precent = column("precent", JDBCType.REAL);

        public final SqlColumn<String> method = column("method", JDBCType.VARCHAR);

        public final SqlColumn<String> points = column("points", JDBCType.VARCHAR);

        public final SqlColumn<Float> threshold = column("threshold", JDBCType.REAL);

        public DiagTree() {
            super("diag_tree");
        }
    }
}