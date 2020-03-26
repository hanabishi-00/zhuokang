package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class MadiagSingHisDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final MadiagSingHis madiagSingHis = new MadiagSingHis();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = madiagSingHis.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> hisId = madiagSingHis.hisId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> histtepId = madiagSingHis.histtepId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> hismeasName = madiagSingHis.hismeasName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> hisproInt = madiagSingHis.hisproInt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class MadiagSingHis extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> hisId = column("his_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> histtepId = column("histtep_id", JDBCType.INTEGER);

        public final SqlColumn<String> hismeasName = column("hismeas_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> hisproInt = column("hispro_int", JDBCType.INTEGER);

        public MadiagSingHis() {
            super("madiag_sing_his");
        }
    }
}