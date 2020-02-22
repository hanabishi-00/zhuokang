package hdy.eva.mapper;

import hdy.eva.entity.evaModelTur20200001Leaf;
import hdy.eva.entity.evaModelTur20200001LeafExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface evaModelTur20200001LeafMapper {
    long countByExample(evaModelTur20200001LeafExample example);

    int deleteByExample(evaModelTur20200001LeafExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(evaModelTur20200001Leaf record);

    int insertSelective(evaModelTur20200001Leaf record);

    List<evaModelTur20200001Leaf> selectByExample(evaModelTur20200001LeafExample example);

    evaModelTur20200001Leaf selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") evaModelTur20200001Leaf record, @Param("example") evaModelTur20200001LeafExample example);

    int updateByExample(@Param("record") evaModelTur20200001Leaf record, @Param("example") evaModelTur20200001LeafExample example);

    int updateByPrimaryKeySelective(evaModelTur20200001Leaf record);

    int updateByPrimaryKey(evaModelTur20200001Leaf record);
}