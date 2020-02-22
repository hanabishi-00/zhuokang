package hdy.eva.mapper;

import hdy.eva.entity.evaThresholdBool20200002;
import hdy.eva.entity.evaThresholdBool20200002Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface evaThresholdBool20200002Mapper {
    long countByExample(evaThresholdBool20200002Example example);

    int deleteByExample(evaThresholdBool20200002Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(evaThresholdBool20200002 record);

    int insertSelective(evaThresholdBool20200002 record);

    List<evaThresholdBool20200002> selectByExample(evaThresholdBool20200002Example example);

    evaThresholdBool20200002 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") evaThresholdBool20200002 record, @Param("example") evaThresholdBool20200002Example example);

    int updateByExample(@Param("record") evaThresholdBool20200002 record, @Param("example") evaThresholdBool20200002Example example);

    int updateByPrimaryKeySelective(evaThresholdBool20200002 record);

    int updateByPrimaryKey(evaThresholdBool20200002 record);
}