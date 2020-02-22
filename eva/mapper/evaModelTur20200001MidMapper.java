package hdy.eva.mapper;

import hdy.eva.entity.evaModelTur20200001Mid;
import hdy.eva.entity.evaModelTur20200001MidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface evaModelTur20200001MidMapper {
    long countByExample(evaModelTur20200001MidExample example);

    int deleteByExample(evaModelTur20200001MidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(evaModelTur20200001Mid record);

    int insertSelective(evaModelTur20200001Mid record);

    List<evaModelTur20200001Mid> selectByExample(evaModelTur20200001MidExample example);

    evaModelTur20200001Mid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") evaModelTur20200001Mid record, @Param("example") evaModelTur20200001MidExample example);

    int updateByExample(@Param("record") evaModelTur20200001Mid record, @Param("example") evaModelTur20200001MidExample example);

    int updateByPrimaryKeySelective(evaModelTur20200001Mid record);

    int updateByPrimaryKey(evaModelTur20200001Mid record);
}