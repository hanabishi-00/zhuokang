package hdy.faultDiagnose.mapper;

import hdy.faultDiagnose.entity.diagSugg;
import hdy.faultDiagnose.entity.diagSuggExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface diagSuggMapper {
    long countByExample(diagSuggExample example);

    int deleteByExample(diagSuggExample example);

    int deleteByPrimaryKey(Integer suggId);

    int insert(diagSugg record);

    int insertSelective(diagSugg record);

    List<diagSugg> selectByExample(diagSuggExample example);

    diagSugg selectByPrimaryKey(Integer suggId);

    int updateByExampleSelective(@Param("record") diagSugg record, @Param("example") diagSuggExample example);

    int updateByExample(@Param("record") diagSugg record, @Param("example") diagSuggExample example);

    int updateByPrimaryKeySelective(diagSugg record);

    int updateByPrimaryKey(diagSugg record);
}