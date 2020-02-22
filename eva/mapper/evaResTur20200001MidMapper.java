package hdy.eva.mapper;

import hdy.eva.entity.evaResTur20200001Mid;
import hdy.eva.entity.evaResTur20200001MidExample;
import hdy.eva.entity.evaResTur20200001MidKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface evaResTur20200001MidMapper {
    long countByExample(evaResTur20200001MidExample example);

    int deleteByExample(evaResTur20200001MidExample example);

    int deleteByPrimaryKey(evaResTur20200001MidKey key);

    int insert(evaResTur20200001Mid record);

    int insertSelective(evaResTur20200001Mid record);

    List<evaResTur20200001Mid> selectByExample(evaResTur20200001MidExample example);

    evaResTur20200001Mid selectByPrimaryKey(evaResTur20200001MidKey key);

    int updateByExampleSelective(@Param("record") evaResTur20200001Mid record, @Param("example") evaResTur20200001MidExample example);

    int updateByExample(@Param("record") evaResTur20200001Mid record, @Param("example") evaResTur20200001MidExample example);

    int updateByPrimaryKeySelective(evaResTur20200001Mid record);

    int updateByPrimaryKey(evaResTur20200001Mid record);
}