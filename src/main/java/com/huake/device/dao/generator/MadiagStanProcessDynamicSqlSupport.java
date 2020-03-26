package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class MadiagStanProcessDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final MadiagStanProcess madiagStanProcess = new MadiagStanProcess();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> measId = madiagStanProcess.measId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> unitId = madiagStanProcess.unitId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> equipPro = madiagStanProcess.equipPro;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> stepId = madiagStanProcess.stepId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> measName = madiagStanProcess.measName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> proInt = madiagStanProcess.proInt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class MadiagStanProcess extends SqlTable {
        public final SqlColumn<Integer> measId = column("meas_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> unitId = column("unit_id", JDBCType.INTEGER);

        public final SqlColumn<String> equipPro = column("equip_pro", JDBCType.VARCHAR);

        public final SqlColumn<Integer> stepId = column("step_id", JDBCType.INTEGER);

        public final SqlColumn<String> measName = column("meas_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> proInt = column("pro_int", JDBCType.INTEGER);

        public MadiagStanProcess() {
            super("madiag_stan_process");
        }
    }
}