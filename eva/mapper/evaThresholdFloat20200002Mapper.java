package hdy.eva.mapper;

import hdy.eva.entity.evaThresholdFloat20200002;
import hdy.eva.entity.evaThresholdFloat20200002Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface evaThresholdFloat20200002Mapper {
    long countByExample(evaThresholdFloat20200002Example example);

    int deleteByExample(evaThresholdFloat20200002Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(evaThresholdFloat20200002 record);

    int insertSelective(evaThresholdFloat20200002 record);

    List<evaThresholdFloat20200002> selectByExample(evaThresholdFloat20200002Example example);

    evaThresholdFloat20200002 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") evaThresholdFloat20200002 record, @Param("example") evaThresholdFloat20200002Example example);

    int updateByExample(@Param("record") evaThresholdFloat20200002 record, @Param("example") evaThresholdFloat20200002Example example);

    int updateByPrimaryKeySelective(evaThresholdFloat20200002 record);

    int updateByPrimaryKey(evaThresholdFloat20200002 record);
}