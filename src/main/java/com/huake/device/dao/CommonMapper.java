package com.huake.device.dao;

import com.huake.device.domain.Tvf;
import com.huake.device.domain.generator.Hdbid;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface CommonMapper {

    @Select("SELECT unit, type, count(0) count FROM tre_warn t\n" +
            "where t.warn = 1 and t.time between unix_timestamp(now()) - 60*60*24 and unix_timestamp(now())\n" +
            "group by t.unit")
    List<Map> get24HWarnEventSummary();

    @Select("SELECT * FROM hdy.tre_warn t\n" +
            "where t.time between unix_timestamp(now()) - 60*60*24 and unix_timestamp(now())")
    List<Map> get24HWarnEventList();

    @Select({
            "<script>",
            "SELECT CONCAT(prefix, name) fullname FROM classifiedqueryprefix b left join classifiedquerytree a on ",
            "b.classifiedid = a.id",
            "where name in",
            "<foreach collection='list' item='item' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<String> getPointFullname(@Param("list") List<String> list);

    @Select({
            "<script>",
            "SELECT id, name FROM float_id ",
            "where name in",
            "<foreach collection='list' item='item' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<Map<String, String>> getFloatList(@Param("list") Set<String> list);

    @Select({
            "<script>",
            "SELECT id, name FROM bool_id ",
            "where name in",
            "<foreach collection='list' item='item' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })
    List<Map<String, String>> getBoolList(@Param("list") Set<String> list);

    @Select({"SELECT * FROM hdbid"})
    List<Hdbid> getPointList();

    @Select("SELECT prefix FROM classifiedqueryprefix WHERE classifiedid=#{key}")
    String getPointFullName(String key);

    @Select("SELECT * FROM hdbid WHERE `name`=#{name}")
    Hdbid getPointListByName(String name);
}
