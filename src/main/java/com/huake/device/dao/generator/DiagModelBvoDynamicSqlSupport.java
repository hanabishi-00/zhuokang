package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DiagModelBvoDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DiagModelBvo diagModelBvo = new DiagModelBvo();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = diagModelBvo.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = diagModelBvo.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> pid = diagModelBvo.pid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> fatherName = diagModelBvo.fatherName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> gatetype = diagModelBvo.gatetype;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> points = diagModelBvo.points;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> method = diagModelBvo.method;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> threshold = diagModelBvo.threshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DiagModelBvo extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> pid = column("pid", JDBCType.INTEGER);

        public final SqlColumn<String> fatherName = column("father_name", JDBCType.VARCHAR);

        public final SqlColumn<String> gatetype = column("gatetype", JDBCType.VARCHAR);

        public final SqlColumn<String> points = column("points", JDBCType.VARCHAR);

        public final SqlColumn<String> method = column("method", JDBCType.VARCHAR);

        public final SqlColumn<String> threshold = column("threshold", JDBCType.VARCHAR);

        public DiagModelBvo() {
            super("diag_model_bvo");
        }
    }
}