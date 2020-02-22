package hdy.eva.mapper;

import hdy.eva.entity.evaThresholdBool20200001;
import hdy.eva.entity.evaThresholdBool20200001Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface evaThresholdBool20200001Mapper {
    long countByExample(evaThresholdBool20200001Example example);

    int deleteByExample(evaThresholdBool20200001Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(evaThresholdBool20200001 record);

    int insertSelective(evaThresholdBool20200001 record);

    List<evaThresholdBool20200001> selectByExample(evaThresholdBool20200001Example example);

    evaThresholdBool20200001 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") evaThresholdBool20200001 record, @Param("example") evaThresholdBool20200001Example example);

    int updateByExample(@Param("record") evaThresholdBool20200001 record, @Param("example") evaThresholdBool20200001Example example);

    int updateByPrimaryKeySelective(evaThresholdBool20200001 record);

    int updateByPrimaryKey(evaThresholdBool20200001 record);
}