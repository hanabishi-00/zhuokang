package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaModelTur20200001LeafDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final EvaModelTur20200001Leaf evaModelTur20200001Leaf = new EvaModelTur20200001Leaf();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = evaModelTur20200001Leaf.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = evaModelTur20200001Leaf.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> father = evaModelTur20200001Leaf.father;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Byte> weight = evaModelTur20200001Leaf.weight;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Boolean> state = evaModelTur20200001Leaf.state;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> stanDescription = evaModelTur20200001Leaf.stanDescription;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> points = evaModelTur20200001Leaf.points;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class EvaModelTur20200001Leaf extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> father = column("father", JDBCType.INTEGER);

        public final SqlColumn<Byte> weight = column("weight", JDBCType.TINYINT);

        public final SqlColumn<Boolean> state = column("state", JDBCType.BIT);

        public final SqlColumn<String> stanDescription = column("stan_description", JDBCType.VARCHAR);

        public final SqlColumn<String> points = column("points", JDBCType.VARCHAR);

        public EvaModelTur20200001Leaf() {
            super("eva_model_tur_20200001_leaf");
        }
    }
}