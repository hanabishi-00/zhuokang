package hdy.faultDiagnose.mapper;

import hdy.faultDiagnose.entity.diagModelGov;
import hdy.faultDiagnose.entity.diagModelGovExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface diagModelGovMapper {
    long countByExample(diagModelGovExample example);

    int deleteByExample(diagModelGovExample example);

    int deleteByPrimaryKey(Integer nodeId);

    int insert(diagModelGov record);

    int insertSelective(diagModelGov record);

    List<diagModelGov> selectByExample(diagModelGovExample example);

    diagModelGov selectByPrimaryKey(Integer nodeId);

    int updateByExampleSelective(@Param("record") diagModelGov record, @Param("example") diagModelGovExample example);

    int updateByExample(@Param("record") diagModelGov record, @Param("example") diagModelGovExample example);

    int updateByPrimaryKeySelective(diagModelGov record);

    int updateByPrimaryKey(diagModelGov record);
}