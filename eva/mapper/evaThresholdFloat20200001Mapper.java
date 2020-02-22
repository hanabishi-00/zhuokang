package hdy.eva.mapper;

import hdy.eva.entity.evaThresholdFloat20200001;
import hdy.eva.entity.evaThresholdFloat20200001Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface evaThresholdFloat20200001Mapper {
    long countByExample(evaThresholdFloat20200001Example example);

    int deleteByExample(evaThresholdFloat20200001Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(evaThresholdFloat20200001 record);

    int insertSelective(evaThresholdFloat20200001 record);

    List<evaThresholdFloat20200001> selectByExample(evaThresholdFloat20200001Example example);

    evaThresholdFloat20200001 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") evaThresholdFloat20200001 record, @Param("example") evaThresholdFloat20200001Example example);

    int updateByExample(@Param("record") evaThresholdFloat20200001 record, @Param("example") evaThresholdFloat20200001Example example);

    int updateByPrimaryKeySelective(evaThresholdFloat20200001 record);

    int updateByPrimaryKey(evaThresholdFloat20200001 record);
}