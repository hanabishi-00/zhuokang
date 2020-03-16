package com.huake.device.service;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.huake.device.dao.generator.DiagTreeDynamicSqlSupport;
import com.huake.device.dao.generator.DiagTreeMapper;
import com.huake.device.dao.generator.TreeDeviceMapper;
import com.huake.device.domain.generator.DiagTree;
import com.huake.device.util.CharUtil;
import com.huake.device.util.MyException;
import com.huake.device.util.ResponseUtil;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;

@Service
public class DiagTreeService {
    @Resource
    private TreeDeviceMapper treeDeviceMapper;

    @Resource
    private DiagTreeMapper diagTreeMapper;

    private Map<String,Object> fillMapByDiagTree(Map<String,Object> mmp,DiagTree diagTree)
    {
        mmp.put("id",diagTree.getId());
        mmp.put("name",diagTree.getName());
        mmp.put("des",diagTree.getDes());
        mmp.put("pid",diagTree.getPid());
        mmp.put("nodetype",diagTree.getNodetype());
        mmp.put("gatetype",diagTree.getGatetype());
        mmp.put("isleaf",diagTree.getIsleaf());
        mmp.put("precent",diagTree.getPrecent());
        mmp.put("method",diagTree.getMethod());
        mmp.put("points",diagTree.getPoints());
        mmp.put("threshold",diagTree.getThreshold());
        return mmp;
    }

    public  Map<String,Object> getDiagTree(String id, List<DiagTree> list, Map<String,Object> mmp){

        if (list == null) {
            list = getDiagTreeList();
        }

        if (mmp == null)
        {
            mmp = new HashMap<>();
            mmp = fillMapByDiagTree(mmp,getDiagTreeRecord(id));
        }

        //Map<String,Object> MMap = new HashMap<>();
        List<Map<String,Object>> mapList =new ArrayList<Map<String,Object>>();
        boolean a = false;
        for (DiagTree diagTree: list)
        {
            if (diagTree.getPid().equals(id))
            {
                a = true;
                Map<String,Object> myMap = new HashMap<>();
                myMap = getDiagTree(diagTree.getId(),list,null);
                //mapList.add(myMap);
                //myMap.put("children","{}");
                //MMap.put(diagTree.getId(),myMap);bak
                mapList.add(myMap);
            }
        }

        if (a)
        {
            mmp.put("children",mapList);
        }else
        {
            mmp.put("children","[]");
        }

        return  mmp;
    }

    public static String getUUID()
    {
        return  UUID.randomUUID().toString().replaceAll("-","");
    }

    public  DiagTree getDiagTreeRecord(String id){
        Optional<DiagTree> optional = diagTreeMapper.selectByPrimaryKey(id);
        com.huake.device.domain.generator.DiagTree diagTree = optional.isPresent() ? optional.get() : null;
        return diagTree;
    }

    public  List<DiagTree> getDiagTreeList()
    {
        List<DiagTree> list = diagTreeMapper.select(SelectDSLCompleter.allRows());
        return list;
    }

    public  Object addDiagTree(String id,String name,String des ){

        DiagTree diagTree = new DiagTree();
        diagTree.setId(id);
        diagTree.setName(name);
        diagTree.setDes(des);
        diagTree.setPid("ROOT");
        diagTree.setNodetype("N");
        diagTree.setIsleaf("0");
        return diagTreeMapper.insertSelective(diagTree);
    }

    public Object modifyDiagTreeNode(DiagTree diagTree)
    {
        //两个非传入参数，强制设置为null
        diagTree.setNodetype(null);
        diagTree.setPid(null);
        return  diagTreeMapper.updateByPrimaryKeySelective(diagTree);
    }

    public Object addDiagTreeChildrenNode(DiagTree diagTree) //throws Exception
    {
        //1. ⾃动⽣成id（采⽤UUID）
        //2. 根据⽗节点判断节点类型（节点-
        //⻔-节点-...-⻔-⼦节点）
        //将入参中有的字段添加，其他字段为空

        //获取UUID
        diagTree.setId(CharUtil.getUUID());
        DiagTree myDiagTree = getDiagTreeRecord(diagTree.getPid());
        if (myDiagTree!=null) {
            diagTree.setNodetype(myDiagTree.getNodetype().equals("N" )? "G" : "N");
        }else
            //return  ResponseUtil.fail(-1,"查询不到父节点");
        throw new MyException(ResponseUtil.fail(-1,"查询不到父节点"));

        return diagTreeMapper.insertSelective(diagTree);
    }

    public Object addDiagTreeBrotherNode(DiagTree diagTree)
    {
        //1. 入参中的id为兄弟节点的id
        //2. ⾃动⽣成id（采⽤UUID）
        //3. ⽗节点采⽤兄弟节点的⽗节点
        //4. 根据兄弟节点判断节点类型（与兄
        //弟节点类型一致）
        //将入参中有的字段添加，其他字段为空

        DiagTree myDiagTree = getDiagTreeRecord(diagTree.getId());
        if (myDiagTree==null)
            //return  ResponseUtil.fail(-1,"查询不到兄弟节点，请检查id是否正确");
            throw new MyException(ResponseUtil.fail(-1,"查询不到兄弟节点，请检查id是否正确"));
        diagTree.setNodetype(myDiagTree.getNodetype());
        diagTree.setPid(myDiagTree.getPid());
        //获取UUID
        diagTree.setId(CharUtil.getUUID());

        return diagTreeMapper.insertSelective(diagTree);
    }

    public  Object deleteDiagTreeNode(String id)
    {
        DiagTree tpDiagTree = getDiagTreeRecord(id);
        if (tpDiagTree == null)
            throw new MyException(ResponseUtil.fail(-1,"没有找到这个节点，请确认输入id是否有效！"));
        List<DiagTree> list = getDiagTreeList();
        for (DiagTree diagTree:list)
        {
            if (diagTree.getPid().equals(id))
            {
                throw new MyException(ResponseUtil.fail(-1,"该节点还有⼦节点不允许删除"));
            }
        }
        return diagTreeMapper.deleteByPrimaryKey(id);
    }

    public  List<DiagTree> getDiagTreeRootNodes()
    {
        SelectDSLCompleter completer = selectModelQueryExpressionDSL -> {
            selectModelQueryExpressionDSL.where()
                    .and(DiagTreeDynamicSqlSupport.pid, isEqualToWhenPresent("ROOT"));
            return selectModelQueryExpressionDSL;
        };
        List<DiagTree> list = diagTreeMapper.select(completer);
        return list;
    }


}
