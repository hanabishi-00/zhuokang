package com.huake.device.dao.generator;

import static com.huake.device.dao.generator.EvaResTur20200001LeafOfflineDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.huake.device.domain.generator.EvaResTur20200001LeafOffline;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface EvaResTur20200001LeafOfflineMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(time, unit, v0, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22, v23, v24, v25, v26, v27, v28, v29, v30, v31, v32, v33, v34, v35, v36, v37, v38, v39, v40, v41, v42, v43, v44, v45, v46, v47, v48, v49, v50, v51, v52, v53, v54, v55, v56, v57, v58, v59, v60, v61, v62, v63, v64, v65, v66, v67, v68, v69, v70, v71, v72, v73, v74, v75, v76, v77, v78, v79, v80, v81, v82, v83, v84, v85, v86, v87, v88, v89, v90, v91, v92, v93, v94, v95, v96, v97, v98, v99, v100, v101, v102, v103, v104, v105, v106, v107, v108);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<EvaResTur20200001LeafOffline> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<EvaResTur20200001LeafOffline> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EvaResTur20200001LeafOfflineResult")
    Optional<EvaResTur20200001LeafOffline> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EvaResTur20200001LeafOfflineResult", value = {
        @Result(column="time", property="time", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="unit", property="unit", jdbcType=JdbcType.TINYINT, id=true),
        @Result(column="v0", property="v0", jdbcType=JdbcType.REAL),
        @Result(column="v1", property="v1", jdbcType=JdbcType.REAL),
        @Result(column="v2", property="v2", jdbcType=JdbcType.REAL),
        @Result(column="v3", property="v3", jdbcType=JdbcType.REAL),
        @Result(column="v4", property="v4", jdbcType=JdbcType.REAL),
        @Result(column="v5", property="v5", jdbcType=JdbcType.REAL),
        @Result(column="v6", property="v6", jdbcType=JdbcType.REAL),
        @Result(column="v7", property="v7", jdbcType=JdbcType.REAL),
        @Result(column="v8", property="v8", jdbcType=JdbcType.REAL),
        @Result(column="v9", property="v9", jdbcType=JdbcType.REAL),
        @Result(column="v10", property="v10", jdbcType=JdbcType.REAL),
        @Result(column="v11", property="v11", jdbcType=JdbcType.REAL),
        @Result(column="v12", property="v12", jdbcType=JdbcType.REAL),
        @Result(column="v13", property="v13", jdbcType=JdbcType.REAL),
        @Result(column="v14", property="v14", jdbcType=JdbcType.REAL),
        @Result(column="v15", property="v15", jdbcType=JdbcType.REAL),
        @Result(column="v16", property="v16", jdbcType=JdbcType.REAL),
        @Result(column="v17", property="v17", jdbcType=JdbcType.REAL),
        @Result(column="v18", property="v18", jdbcType=JdbcType.REAL),
        @Result(column="v19", property="v19", jdbcType=JdbcType.REAL),
        @Result(column="v20", property="v20", jdbcType=JdbcType.REAL),
        @Result(column="v21", property="v21", jdbcType=JdbcType.REAL),
        @Result(column="v22", property="v22", jdbcType=JdbcType.REAL),
        @Result(column="v23", property="v23", jdbcType=JdbcType.REAL),
        @Result(column="v24", property="v24", jdbcType=JdbcType.REAL),
        @Result(column="v25", property="v25", jdbcType=JdbcType.REAL),
        @Result(column="v26", property="v26", jdbcType=JdbcType.REAL),
        @Result(column="v27", property="v27", jdbcType=JdbcType.REAL),
        @Result(column="v28", property="v28", jdbcType=JdbcType.REAL),
        @Result(column="v29", property="v29", jdbcType=JdbcType.REAL),
        @Result(column="v30", property="v30", jdbcType=JdbcType.REAL),
        @Result(column="v31", property="v31", jdbcType=JdbcType.REAL),
        @Result(column="v32", property="v32", jdbcType=JdbcType.REAL),
        @Result(column="v33", property="v33", jdbcType=JdbcType.REAL),
        @Result(column="v34", property="v34", jdbcType=JdbcType.REAL),
        @Result(column="v35", property="v35", jdbcType=JdbcType.REAL),
        @Result(column="v36", property="v36", jdbcType=JdbcType.REAL),
        @Result(column="v37", property="v37", jdbcType=JdbcType.REAL),
        @Result(column="v38", property="v38", jdbcType=JdbcType.REAL),
        @Result(column="v39", property="v39", jdbcType=JdbcType.REAL),
        @Result(column="v40", property="v40", jdbcType=JdbcType.REAL),
        @Result(column="v41", property="v41", jdbcType=JdbcType.REAL),
        @Result(column="v42", property="v42", jdbcType=JdbcType.REAL),
        @Result(column="v43", property="v43", jdbcType=JdbcType.REAL),
        @Result(column="v44", property="v44", jdbcType=JdbcType.REAL),
        @Result(column="v45", property="v45", jdbcType=JdbcType.REAL),
        @Result(column="v46", property="v46", jdbcType=JdbcType.REAL),
        @Result(column="v47", property="v47", jdbcType=JdbcType.REAL),
        @Result(column="v48", property="v48", jdbcType=JdbcType.REAL),
        @Result(column="v49", property="v49", jdbcType=JdbcType.REAL),
        @Result(column="v50", property="v50", jdbcType=JdbcType.REAL),
        @Result(column="v51", property="v51", jdbcType=JdbcType.REAL),
        @Result(column="v52", property="v52", jdbcType=JdbcType.REAL),
        @Result(column="v53", property="v53", jdbcType=JdbcType.REAL),
        @Result(column="v54", property="v54", jdbcType=JdbcType.REAL),
        @Result(column="v55", property="v55", jdbcType=JdbcType.REAL),
        @Result(column="v56", property="v56", jdbcType=JdbcType.REAL),
        @Result(column="v57", property="v57", jdbcType=JdbcType.REAL),
        @Result(column="v58", property="v58", jdbcType=JdbcType.REAL),
        @Result(column="v59", property="v59", jdbcType=JdbcType.REAL),
        @Result(column="v60", property="v60", jdbcType=JdbcType.REAL),
        @Result(column="v61", property="v61", jdbcType=JdbcType.REAL),
        @Result(column="v62", property="v62", jdbcType=JdbcType.REAL),
        @Result(column="v63", property="v63", jdbcType=JdbcType.REAL),
        @Result(column="v64", property="v64", jdbcType=JdbcType.REAL),
        @Result(column="v65", property="v65", jdbcType=JdbcType.REAL),
        @Result(column="v66", property="v66", jdbcType=JdbcType.REAL),
        @Result(column="v67", property="v67", jdbcType=JdbcType.REAL),
        @Result(column="v68", property="v68", jdbcType=JdbcType.REAL),
        @Result(column="v69", property="v69", jdbcType=JdbcType.REAL),
        @Result(column="v70", property="v70", jdbcType=JdbcType.REAL),
        @Result(column="v71", property="v71", jdbcType=JdbcType.REAL),
        @Result(column="v72", property="v72", jdbcType=JdbcType.REAL),
        @Result(column="v73", property="v73", jdbcType=JdbcType.REAL),
        @Result(column="v74", property="v74", jdbcType=JdbcType.REAL),
        @Result(column="v75", property="v75", jdbcType=JdbcType.REAL),
        @Result(column="v76", property="v76", jdbcType=JdbcType.REAL),
        @Result(column="v77", property="v77", jdbcType=JdbcType.REAL),
        @Result(column="v78", property="v78", jdbcType=JdbcType.REAL),
        @Result(column="v79", property="v79", jdbcType=JdbcType.REAL),
        @Result(column="v80", property="v80", jdbcType=JdbcType.REAL),
        @Result(column="v81", property="v81", jdbcType=JdbcType.REAL),
        @Result(column="v82", property="v82", jdbcType=JdbcType.REAL),
        @Result(column="v83", property="v83", jdbcType=JdbcType.REAL),
        @Result(column="v84", property="v84", jdbcType=JdbcType.REAL),
        @Result(column="v85", property="v85", jdbcType=JdbcType.REAL),
        @Result(column="v86", property="v86", jdbcType=JdbcType.REAL),
        @Result(column="v87", property="v87", jdbcType=JdbcType.REAL),
        @Result(column="v88", property="v88", jdbcType=JdbcType.REAL),
        @Result(column="v89", property="v89", jdbcType=JdbcType.REAL),
        @Result(column="v90", property="v90", jdbcType=JdbcType.REAL),
        @Result(column="v91", property="v91", jdbcType=JdbcType.REAL),
        @Result(column="v92", property="v92", jdbcType=JdbcType.REAL),
        @Result(column="v93", property="v93", jdbcType=JdbcType.REAL),
        @Result(column="v94", property="v94", jdbcType=JdbcType.REAL),
        @Result(column="v95", property="v95", jdbcType=JdbcType.REAL),
        @Result(column="v96", property="v96", jdbcType=JdbcType.REAL),
        @Result(column="v97", property="v97", jdbcType=JdbcType.REAL),
        @Result(column="v98", property="v98", jdbcType=JdbcType.REAL),
        @Result(column="v99", property="v99", jdbcType=JdbcType.REAL),
        @Result(column="v100", property="v100", jdbcType=JdbcType.REAL),
        @Result(column="v101", property="v101", jdbcType=JdbcType.REAL),
        @Result(column="v102", property="v102", jdbcType=JdbcType.REAL),
        @Result(column="v103", property="v103", jdbcType=JdbcType.REAL),
        @Result(column="v104", property="v104", jdbcType=JdbcType.REAL),
        @Result(column="v105", property="v105", jdbcType=JdbcType.REAL),
        @Result(column="v106", property="v106", jdbcType=JdbcType.REAL),
        @Result(column="v107", property="v107", jdbcType=JdbcType.REAL),
        @Result(column="v108", property="v108", jdbcType=JdbcType.REAL)
    })
    List<EvaResTur20200001LeafOffline> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, evaResTur20200001LeafOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, evaResTur20200001LeafOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer time_, Byte unit_) {
        return delete(c -> 
            c.where(time, isEqualTo(time_))
            .and(unit, isEqualTo(unit_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(EvaResTur20200001LeafOffline record) {
        return MyBatis3Utils.insert(this::insert, record, evaResTur20200001LeafOffline, c ->
            c.map(time).toProperty("time")
            .map(unit).toProperty("unit")
            .map(v0).toProperty("v0")
            .map(v1).toProperty("v1")
            .map(v2).toProperty("v2")
            .map(v3).toProperty("v3")
            .map(v4).toProperty("v4")
            .map(v5).toProperty("v5")
            .map(v6).toProperty("v6")
            .map(v7).toProperty("v7")
            .map(v8).toProperty("v8")
            .map(v9).toProperty("v9")
            .map(v10).toProperty("v10")
            .map(v11).toProperty("v11")
            .map(v12).toProperty("v12")
            .map(v13).toProperty("v13")
            .map(v14).toProperty("v14")
            .map(v15).toProperty("v15")
            .map(v16).toProperty("v16")
            .map(v17).toProperty("v17")
            .map(v18).toProperty("v18")
            .map(v19).toProperty("v19")
            .map(v20).toProperty("v20")
            .map(v21).toProperty("v21")
            .map(v22).toProperty("v22")
            .map(v23).toProperty("v23")
            .map(v24).toProperty("v24")
            .map(v25).toProperty("v25")
            .map(v26).toProperty("v26")
            .map(v27).toProperty("v27")
            .map(v28).toProperty("v28")
            .map(v29).toProperty("v29")
            .map(v30).toProperty("v30")
            .map(v31).toProperty("v31")
            .map(v32).toProperty("v32")
            .map(v33).toProperty("v33")
            .map(v34).toProperty("v34")
            .map(v35).toProperty("v35")
            .map(v36).toProperty("v36")
            .map(v37).toProperty("v37")
            .map(v38).toProperty("v38")
            .map(v39).toProperty("v39")
            .map(v40).toProperty("v40")
            .map(v41).toProperty("v41")
            .map(v42).toProperty("v42")
            .map(v43).toProperty("v43")
            .map(v44).toProperty("v44")
            .map(v45).toProperty("v45")
            .map(v46).toProperty("v46")
            .map(v47).toProperty("v47")
            .map(v48).toProperty("v48")
            .map(v49).toProperty("v49")
            .map(v50).toProperty("v50")
            .map(v51).toProperty("v51")
            .map(v52).toProperty("v52")
            .map(v53).toProperty("v53")
            .map(v54).toProperty("v54")
            .map(v55).toProperty("v55")
            .map(v56).toProperty("v56")
            .map(v57).toProperty("v57")
            .map(v58).toProperty("v58")
            .map(v59).toProperty("v59")
            .map(v60).toProperty("v60")
            .map(v61).toProperty("v61")
            .map(v62).toProperty("v62")
            .map(v63).toProperty("v63")
            .map(v64).toProperty("v64")
            .map(v65).toProperty("v65")
            .map(v66).toProperty("v66")
            .map(v67).toProperty("v67")
            .map(v68).toProperty("v68")
            .map(v69).toProperty("v69")
            .map(v70).toProperty("v70")
            .map(v71).toProperty("v71")
            .map(v72).toProperty("v72")
            .map(v73).toProperty("v73")
            .map(v74).toProperty("v74")
            .map(v75).toProperty("v75")
            .map(v76).toProperty("v76")
            .map(v77).toProperty("v77")
            .map(v78).toProperty("v78")
            .map(v79).toProperty("v79")
            .map(v80).toProperty("v80")
            .map(v81).toProperty("v81")
            .map(v82).toProperty("v82")
            .map(v83).toProperty("v83")
            .map(v84).toProperty("v84")
            .map(v85).toProperty("v85")
            .map(v86).toProperty("v86")
            .map(v87).toProperty("v87")
            .map(v88).toProperty("v88")
            .map(v89).toProperty("v89")
            .map(v90).toProperty("v90")
            .map(v91).toProperty("v91")
            .map(v92).toProperty("v92")
            .map(v93).toProperty("v93")
            .map(v94).toProperty("v94")
            .map(v95).toProperty("v95")
            .map(v96).toProperty("v96")
            .map(v97).toProperty("v97")
            .map(v98).toProperty("v98")
            .map(v99).toProperty("v99")
            .map(v100).toProperty("v100")
            .map(v101).toProperty("v101")
            .map(v102).toProperty("v102")
            .map(v103).toProperty("v103")
            .map(v104).toProperty("v104")
            .map(v105).toProperty("v105")
            .map(v106).toProperty("v106")
            .map(v107).toProperty("v107")
            .map(v108).toProperty("v108")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<EvaResTur20200001LeafOffline> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, evaResTur20200001LeafOffline, c ->
            c.map(time).toProperty("time")
            .map(unit).toProperty("unit")
            .map(v0).toProperty("v0")
            .map(v1).toProperty("v1")
            .map(v2).toProperty("v2")
            .map(v3).toProperty("v3")
            .map(v4).toProperty("v4")
            .map(v5).toProperty("v5")
            .map(v6).toProperty("v6")
            .map(v7).toProperty("v7")
            .map(v8).toProperty("v8")
            .map(v9).toProperty("v9")
            .map(v10).toProperty("v10")
            .map(v11).toProperty("v11")
            .map(v12).toProperty("v12")
            .map(v13).toProperty("v13")
            .map(v14).toProperty("v14")
            .map(v15).toProperty("v15")
            .map(v16).toProperty("v16")
            .map(v17).toProperty("v17")
            .map(v18).toProperty("v18")
            .map(v19).toProperty("v19")
            .map(v20).toProperty("v20")
            .map(v21).toProperty("v21")
            .map(v22).toProperty("v22")
            .map(v23).toProperty("v23")
            .map(v24).toProperty("v24")
            .map(v25).toProperty("v25")
            .map(v26).toProperty("v26")
            .map(v27).toProperty("v27")
            .map(v28).toProperty("v28")
            .map(v29).toProperty("v29")
            .map(v30).toProperty("v30")
            .map(v31).toProperty("v31")
            .map(v32).toProperty("v32")
            .map(v33).toProperty("v33")
            .map(v34).toProperty("v34")
            .map(v35).toProperty("v35")
            .map(v36).toProperty("v36")
            .map(v37).toProperty("v37")
            .map(v38).toProperty("v38")
            .map(v39).toProperty("v39")
            .map(v40).toProperty("v40")
            .map(v41).toProperty("v41")
            .map(v42).toProperty("v42")
            .map(v43).toProperty("v43")
            .map(v44).toProperty("v44")
            .map(v45).toProperty("v45")
            .map(v46).toProperty("v46")
            .map(v47).toProperty("v47")
            .map(v48).toProperty("v48")
            .map(v49).toProperty("v49")
            .map(v50).toProperty("v50")
            .map(v51).toProperty("v51")
            .map(v52).toProperty("v52")
            .map(v53).toProperty("v53")
            .map(v54).toProperty("v54")
            .map(v55).toProperty("v55")
            .map(v56).toProperty("v56")
            .map(v57).toProperty("v57")
            .map(v58).toProperty("v58")
            .map(v59).toProperty("v59")
            .map(v60).toProperty("v60")
            .map(v61).toProperty("v61")
            .map(v62).toProperty("v62")
            .map(v63).toProperty("v63")
            .map(v64).toProperty("v64")
            .map(v65).toProperty("v65")
            .map(v66).toProperty("v66")
            .map(v67).toProperty("v67")
            .map(v68).toProperty("v68")
            .map(v69).toProperty("v69")
            .map(v70).toProperty("v70")
            .map(v71).toProperty("v71")
            .map(v72).toProperty("v72")
            .map(v73).toProperty("v73")
            .map(v74).toProperty("v74")
            .map(v75).toProperty("v75")
            .map(v76).toProperty("v76")
            .map(v77).toProperty("v77")
            .map(v78).toProperty("v78")
            .map(v79).toProperty("v79")
            .map(v80).toProperty("v80")
            .map(v81).toProperty("v81")
            .map(v82).toProperty("v82")
            .map(v83).toProperty("v83")
            .map(v84).toProperty("v84")
            .map(v85).toProperty("v85")
            .map(v86).toProperty("v86")
            .map(v87).toProperty("v87")
            .map(v88).toProperty("v88")
            .map(v89).toProperty("v89")
            .map(v90).toProperty("v90")
            .map(v91).toProperty("v91")
            .map(v92).toProperty("v92")
            .map(v93).toProperty("v93")
            .map(v94).toProperty("v94")
            .map(v95).toProperty("v95")
            .map(v96).toProperty("v96")
            .map(v97).toProperty("v97")
            .map(v98).toProperty("v98")
            .map(v99).toProperty("v99")
            .map(v100).toProperty("v100")
            .map(v101).toProperty("v101")
            .map(v102).toProperty("v102")
            .map(v103).toProperty("v103")
            .map(v104).toProperty("v104")
            .map(v105).toProperty("v105")
            .map(v106).toProperty("v106")
            .map(v107).toProperty("v107")
            .map(v108).toProperty("v108")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(EvaResTur20200001LeafOffline record) {
        return MyBatis3Utils.insert(this::insert, record, evaResTur20200001LeafOffline, c ->
            c.map(time).toPropertyWhenPresent("time", record::getTime)
            .map(unit).toPropertyWhenPresent("unit", record::getUnit)
            .map(v0).toPropertyWhenPresent("v0", record::getV0)
            .map(v1).toPropertyWhenPresent("v1", record::getV1)
            .map(v2).toPropertyWhenPresent("v2", record::getV2)
            .map(v3).toPropertyWhenPresent("v3", record::getV3)
            .map(v4).toPropertyWhenPresent("v4", record::getV4)
            .map(v5).toPropertyWhenPresent("v5", record::getV5)
            .map(v6).toPropertyWhenPresent("v6", record::getV6)
            .map(v7).toPropertyWhenPresent("v7", record::getV7)
            .map(v8).toPropertyWhenPresent("v8", record::getV8)
            .map(v9).toPropertyWhenPresent("v9", record::getV9)
            .map(v10).toPropertyWhenPresent("v10", record::getV10)
            .map(v11).toPropertyWhenPresent("v11", record::getV11)
            .map(v12).toPropertyWhenPresent("v12", record::getV12)
            .map(v13).toPropertyWhenPresent("v13", record::getV13)
            .map(v14).toPropertyWhenPresent("v14", record::getV14)
            .map(v15).toPropertyWhenPresent("v15", record::getV15)
            .map(v16).toPropertyWhenPresent("v16", record::getV16)
            .map(v17).toPropertyWhenPresent("v17", record::getV17)
            .map(v18).toPropertyWhenPresent("v18", record::getV18)
            .map(v19).toPropertyWhenPresent("v19", record::getV19)
            .map(v20).toPropertyWhenPresent("v20", record::getV20)
            .map(v21).toPropertyWhenPresent("v21", record::getV21)
            .map(v22).toPropertyWhenPresent("v22", record::getV22)
            .map(v23).toPropertyWhenPresent("v23", record::getV23)
            .map(v24).toPropertyWhenPresent("v24", record::getV24)
            .map(v25).toPropertyWhenPresent("v25", record::getV25)
            .map(v26).toPropertyWhenPresent("v26", record::getV26)
            .map(v27).toPropertyWhenPresent("v27", record::getV27)
            .map(v28).toPropertyWhenPresent("v28", record::getV28)
            .map(v29).toPropertyWhenPresent("v29", record::getV29)
            .map(v30).toPropertyWhenPresent("v30", record::getV30)
            .map(v31).toPropertyWhenPresent("v31", record::getV31)
            .map(v32).toPropertyWhenPresent("v32", record::getV32)
            .map(v33).toPropertyWhenPresent("v33", record::getV33)
            .map(v34).toPropertyWhenPresent("v34", record::getV34)
            .map(v35).toPropertyWhenPresent("v35", record::getV35)
            .map(v36).toPropertyWhenPresent("v36", record::getV36)
            .map(v37).toPropertyWhenPresent("v37", record::getV37)
            .map(v38).toPropertyWhenPresent("v38", record::getV38)
            .map(v39).toPropertyWhenPresent("v39", record::getV39)
            .map(v40).toPropertyWhenPresent("v40", record::getV40)
            .map(v41).toPropertyWhenPresent("v41", record::getV41)
            .map(v42).toPropertyWhenPresent("v42", record::getV42)
            .map(v43).toPropertyWhenPresent("v43", record::getV43)
            .map(v44).toPropertyWhenPresent("v44", record::getV44)
            .map(v45).toPropertyWhenPresent("v45", record::getV45)
            .map(v46).toPropertyWhenPresent("v46", record::getV46)
            .map(v47).toPropertyWhenPresent("v47", record::getV47)
            .map(v48).toPropertyWhenPresent("v48", record::getV48)
            .map(v49).toPropertyWhenPresent("v49", record::getV49)
            .map(v50).toPropertyWhenPresent("v50", record::getV50)
            .map(v51).toPropertyWhenPresent("v51", record::getV51)
            .map(v52).toPropertyWhenPresent("v52", record::getV52)
            .map(v53).toPropertyWhenPresent("v53", record::getV53)
            .map(v54).toPropertyWhenPresent("v54", record::getV54)
            .map(v55).toPropertyWhenPresent("v55", record::getV55)
            .map(v56).toPropertyWhenPresent("v56", record::getV56)
            .map(v57).toPropertyWhenPresent("v57", record::getV57)
            .map(v58).toPropertyWhenPresent("v58", record::getV58)
            .map(v59).toPropertyWhenPresent("v59", record::getV59)
            .map(v60).toPropertyWhenPresent("v60", record::getV60)
            .map(v61).toPropertyWhenPresent("v61", record::getV61)
            .map(v62).toPropertyWhenPresent("v62", record::getV62)
            .map(v63).toPropertyWhenPresent("v63", record::getV63)
            .map(v64).toPropertyWhenPresent("v64", record::getV64)
            .map(v65).toPropertyWhenPresent("v65", record::getV65)
            .map(v66).toPropertyWhenPresent("v66", record::getV66)
            .map(v67).toPropertyWhenPresent("v67", record::getV67)
            .map(v68).toPropertyWhenPresent("v68", record::getV68)
            .map(v69).toPropertyWhenPresent("v69", record::getV69)
            .map(v70).toPropertyWhenPresent("v70", record::getV70)
            .map(v71).toPropertyWhenPresent("v71", record::getV71)
            .map(v72).toPropertyWhenPresent("v72", record::getV72)
            .map(v73).toPropertyWhenPresent("v73", record::getV73)
            .map(v74).toPropertyWhenPresent("v74", record::getV74)
            .map(v75).toPropertyWhenPresent("v75", record::getV75)
            .map(v76).toPropertyWhenPresent("v76", record::getV76)
            .map(v77).toPropertyWhenPresent("v77", record::getV77)
            .map(v78).toPropertyWhenPresent("v78", record::getV78)
            .map(v79).toPropertyWhenPresent("v79", record::getV79)
            .map(v80).toPropertyWhenPresent("v80", record::getV80)
            .map(v81).toPropertyWhenPresent("v81", record::getV81)
            .map(v82).toPropertyWhenPresent("v82", record::getV82)
            .map(v83).toPropertyWhenPresent("v83", record::getV83)
            .map(v84).toPropertyWhenPresent("v84", record::getV84)
            .map(v85).toPropertyWhenPresent("v85", record::getV85)
            .map(v86).toPropertyWhenPresent("v86", record::getV86)
            .map(v87).toPropertyWhenPresent("v87", record::getV87)
            .map(v88).toPropertyWhenPresent("v88", record::getV88)
            .map(v89).toPropertyWhenPresent("v89", record::getV89)
            .map(v90).toPropertyWhenPresent("v90", record::getV90)
            .map(v91).toPropertyWhenPresent("v91", record::getV91)
            .map(v92).toPropertyWhenPresent("v92", record::getV92)
            .map(v93).toPropertyWhenPresent("v93", record::getV93)
            .map(v94).toPropertyWhenPresent("v94", record::getV94)
            .map(v95).toPropertyWhenPresent("v95", record::getV95)
            .map(v96).toPropertyWhenPresent("v96", record::getV96)
            .map(v97).toPropertyWhenPresent("v97", record::getV97)
            .map(v98).toPropertyWhenPresent("v98", record::getV98)
            .map(v99).toPropertyWhenPresent("v99", record::getV99)
            .map(v100).toPropertyWhenPresent("v100", record::getV100)
            .map(v101).toPropertyWhenPresent("v101", record::getV101)
            .map(v102).toPropertyWhenPresent("v102", record::getV102)
            .map(v103).toPropertyWhenPresent("v103", record::getV103)
            .map(v104).toPropertyWhenPresent("v104", record::getV104)
            .map(v105).toPropertyWhenPresent("v105", record::getV105)
            .map(v106).toPropertyWhenPresent("v106", record::getV106)
            .map(v107).toPropertyWhenPresent("v107", record::getV107)
            .map(v108).toPropertyWhenPresent("v108", record::getV108)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaResTur20200001LeafOffline> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, evaResTur20200001LeafOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaResTur20200001LeafOffline> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, evaResTur20200001LeafOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<EvaResTur20200001LeafOffline> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, evaResTur20200001LeafOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<EvaResTur20200001LeafOffline> selectByPrimaryKey(Integer time_, Byte unit_) {
        return selectOne(c ->
            c.where(time, isEqualTo(time_))
            .and(unit, isEqualTo(unit_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, evaResTur20200001LeafOffline, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(EvaResTur20200001LeafOffline record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(time).equalTo(record::getTime)
                .set(unit).equalTo(record::getUnit)
                .set(v0).equalTo(record::getV0)
                .set(v1).equalTo(record::getV1)
                .set(v2).equalTo(record::getV2)
                .set(v3).equalTo(record::getV3)
                .set(v4).equalTo(record::getV4)
                .set(v5).equalTo(record::getV5)
                .set(v6).equalTo(record::getV6)
                .set(v7).equalTo(record::getV7)
                .set(v8).equalTo(record::getV8)
                .set(v9).equalTo(record::getV9)
                .set(v10).equalTo(record::getV10)
                .set(v11).equalTo(record::getV11)
                .set(v12).equalTo(record::getV12)
                .set(v13).equalTo(record::getV13)
                .set(v14).equalTo(record::getV14)
                .set(v15).equalTo(record::getV15)
                .set(v16).equalTo(record::getV16)
                .set(v17).equalTo(record::getV17)
                .set(v18).equalTo(record::getV18)
                .set(v19).equalTo(record::getV19)
                .set(v20).equalTo(record::getV20)
                .set(v21).equalTo(record::getV21)
                .set(v22).equalTo(record::getV22)
                .set(v23).equalTo(record::getV23)
                .set(v24).equalTo(record::getV24)
                .set(v25).equalTo(record::getV25)
                .set(v26).equalTo(record::getV26)
                .set(v27).equalTo(record::getV27)
                .set(v28).equalTo(record::getV28)
                .set(v29).equalTo(record::getV29)
                .set(v30).equalTo(record::getV30)
                .set(v31).equalTo(record::getV31)
                .set(v32).equalTo(record::getV32)
                .set(v33).equalTo(record::getV33)
                .set(v34).equalTo(record::getV34)
                .set(v35).equalTo(record::getV35)
                .set(v36).equalTo(record::getV36)
                .set(v37).equalTo(record::getV37)
                .set(v38).equalTo(record::getV38)
                .set(v39).equalTo(record::getV39)
                .set(v40).equalTo(record::getV40)
                .set(v41).equalTo(record::getV41)
                .set(v42).equalTo(record::getV42)
                .set(v43).equalTo(record::getV43)
                .set(v44).equalTo(record::getV44)
                .set(v45).equalTo(record::getV45)
                .set(v46).equalTo(record::getV46)
                .set(v47).equalTo(record::getV47)
                .set(v48).equalTo(record::getV48)
                .set(v49).equalTo(record::getV49)
                .set(v50).equalTo(record::getV50)
                .set(v51).equalTo(record::getV51)
                .set(v52).equalTo(record::getV52)
                .set(v53).equalTo(record::getV53)
                .set(v54).equalTo(record::getV54)
                .set(v55).equalTo(record::getV55)
                .set(v56).equalTo(record::getV56)
                .set(v57).equalTo(record::getV57)
                .set(v58).equalTo(record::getV58)
                .set(v59).equalTo(record::getV59)
                .set(v60).equalTo(record::getV60)
                .set(v61).equalTo(record::getV61)
                .set(v62).equalTo(record::getV62)
                .set(v63).equalTo(record::getV63)
                .set(v64).equalTo(record::getV64)
                .set(v65).equalTo(record::getV65)
                .set(v66).equalTo(record::getV66)
                .set(v67).equalTo(record::getV67)
                .set(v68).equalTo(record::getV68)
                .set(v69).equalTo(record::getV69)
                .set(v70).equalTo(record::getV70)
                .set(v71).equalTo(record::getV71)
                .set(v72).equalTo(record::getV72)
                .set(v73).equalTo(record::getV73)
                .set(v74).equalTo(record::getV74)
                .set(v75).equalTo(record::getV75)
                .set(v76).equalTo(record::getV76)
                .set(v77).equalTo(record::getV77)
                .set(v78).equalTo(record::getV78)
                .set(v79).equalTo(record::getV79)
                .set(v80).equalTo(record::getV80)
                .set(v81).equalTo(record::getV81)
                .set(v82).equalTo(record::getV82)
                .set(v83).equalTo(record::getV83)
                .set(v84).equalTo(record::getV84)
                .set(v85).equalTo(record::getV85)
                .set(v86).equalTo(record::getV86)
                .set(v87).equalTo(record::getV87)
                .set(v88).equalTo(record::getV88)
                .set(v89).equalTo(record::getV89)
                .set(v90).equalTo(record::getV90)
                .set(v91).equalTo(record::getV91)
                .set(v92).equalTo(record::getV92)
                .set(v93).equalTo(record::getV93)
                .set(v94).equalTo(record::getV94)
                .set(v95).equalTo(record::getV95)
                .set(v96).equalTo(record::getV96)
                .set(v97).equalTo(record::getV97)
                .set(v98).equalTo(record::getV98)
                .set(v99).equalTo(record::getV99)
                .set(v100).equalTo(record::getV100)
                .set(v101).equalTo(record::getV101)
                .set(v102).equalTo(record::getV102)
                .set(v103).equalTo(record::getV103)
                .set(v104).equalTo(record::getV104)
                .set(v105).equalTo(record::getV105)
                .set(v106).equalTo(record::getV106)
                .set(v107).equalTo(record::getV107)
                .set(v108).equalTo(record::getV108);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(EvaResTur20200001LeafOffline record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(time).equalToWhenPresent(record::getTime)
                .set(unit).equalToWhenPresent(record::getUnit)
                .set(v0).equalToWhenPresent(record::getV0)
                .set(v1).equalToWhenPresent(record::getV1)
                .set(v2).equalToWhenPresent(record::getV2)
                .set(v3).equalToWhenPresent(record::getV3)
                .set(v4).equalToWhenPresent(record::getV4)
                .set(v5).equalToWhenPresent(record::getV5)
                .set(v6).equalToWhenPresent(record::getV6)
                .set(v7).equalToWhenPresent(record::getV7)
                .set(v8).equalToWhenPresent(record::getV8)
                .set(v9).equalToWhenPresent(record::getV9)
                .set(v10).equalToWhenPresent(record::getV10)
                .set(v11).equalToWhenPresent(record::getV11)
                .set(v12).equalToWhenPresent(record::getV12)
                .set(v13).equalToWhenPresent(record::getV13)
                .set(v14).equalToWhenPresent(record::getV14)
                .set(v15).equalToWhenPresent(record::getV15)
                .set(v16).equalToWhenPresent(record::getV16)
                .set(v17).equalToWhenPresent(record::getV17)
                .set(v18).equalToWhenPresent(record::getV18)
                .set(v19).equalToWhenPresent(record::getV19)
                .set(v20).equalToWhenPresent(record::getV20)
                .set(v21).equalToWhenPresent(record::getV21)
                .set(v22).equalToWhenPresent(record::getV22)
                .set(v23).equalToWhenPresent(record::getV23)
                .set(v24).equalToWhenPresent(record::getV24)
                .set(v25).equalToWhenPresent(record::getV25)
                .set(v26).equalToWhenPresent(record::getV26)
                .set(v27).equalToWhenPresent(record::getV27)
                .set(v28).equalToWhenPresent(record::getV28)
                .set(v29).equalToWhenPresent(record::getV29)
                .set(v30).equalToWhenPresent(record::getV30)
                .set(v31).equalToWhenPresent(record::getV31)
                .set(v32).equalToWhenPresent(record::getV32)
                .set(v33).equalToWhenPresent(record::getV33)
                .set(v34).equalToWhenPresent(record::getV34)
                .set(v35).equalToWhenPresent(record::getV35)
                .set(v36).equalToWhenPresent(record::getV36)
                .set(v37).equalToWhenPresent(record::getV37)
                .set(v38).equalToWhenPresent(record::getV38)
                .set(v39).equalToWhenPresent(record::getV39)
                .set(v40).equalToWhenPresent(record::getV40)
                .set(v41).equalToWhenPresent(record::getV41)
                .set(v42).equalToWhenPresent(record::getV42)
                .set(v43).equalToWhenPresent(record::getV43)
                .set(v44).equalToWhenPresent(record::getV44)
                .set(v45).equalToWhenPresent(record::getV45)
                .set(v46).equalToWhenPresent(record::getV46)
                .set(v47).equalToWhenPresent(record::getV47)
                .set(v48).equalToWhenPresent(record::getV48)
                .set(v49).equalToWhenPresent(record::getV49)
                .set(v50).equalToWhenPresent(record::getV50)
                .set(v51).equalToWhenPresent(record::getV51)
                .set(v52).equalToWhenPresent(record::getV52)
                .set(v53).equalToWhenPresent(record::getV53)
                .set(v54).equalToWhenPresent(record::getV54)
                .set(v55).equalToWhenPresent(record::getV55)
                .set(v56).equalToWhenPresent(record::getV56)
                .set(v57).equalToWhenPresent(record::getV57)
                .set(v58).equalToWhenPresent(record::getV58)
                .set(v59).equalToWhenPresent(record::getV59)
                .set(v60).equalToWhenPresent(record::getV60)
                .set(v61).equalToWhenPresent(record::getV61)
                .set(v62).equalToWhenPresent(record::getV62)
                .set(v63).equalToWhenPresent(record::getV63)
                .set(v64).equalToWhenPresent(record::getV64)
                .set(v65).equalToWhenPresent(record::getV65)
                .set(v66).equalToWhenPresent(record::getV66)
                .set(v67).equalToWhenPresent(record::getV67)
                .set(v68).equalToWhenPresent(record::getV68)
                .set(v69).equalToWhenPresent(record::getV69)
                .set(v70).equalToWhenPresent(record::getV70)
                .set(v71).equalToWhenPresent(record::getV71)
                .set(v72).equalToWhenPresent(record::getV72)
                .set(v73).equalToWhenPresent(record::getV73)
                .set(v74).equalToWhenPresent(record::getV74)
                .set(v75).equalToWhenPresent(record::getV75)
                .set(v76).equalToWhenPresent(record::getV76)
                .set(v77).equalToWhenPresent(record::getV77)
                .set(v78).equalToWhenPresent(record::getV78)
                .set(v79).equalToWhenPresent(record::getV79)
                .set(v80).equalToWhenPresent(record::getV80)
                .set(v81).equalToWhenPresent(record::getV81)
                .set(v82).equalToWhenPresent(record::getV82)
                .set(v83).equalToWhenPresent(record::getV83)
                .set(v84).equalToWhenPresent(record::getV84)
                .set(v85).equalToWhenPresent(record::getV85)
                .set(v86).equalToWhenPresent(record::getV86)
                .set(v87).equalToWhenPresent(record::getV87)
                .set(v88).equalToWhenPresent(record::getV88)
                .set(v89).equalToWhenPresent(record::getV89)
                .set(v90).equalToWhenPresent(record::getV90)
                .set(v91).equalToWhenPresent(record::getV91)
                .set(v92).equalToWhenPresent(record::getV92)
                .set(v93).equalToWhenPresent(record::getV93)
                .set(v94).equalToWhenPresent(record::getV94)
                .set(v95).equalToWhenPresent(record::getV95)
                .set(v96).equalToWhenPresent(record::getV96)
                .set(v97).equalToWhenPresent(record::getV97)
                .set(v98).equalToWhenPresent(record::getV98)
                .set(v99).equalToWhenPresent(record::getV99)
                .set(v100).equalToWhenPresent(record::getV100)
                .set(v101).equalToWhenPresent(record::getV101)
                .set(v102).equalToWhenPresent(record::getV102)
                .set(v103).equalToWhenPresent(record::getV103)
                .set(v104).equalToWhenPresent(record::getV104)
                .set(v105).equalToWhenPresent(record::getV105)
                .set(v106).equalToWhenPresent(record::getV106)
                .set(v107).equalToWhenPresent(record::getV107)
                .set(v108).equalToWhenPresent(record::getV108);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(EvaResTur20200001LeafOffline record) {
        return update(c ->
            c.set(v0).equalTo(record::getV0)
            .set(v1).equalTo(record::getV1)
            .set(v2).equalTo(record::getV2)
            .set(v3).equalTo(record::getV3)
            .set(v4).equalTo(record::getV4)
            .set(v5).equalTo(record::getV5)
            .set(v6).equalTo(record::getV6)
            .set(v7).equalTo(record::getV7)
            .set(v8).equalTo(record::getV8)
            .set(v9).equalTo(record::getV9)
            .set(v10).equalTo(record::getV10)
            .set(v11).equalTo(record::getV11)
            .set(v12).equalTo(record::getV12)
            .set(v13).equalTo(record::getV13)
            .set(v14).equalTo(record::getV14)
            .set(v15).equalTo(record::getV15)
            .set(v16).equalTo(record::getV16)
            .set(v17).equalTo(record::getV17)
            .set(v18).equalTo(record::getV18)
            .set(v19).equalTo(record::getV19)
            .set(v20).equalTo(record::getV20)
            .set(v21).equalTo(record::getV21)
            .set(v22).equalTo(record::getV22)
            .set(v23).equalTo(record::getV23)
            .set(v24).equalTo(record::getV24)
            .set(v25).equalTo(record::getV25)
            .set(v26).equalTo(record::getV26)
            .set(v27).equalTo(record::getV27)
            .set(v28).equalTo(record::getV28)
            .set(v29).equalTo(record::getV29)
            .set(v30).equalTo(record::getV30)
            .set(v31).equalTo(record::getV31)
            .set(v32).equalTo(record::getV32)
            .set(v33).equalTo(record::getV33)
            .set(v34).equalTo(record::getV34)
            .set(v35).equalTo(record::getV35)
            .set(v36).equalTo(record::getV36)
            .set(v37).equalTo(record::getV37)
            .set(v38).equalTo(record::getV38)
            .set(v39).equalTo(record::getV39)
            .set(v40).equalTo(record::getV40)
            .set(v41).equalTo(record::getV41)
            .set(v42).equalTo(record::getV42)
            .set(v43).equalTo(record::getV43)
            .set(v44).equalTo(record::getV44)
            .set(v45).equalTo(record::getV45)
            .set(v46).equalTo(record::getV46)
            .set(v47).equalTo(record::getV47)
            .set(v48).equalTo(record::getV48)
            .set(v49).equalTo(record::getV49)
            .set(v50).equalTo(record::getV50)
            .set(v51).equalTo(record::getV51)
            .set(v52).equalTo(record::getV52)
            .set(v53).equalTo(record::getV53)
            .set(v54).equalTo(record::getV54)
            .set(v55).equalTo(record::getV55)
            .set(v56).equalTo(record::getV56)
            .set(v57).equalTo(record::getV57)
            .set(v58).equalTo(record::getV58)
            .set(v59).equalTo(record::getV59)
            .set(v60).equalTo(record::getV60)
            .set(v61).equalTo(record::getV61)
            .set(v62).equalTo(record::getV62)
            .set(v63).equalTo(record::getV63)
            .set(v64).equalTo(record::getV64)
            .set(v65).equalTo(record::getV65)
            .set(v66).equalTo(record::getV66)
            .set(v67).equalTo(record::getV67)
            .set(v68).equalTo(record::getV68)
            .set(v69).equalTo(record::getV69)
            .set(v70).equalTo(record::getV70)
            .set(v71).equalTo(record::getV71)
            .set(v72).equalTo(record::getV72)
            .set(v73).equalTo(record::getV73)
            .set(v74).equalTo(record::getV74)
            .set(v75).equalTo(record::getV75)
            .set(v76).equalTo(record::getV76)
            .set(v77).equalTo(record::getV77)
            .set(v78).equalTo(record::getV78)
            .set(v79).equalTo(record::getV79)
            .set(v80).equalTo(record::getV80)
            .set(v81).equalTo(record::getV81)
            .set(v82).equalTo(record::getV82)
            .set(v83).equalTo(record::getV83)
            .set(v84).equalTo(record::getV84)
            .set(v85).equalTo(record::getV85)
            .set(v86).equalTo(record::getV86)
            .set(v87).equalTo(record::getV87)
            .set(v88).equalTo(record::getV88)
            .set(v89).equalTo(record::getV89)
            .set(v90).equalTo(record::getV90)
            .set(v91).equalTo(record::getV91)
            .set(v92).equalTo(record::getV92)
            .set(v93).equalTo(record::getV93)
            .set(v94).equalTo(record::getV94)
            .set(v95).equalTo(record::getV95)
            .set(v96).equalTo(record::getV96)
            .set(v97).equalTo(record::getV97)
            .set(v98).equalTo(record::getV98)
            .set(v99).equalTo(record::getV99)
            .set(v100).equalTo(record::getV100)
            .set(v101).equalTo(record::getV101)
            .set(v102).equalTo(record::getV102)
            .set(v103).equalTo(record::getV103)
            .set(v104).equalTo(record::getV104)
            .set(v105).equalTo(record::getV105)
            .set(v106).equalTo(record::getV106)
            .set(v107).equalTo(record::getV107)
            .set(v108).equalTo(record::getV108)
            .where(time, isEqualTo(record::getTime))
            .and(unit, isEqualTo(record::getUnit))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(EvaResTur20200001LeafOffline record) {
        return update(c ->
            c.set(v0).equalToWhenPresent(record::getV0)
            .set(v1).equalToWhenPresent(record::getV1)
            .set(v2).equalToWhenPresent(record::getV2)
            .set(v3).equalToWhenPresent(record::getV3)
            .set(v4).equalToWhenPresent(record::getV4)
            .set(v5).equalToWhenPresent(record::getV5)
            .set(v6).equalToWhenPresent(record::getV6)
            .set(v7).equalToWhenPresent(record::getV7)
            .set(v8).equalToWhenPresent(record::getV8)
            .set(v9).equalToWhenPresent(record::getV9)
            .set(v10).equalToWhenPresent(record::getV10)
            .set(v11).equalToWhenPresent(record::getV11)
            .set(v12).equalToWhenPresent(record::getV12)
            .set(v13).equalToWhenPresent(record::getV13)
            .set(v14).equalToWhenPresent(record::getV14)
            .set(v15).equalToWhenPresent(record::getV15)
            .set(v16).equalToWhenPresent(record::getV16)
            .set(v17).equalToWhenPresent(record::getV17)
            .set(v18).equalToWhenPresent(record::getV18)
            .set(v19).equalToWhenPresent(record::getV19)
            .set(v20).equalToWhenPresent(record::getV20)
            .set(v21).equalToWhenPresent(record::getV21)
            .set(v22).equalToWhenPresent(record::getV22)
            .set(v23).equalToWhenPresent(record::getV23)
            .set(v24).equalToWhenPresent(record::getV24)
            .set(v25).equalToWhenPresent(record::getV25)
            .set(v26).equalToWhenPresent(record::getV26)
            .set(v27).equalToWhenPresent(record::getV27)
            .set(v28).equalToWhenPresent(record::getV28)
            .set(v29).equalToWhenPresent(record::getV29)
            .set(v30).equalToWhenPresent(record::getV30)
            .set(v31).equalToWhenPresent(record::getV31)
            .set(v32).equalToWhenPresent(record::getV32)
            .set(v33).equalToWhenPresent(record::getV33)
            .set(v34).equalToWhenPresent(record::getV34)
            .set(v35).equalToWhenPresent(record::getV35)
            .set(v36).equalToWhenPresent(record::getV36)
            .set(v37).equalToWhenPresent(record::getV37)
            .set(v38).equalToWhenPresent(record::getV38)
            .set(v39).equalToWhenPresent(record::getV39)
            .set(v40).equalToWhenPresent(record::getV40)
            .set(v41).equalToWhenPresent(record::getV41)
            .set(v42).equalToWhenPresent(record::getV42)
            .set(v43).equalToWhenPresent(record::getV43)
            .set(v44).equalToWhenPresent(record::getV44)
            .set(v45).equalToWhenPresent(record::getV45)
            .set(v46).equalToWhenPresent(record::getV46)
            .set(v47).equalToWhenPresent(record::getV47)
            .set(v48).equalToWhenPresent(record::getV48)
            .set(v49).equalToWhenPresent(record::getV49)
            .set(v50).equalToWhenPresent(record::getV50)
            .set(v51).equalToWhenPresent(record::getV51)
            .set(v52).equalToWhenPresent(record::getV52)
            .set(v53).equalToWhenPresent(record::getV53)
            .set(v54).equalToWhenPresent(record::getV54)
            .set(v55).equalToWhenPresent(record::getV55)
            .set(v56).equalToWhenPresent(record::getV56)
            .set(v57).equalToWhenPresent(record::getV57)
            .set(v58).equalToWhenPresent(record::getV58)
            .set(v59).equalToWhenPresent(record::getV59)
            .set(v60).equalToWhenPresent(record::getV60)
            .set(v61).equalToWhenPresent(record::getV61)
            .set(v62).equalToWhenPresent(record::getV62)
            .set(v63).equalToWhenPresent(record::getV63)
            .set(v64).equalToWhenPresent(record::getV64)
            .set(v65).equalToWhenPresent(record::getV65)
            .set(v66).equalToWhenPresent(record::getV66)
            .set(v67).equalToWhenPresent(record::getV67)
            .set(v68).equalToWhenPresent(record::getV68)
            .set(v69).equalToWhenPresent(record::getV69)
            .set(v70).equalToWhenPresent(record::getV70)
            .set(v71).equalToWhenPresent(record::getV71)
            .set(v72).equalToWhenPresent(record::getV72)
            .set(v73).equalToWhenPresent(record::getV73)
            .set(v74).equalToWhenPresent(record::getV74)
            .set(v75).equalToWhenPresent(record::getV75)
            .set(v76).equalToWhenPresent(record::getV76)
            .set(v77).equalToWhenPresent(record::getV77)
            .set(v78).equalToWhenPresent(record::getV78)
            .set(v79).equalToWhenPresent(record::getV79)
            .set(v80).equalToWhenPresent(record::getV80)
            .set(v81).equalToWhenPresent(record::getV81)
            .set(v82).equalToWhenPresent(record::getV82)
            .set(v83).equalToWhenPresent(record::getV83)
            .set(v84).equalToWhenPresent(record::getV84)
            .set(v85).equalToWhenPresent(record::getV85)
            .set(v86).equalToWhenPresent(record::getV86)
            .set(v87).equalToWhenPresent(record::getV87)
            .set(v88).equalToWhenPresent(record::getV88)
            .set(v89).equalToWhenPresent(record::getV89)
            .set(v90).equalToWhenPresent(record::getV90)
            .set(v91).equalToWhenPresent(record::getV91)
            .set(v92).equalToWhenPresent(record::getV92)
            .set(v93).equalToWhenPresent(record::getV93)
            .set(v94).equalToWhenPresent(record::getV94)
            .set(v95).equalToWhenPresent(record::getV95)
            .set(v96).equalToWhenPresent(record::getV96)
            .set(v97).equalToWhenPresent(record::getV97)
            .set(v98).equalToWhenPresent(record::getV98)
            .set(v99).equalToWhenPresent(record::getV99)
            .set(v100).equalToWhenPresent(record::getV100)
            .set(v101).equalToWhenPresent(record::getV101)
            .set(v102).equalToWhenPresent(record::getV102)
            .set(v103).equalToWhenPresent(record::getV103)
            .set(v104).equalToWhenPresent(record::getV104)
            .set(v105).equalToWhenPresent(record::getV105)
            .set(v106).equalToWhenPresent(record::getV106)
            .set(v107).equalToWhenPresent(record::getV107)
            .set(v108).equalToWhenPresent(record::getV108)
            .where(time, isEqualTo(record::getTime))
            .and(unit, isEqualTo(record::getUnit))
        );
    }
}