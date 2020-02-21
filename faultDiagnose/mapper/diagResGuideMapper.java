package hdy.faultDiagnose.mapper;

import hdy.faultDiagnose.entity.diagResGuide;
import hdy.faultDiagnose.entity.diagResGuideExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface diagResGuideMapper {
    long countByExample(diagResGuideExample example);

    int deleteByExample(diagResGuideExample example);

    int deleteByPrimaryKey(Integer recordId);

    int insert(diagResGuide record);

    int insertSelective(diagResGuide record);

    List<diagResGuide> selectByExample(diagResGuideExample example);

    diagResGuide selectByPrimaryKey(Integer recordId);

    int updateByExampleSelective(@Param("record") diagResGuide record, @Param("example") diagResGuideExample example);

    int updateByExample(@Param("record") diagResGuide record, @Param("example") diagResGuideExample example);

    int updateByPrimaryKeySelective(diagResGuide record);

    int updateByPrimaryKey(diagResGuide record);
}