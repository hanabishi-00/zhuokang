package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class MadiagConResDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final MadiagConRes madiagConRes = new MadiagConRes();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = madiagConRes.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> openCon = madiagConRes.openCon;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> operPro = madiagConRes.operPro;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> conproInt = madiagConRes.conproInt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class MadiagConRes extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> openCon = column("open_con", JDBCType.VARCHAR);

        public final SqlColumn<String> operPro = column("oper_pro", JDBCType.VARCHAR);

        public final SqlColumn<String> conproInt = column("conpro_int", JDBCType.VARCHAR);

        public MadiagConRes() {
            super("madiag_con_res");
        }
    }
}