package com.huake.device.dao;

import org.apache.ibatis.annotations.*;
import com.huake.device.domain.Tvf;

import java.util.List;
import java.util.Map;

@Mapper
public interface TvfMapper {
    @Select("SELECT * FROM ${table}")
    @Results({
            @Result(property = "t", column = "t"),
            @Result(property = "v", column = "v"),
            @Result(property = "f", column = "f")
    })
    List<Tvf> select(@Param("table") String table);

    @Select("SELECT count(*) FROM information_schema.TABLES WHERE table_name = #{table}")
    int exist(@Param("table") String table);

    @Select("SELECT * FROM ${table} WHERE t >= #{start} && t <= #{end}")
    @Results({
            @Result(property = "t", column = "t"),
            @Result(property = "v", column = "v"),
            @Result(property = "f", column = "f")
    })
    List<Tvf> selectBetween(@Param("table") String table, @Param("start") long start, @Param("end") long end);


    @Select("SELECT avg(v) FROM ${table} WHERE t >= #{start} && t <= #{end}")
    Float avgBetween(@Param("table") String table, @Param("start") long start, @Param("end") long end);

    @Select("SELECT DATE_FORMAT(FROM_UNIXTIME(t),'%Y-%m-%d %H:%i') time, AVG(v) avg FROM ${table} WHERE t >= #{start} && t <= #{end} group by time")
    List<Map> avgBetweenInM(@Param("table") String table, @Param("start") long start, @Param("end") long end);

    @Select("SELECT DATE_FORMAT(FROM_UNIXTIME(t),'%Y-%m-%d %H') time, AVG(v) avg FROM ${table} WHERE t >= #{start} && t <= #{end} group by time")
    List<Map> avgBetweenInH(@Param("table") String table, @Param("start") long start, @Param("end") long end);

    @Select("SELECT DATE_FORMAT(FROM_UNIXTIME(t),'%Y-%m-%d') time, AVG(v) avg FROM ${table} WHERE t >= #{start} && t <= #{end} group by time")
    List<Map> avgBetweenInD(@Param("table") String table, @Param("start") long start, @Param("end") long end);


    @Select("SELECT * FROM tre_deterpre_101001 WHERE pretime = ${preTime}")
    List<Map> tre_deterpre_101001(@Param("preTime") String preTime);

    @Select("SELECT * FROM eva_res_tur_model_201912010100 WHERE time >= #{start} && time <= #{end}")
    List<Map> eva_res_tur_model_201912010100(@Param("start") String start, @Param("end") String end);
}
