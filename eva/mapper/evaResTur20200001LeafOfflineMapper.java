package hdy.eva.mapper;

import hdy.eva.entity.evaResTur20200001LeafOffline;
import hdy.eva.entity.evaResTur20200001LeafOfflineExample;
import hdy.eva.entity.evaResTur20200001LeafOfflineKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface evaResTur20200001LeafOfflineMapper {
    long countByExample(evaResTur20200001LeafOfflineExample example);

    int deleteByExample(evaResTur20200001LeafOfflineExample example);

    int deleteByPrimaryKey(evaResTur20200001LeafOfflineKey key);

    int insert(evaResTur20200001LeafOffline record);

    int insertSelective(evaResTur20200001LeafOffline record);

    List<evaResTur20200001LeafOffline> selectByExample(evaResTur20200001LeafOfflineExample example);

    evaResTur20200001LeafOffline selectByPrimaryKey(evaResTur20200001LeafOfflineKey key);

    int updateByExampleSelective(@Param("record") evaResTur20200001LeafOffline record, @Param("example") evaResTur20200001LeafOfflineExample example);

    int updateByExample(@Param("record") evaResTur20200001LeafOffline record, @Param("example") evaResTur20200001LeafOfflineExample example);

    int updateByPrimaryKeySelective(evaResTur20200001LeafOffline record);

    int updateByPrimaryKey(evaResTur20200001LeafOffline record);
}