package org.linlinjava.device.dao;

import org.apache.ibatis.annotations.*;
import org.linlinjava.device.domain.Tvf;

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

    @Select("SELECT * FROM ${table} WHERE t >= #{start} && t < #{end}")
    @Results({
            @Result(property = "t", column = "t"),
            @Result(property = "v", column = "v"),
            @Result(property = "f", column = "f")
    })
    List<Tvf> selectBetween(@Param("table") String table, @Param("start") long start, @Param("end") long end);


    @Select("SELECT avg(v) FROM ${table} WHERE t >= #{start} && t < #{end}")
    Float avgBetween(@Param("table") String table, @Param("start") long start, @Param("end") long end);

    @Select("SELECT DATE_FORMAT(FROM_UNIXTIME(t),'%Y-%m-%d %H:%i') minute, AVG(v) avg FROM ${table} WHERE t >= #{start} && t < #{end} group by minute")
    List<Map> avgBetweenInM(@Param("table") String table, @Param("start") long start, @Param("end") long end);
}
