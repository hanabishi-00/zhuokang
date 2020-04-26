package main.model;

import main.config.db2xml;
import main.service.BoolTree;
import main.service.FloatTree;
import main.service.SpecialTree;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;


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
        switch (judg){   //这里的abcde分别对应几种判断方法，可根据实际情况做相应更改
            case "BooleanTree":  //BooleanTree
                BoolTree bool1 = new BoolTree();
                p1 = bool1.BooleanTree(mon.get(0).get(unitID-1),time,thr.get(0));
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
            case "SpecialTree1":  //SumFloateanTree
                FloatTree float5 = new FloatTree();
                if (thr.size()==1){
                    thr.add("null");
                }
                ArrayList<String> measureID = new ArrayList<String>();
                for (ArrayList<String> d:mon) {
                    measureID.add(d.get(unitID-1));
                }
                p1 = float5.SumFloateanTree(measureID, time,thr.get(0),thr.get(1));
                return p1;
            case "SpecialTree2":
                SpecialTree sp1 = new SpecialTree();
                if (thr.size()==1){
                    thr.add("null");
                }
                ArrayList<String> measureID2 = new ArrayList<String>();
                for (ArrayList<String> d:mon) {
                    measureID2.add(d.get(unitID-1));
                }
                p1=sp1.SpecialTree2(measureID2,time);
                return p1;
            case "SpecialTree3":
                SpecialTree sp2 = new SpecialTree();
                if (thr.size()==1){
                    thr.add("null");
                }
                p1=sp2.SpecialTree3(mon.get(0).get(unitID-1),time);
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
        //构建故障树
//        String result1 = GovXMLUtils.GetXMLPath("FT_test2");
////        System.out.println("文件读取成功！");
//        System.out.println(result1);

        db2xml que = new db2xml();
        ArrayList<ArrayList<String>> data=que.saveFile(kind);
//        System.out.println(data);
        for(ArrayList<String> data1:data) {

            //            System.out.println(data1);
//            Node temp = new Node();
            Node temp = new Node();
            temp.name = data1.get(1);

            temp.Id = data1.get(0);
            String[] childs = data1.get(8).split(",");
            for (int i =0; i<childs.length; i++) {
                if (!childs[i].equals("null")) {
                    temp.children.add(childs[i]);
                    if (i==childs.length-1){
                        temp.freq = 0.0;
                    }
                }else if (data1.get(5)==null || data1.get(5).equals("null")){
                    ArrayList<String> qwe = new ArrayList<>();
                    qwe.add("null");
                    temp.monitorid.add(qwe);
                    temp.judgment="null";
                    temp.threshold.add("null");
                    temp.freq = 0.0;
                }
                else{
//                            System.out.println(ele.attribute("monitorId").getValue());
                    for (String d:data1.get(5).split(";")) {
                        ArrayList<String> newarray = new ArrayList<>();
                        newarray.addAll(Arrays.asList(d.split(",")));
                        temp.monitorid.add((ArrayList<String>)newarray.clone());
//                                newarray.clear();
                    }
                    temp.threshold.addAll(Arrays.asList(data1.get(7).split(",")));
                    temp.judgment = data1.get(6);
//                            System.out.println(temp.name);
//                            System.out.println(temp.judgment);
//                            System.out.println(temp.threshold);
//                            System.out.println(temp.monitorid);
                    try {
                        temp.freq =  getfreValue(time,Uid,temp.getMonitorid(),temp.getThreshold(),temp.getJudgment());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
//                            System.out.println(temp.freq);
                }
            }
            temp.gate = data1.get(4);
//                    Long time = TimeUtils.stringToLong(date,"yyyy-MM-dd HH:mm:ss")/1000;
//                    此处为temp.freq赋值，需利用相关函数，暂且空缺

            if (!data1.get(3).equals("null")) {
                temp.father = data1.get(3);
            }
//                    temp.gate = ele.attribute("gate").getValue();
            Inodes.add(temp);
        }

        for (int i = 0; i < Inodes.size();i++) {
            if (Inodes.get(i).father == null) {
                Node tempn = Inodes.get(0);
                Inodes.set(0, Inodes.get(i));
                Inodes.set(i, tempn);
            }
        }
    }



    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<Node> Inodes = new ArrayList<>();
        long d1= 11111111;
        MakeFaultTree asd = new MakeFaultTree();
        asd.InitialNodes(Inodes,d1,1,2);
        for (Node d2:Inodes){
            if (d2.getId().equals("4311")){
//                System.out.println(d2.getThreshold().get(0).equals("null"));
            }
//            System.out.println(d2.getId()+" "+d2.getName()+" " +d2.getFather()+" " +d2.getChildren()+" " +d2.getMonitorid()+" " +d2.getThreshold()+" " +d2.getJudgment());
        }

    }

}
