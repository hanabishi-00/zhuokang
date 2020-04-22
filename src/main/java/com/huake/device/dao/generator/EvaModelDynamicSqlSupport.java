package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaModelDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaModel evaModel = new EvaModel();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> time = evaModel.time;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> type = evaModel.type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = evaModel.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = evaModel.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> father = evaModel.father;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> weight = evaModel.weight;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> state = evaModel.state;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> stanDescription = evaModel.stanDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> points = evaModel.points;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaModel extends SqlTable {
        public final SqlColumn<Integer> time = column("time", JDBCType.INTEGER);

        public final SqlColumn<String> type = column("type", JDBCType.VARCHAR);

        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> father = column("father", JDBCType.INTEGER);

        public final SqlColumn<Byte> weight = column("weight", JDBCType.TINYINT);

        public final SqlColumn<Boolean> state = column("state", JDBCType.BIT);

        public final SqlColumn<String> stanDescription = column("stan_description", JDBCType.VARCHAR);

        public final SqlColumn<String> points = column("points", JDBCType.LONGVARCHAR);

        public EvaModel() {
            super("eva_model");
        }
    }
}