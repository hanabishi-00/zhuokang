package com.huake.device.dao.generator;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class MadiagHisProcessDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final MadiagHisProcess madiagHisProcess = new MadiagHisProcess();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = madiagHisProcess.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> unitId = madiagHisProcess.unitId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> equipPro = madiagHisProcess.equipPro;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> time = madiagHisProcess.time;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class MadiagHisProcess extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> unitId = column("unit_id", JDBCType.INTEGER);

        public final SqlColumn<String> equipPro = column("equip_pro", JDBCType.VARCHAR);

        public final SqlColumn<String> time = column("time", JDBCType.VARCHAR);

        public MadiagHisProcess() {
            super("madiag_his_process");
        }
    }
}