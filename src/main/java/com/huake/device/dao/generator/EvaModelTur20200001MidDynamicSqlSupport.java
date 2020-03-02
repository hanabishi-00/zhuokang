package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaModelTur20200001MidDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaModelTur20200001Mid evaModelTur20200001Mid = new EvaModelTur20200001Mid();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = evaModelTur20200001Mid.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = evaModelTur20200001Mid.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> father = evaModelTur20200001Mid.father;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaModelTur20200001Mid extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> father = column("father", JDBCType.INTEGER);

        public EvaModelTur20200001Mid() {
            super("eva_model_tur_20200001_mid");
        }
    }
}