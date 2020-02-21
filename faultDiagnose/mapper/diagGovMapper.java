package hdy.faultDiagnose.mapper;

import hdy.faultDiagnose.entity.diagGov;
import hdy.faultDiagnose.entity.diagGovExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface diagGovMapper {
    long countByExample(diagGovExample example);

    int deleteByExample(diagGovExample example);

    int deleteByPrimaryKey(Integer nodeId);

    int insert(diagGov record);

    int insertSelective(diagGov record);

    List<diagGov> selectByExample(diagGovExample example);

    diagGov selectByPrimaryKey(Integer nodeId);

    int updateByExampleSelective(@Param("record") diagGov record, @Param("example") diagGovExample example);

    int updateByExample(@Param("record") diagGov record, @Param("example") diagGovExample example);

    int updateByPrimaryKeySelective(diagGov record);

    int updateByPrimaryKey(diagGov record);
}