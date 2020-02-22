package hdy.eva.mapper;

import hdy.eva.entity.evaModelGuide;
import hdy.eva.entity.evaModelGuideExample;
import hdy.eva.entity.evaModelGuideKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface evaModelGuideMapper {
    long countByExample(evaModelGuideExample example);

    int deleteByExample(evaModelGuideExample example);

    int deleteByPrimaryKey(evaModelGuideKey key);

    int insert(evaModelGuide record);

    int insertSelective(evaModelGuide record);

    List<evaModelGuide> selectByExample(evaModelGuideExample example);

    evaModelGuide selectByPrimaryKey(evaModelGuideKey key);

    int updateByExampleSelective(@Param("record") evaModelGuide record, @Param("example") evaModelGuideExample example);

    int updateByExample(@Param("record") evaModelGuide record, @Param("example") evaModelGuideExample example);

    int updateByPrimaryKeySelective(evaModelGuide record);

    int updateByPrimaryKey(evaModelGuide record);
}