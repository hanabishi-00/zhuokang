package main.model;

import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import main.service.BoolTree;
import main.service.FloatTree;


/**
 * 构造树的逻辑实现
 */
public class MakeFaultTree {
    private DefaultTreeModel faultTree;
    private DefaultMutableTreeNode root;
    private ArrayList<Node> nodes;
    private Long time;
    private ArrayList<Node> equations;

    /**
     * 处理后的等式集
     */
    public MakeFaultTree(ArrayList<Node> equations) {
        nodes = equations;
    }
    public MakeFaultTree(Long time,ArrayList<Node> equations) {
        super();
        this.time = time;
        this.equations = equations;
    }
    /**
     * 递归实现树的构建
     * treeNode树根节点
     */
    public void makeTree(DefaultMutableTreeNode treeNode) {
        String name = (String) treeNode.getUserObject();
        Node node = searchChildren(name);
        if (node != null) {
            for (int i = 0; i < node.children.size(); i++) {
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(node.children.get(i));
                ;
                treeNode.add(newNode);
                makeTree(newNode);
            }
        } else {
            node = new Node();
            node.name = name;
            node.gate = "*";      //gate是逻辑门
            nodes.add(node);
        }
    }
    /**
     * 找出指定名称的子节点
     *
     * @param name 节点名称
     * @return 结点的子结点
     */
    public Node searchChildren(String name) {

        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            Node newNode = nodes.get(i);
            if (newNode.name.equals(name))
                return newNode;
        }
        return null;
    }
    /**
     * 故障树获取
     *
     * @return 故障树
     */
    public DefaultTreeModel getFaultTree() {
        Node node = nodes.get(0);
        root = new DefaultMutableTreeNode(node.name);
        makeTree(root);
        faultTree = new DefaultTreeModel(root);
        return faultTree;
    }
    /**
     * 清空节点
     */
    public void clear() {
        nodes.clear();
    }

    public void setNodes(ArrayList<Node> equations) {
        nodes = equations;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }


    public static double getfreValue(long time, int unitID, ArrayList<ArrayList<String>> mon, ArrayList<String> thr, String judg) throws ClassNotFoundException, SQLException, ParseException {
        double p1 = 0.0;
        switch (judg){   //这里的abcdef分别对应几种判断方法，可根据实际情况做相应更改
            case "BooleanTree":  //BooleanTree
                BoolTree bool1 = new BoolTree();
                p1 = bool1.BooleanTree(mon.get(0).get(unitID-1),time,thr.get(0));
                return p1;
            case "BooltimeTree":
                BoolTree bool2 = new BoolTree();
                ArrayList<String> measureID = new ArrayList<String>();
                for (ArrayList<String> d:mon) {
                    measureID.add(d.get(unitID-1));
                }
                p1 = bool2.BooltimeTree(measureID,time);
                return p1;
            case "FloateanTree":  //FloateanTree
                FloatTree float2 = new FloatTree();
                if (thr.size()==1){
                    thr.add("null");
                }
                p1 = float2.FloateanTree(mon.get(0).get(unitID-1), time,thr.get(0),thr.get(1));
                return p1;
            case "TrendTree":  //TrendTree
                FloatTree float3 = new FloatTree();
                if (thr.size()==1){
                    thr.add("null");
                }
                p1 = float3.TrendTree(mon.get(0).get(unitID-1), time,thr.get(0),thr.get(1));
                return p1;
            case "SpeedTrendTree":  //SpeedTrendTree
                FloatTree float4 = new FloatTree();
                if (thr.size()==1){
                    thr.add("null");
                }
                p1 = float4.SpeedTrendTree(mon.get(0).get(unitID-1), time,thr.get(0),thr.get(1));
                return p1;
            case "SumFloateanTree":  //SumFloateanTree
                FloatTree float5 = new FloatTree();
                if (thr.size()==1){
                    thr.add("null");
                }
                ArrayList<String> measureID1 = new ArrayList<String>();
                for (ArrayList<String> d:mon) {
                    measureID1.add(d.get(unitID-1));
                }
                p1 = float5.SumFloateanTree(measureID1, time,thr.get(0),thr.get(1));
                return p1;
            default:return p1;
        }
    }

    /**
     * 节点信息初始化
     *
     * @param Inodes
     *            节点信息列表
     */
    @SuppressWarnings("rawtypes")
    public static void  InitialNodes(ArrayList<Node> Inodes, long time, int Uid, int kind) throws ClassNotFoundException,SQLException{
        ArrayList<String> xmldir = new ArrayList<>();
        xmldir.add( "src/main/config/FT_test1.xml");
        xmldir.add( "src/main/config/FT_test2.xml");
        String result = xmldir.get(kind-1);
        File file = new File(result);
        if (file.exists()){
            try{
                SAXReader reader = new SAXReader();// 创建SAXReader
                Document document =reader.read(file);//从xml文件获取数据
                Element rootElement = document.getRootElement();// 获取根节点 (WorkNode)

                /**
                 * 得到第一层节点
                 */
                List list = rootElement.elements("WorkNode");//得到所有(WorkNode)元素
                Iterator iter = list.iterator();//遍历元素
                while (iter.hasNext()) {
                    Element ele = (Element) iter.next();
                    Node temp = new Node();
                    temp.name = ele.attribute("Name").getValue();
                    temp.Id = ele.attribute("ID").getValue();
                    String[] childs = ele.attribute("children").getValue().split(",");
                    for (int i =0; i<childs.length; i++) {
                        if (!childs[i].equals("null")) {
                            temp.children.add(childs[i]);
                            if (i==childs.length-1){
                                temp.freq = 0.0;
                            }
                        }else if (ele.attribute("monitorId").getValue()==null || ele.attribute("monitorId").getValue().equals("null")){
                            ArrayList<String> qwe = new ArrayList<>();
                            qwe.add("null");
                            temp.monitorid.add(qwe);
                            temp.judgment="null";
                            temp.threshold.add("null");
                            temp.freq = 0.0;
                            }
                        else{
                            for (String d:ele.attribute("monitorId").getValue().split(";")) {
                                ArrayList<String> newarray = new ArrayList<>();
                                newarray.addAll(Arrays.asList(d.split(",")));
                                temp.monitorid.add((ArrayList<String>)newarray.clone());
                            }
                            temp.threshold.addAll(Arrays.asList(ele.attribute("threshold").getValue().split(",")));
                            temp.judgment = ele.attribute("judgment").getValue();
                            temp.freq =  getfreValue(time,Uid,temp.getMonitorid(),temp.getThreshold(),temp.getJudgment());
                        }
                    }
                    temp.gate = ele.attribute("gate").getValue();

                    if (!ele.attribute("fatherName").getValue().equals("null")) {
                        temp.father = ele.attribute("fatherName").getValue();
                    }
                    Inodes.add(temp);

                }

            }catch (DocumentException | ParseException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < Inodes.size();i++) {
            if (Inodes.get(i).father == null) {
                Node tempn = Inodes.get(0);
                Inodes.set(0, Inodes.get(i));
                Inodes.set(i, tempn);
            }
        }
//        System.out.println(Inodes.size());
    }

    public Long getTime() {
        return time;
    }
    public void setTime(Long time) {
        this.time = time;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<Node> Inodes = new ArrayList<>();
        long d1= 11111111;
        MakeFaultTree.InitialNodes(Inodes,d1,1,2);
        for (Node d2:Inodes){
            if (d2.getId().equals("4311")){
                System.out.println(d2.getThreshold().get(0).equals("null"));
            }
            System.out.println(d2.getId()+" "+d2.getName()+" " +d2.getFather()+" " +d2.getChildren()+" " +d2.getMonitorid()+" " +d2.getThreshold()+" " +d2.getJudgment());
        }

    }

}
