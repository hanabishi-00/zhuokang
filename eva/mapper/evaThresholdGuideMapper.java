package hdy.eva.mapper;

import hdy.eva.entity.evaThresholdGuide;
import hdy.eva.entity.evaThresholdGuideExample;
import hdy.eva.entity.evaThresholdGuideKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface evaThresholdGuideMapper {
    long countByExample(evaThresholdGuideExample example);

    int deleteByExample(evaThresholdGuideExample example);

    int deleteByPrimaryKey(evaThresholdGuideKey key);

    int insert(evaThresholdGuide record);

    int insertSelective(evaThresholdGuide record);

    List<evaThresholdGuide> selectByExample(evaThresholdGuideExample example);

    evaThresholdGuide selectByPrimaryKey(evaThresholdGuideKey key);

    int updateByExampleSelective(@Param("record") evaThresholdGuide record, @Param("example") evaThresholdGuideExample example);

    int updateByExample(@Param("record") evaThresholdGuide record, @Param("example") evaThresholdGuideExample example);

    int updateByPrimaryKeySelective(evaThresholdGuide record);

    int updateByPrimaryKey(evaThresholdGuide record);
}