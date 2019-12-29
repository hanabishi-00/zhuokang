package org.linlinjava.device.dao;

import org.apache.ibatis.annotations.*;
import org.linlinjava.device.domain.Tvf;

import java.util.List;

@Mapper
public interface TvfMapper {
    @Select("SELECT * FROM ${table}")
    @Results({
            @Result(property = "t", column = "t"),
            @Result(property = "v", column = "v"),
            @Result(property = "f", column = "f")
    })
    List<Tvf> select(@Param("table") String table);

    @Select("SELECT * FROM ${table} where t >= #{start} && t < #{end}")
    @Results({
            @Result(property = "t", column = "t"),
            @Result(property = "v", column = "v"),
            @Result(property = "f", column = "f")
    })
    List<Tvf> selectBetween(@Param("table") String table, @Param("start") long start, @Param("end") long end);

    @Select("SELECT avg(v) FROM ${table} where t >= #{start} && t < #{end}")
    Float avgBetween(@Param("table") String table, @Param("start") long start, @Param("end") long end);
}
