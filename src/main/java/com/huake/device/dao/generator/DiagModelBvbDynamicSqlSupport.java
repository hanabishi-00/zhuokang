package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DiagModelBvbDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DiagModelBvb diagModelBvb = new DiagModelBvb();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = diagModelBvb.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = diagModelBvb.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> pid = diagModelBvb.pid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> fatherName = diagModelBvb.fatherName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> gatetype = diagModelBvb.gatetype;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> points = diagModelBvb.points;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> method = diagModelBvb.method;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> threshold = diagModelBvb.threshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DiagModelBvb extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> pid = column("pid", JDBCType.INTEGER);

        public final SqlColumn<String> fatherName = column("father_name", JDBCType.VARCHAR);

        public final SqlColumn<String> gatetype = column("gatetype", JDBCType.VARCHAR);

        public final SqlColumn<String> points = column("points", JDBCType.VARCHAR);

        public final SqlColumn<String> method = column("method", JDBCType.VARCHAR);

        public final SqlColumn<String> threshold = column("threshold", JDBCType.VARCHAR);

        public DiagModelBvb() {
            super("diag_model_bvb");
        }
    }
}