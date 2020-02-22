package hdy.eva.mapper;

import hdy.eva.entity.evaResTur20200001LeafOnline;
import hdy.eva.entity.evaResTur20200001LeafOnlineExample;
import hdy.eva.entity.evaResTur20200001LeafOnlineKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface evaResTur20200001LeafOnlineMapper {
    long countByExample(evaResTur20200001LeafOnlineExample example);

    int deleteByExample(evaResTur20200001LeafOnlineExample example);

    int deleteByPrimaryKey(evaResTur20200001LeafOnlineKey key);

    int insert(evaResTur20200001LeafOnline record);

    int insertSelective(evaResTur20200001LeafOnline record);

    List<evaResTur20200001LeafOnline> selectByExample(evaResTur20200001LeafOnlineExample example);

    evaResTur20200001LeafOnline selectByPrimaryKey(evaResTur20200001LeafOnlineKey key);

    int updateByExampleSelective(@Param("record") evaResTur20200001LeafOnline record, @Param("example") evaResTur20200001LeafOnlineExample example);

    int updateByExample(@Param("record") evaResTur20200001LeafOnline record, @Param("example") evaResTur20200001LeafOnlineExample example);

    int updateByPrimaryKeySelective(evaResTur20200001LeafOnline record);

    int updateByPrimaryKey(evaResTur20200001LeafOnline record);
}