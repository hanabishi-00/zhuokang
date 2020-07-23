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
     * 找出指定id的子节点
     *
     * @param id 节点id
     * @return 结点的子结点
     */
    public Node searchChildren(String id) {

        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            Node newNode = nodes.get(i);
            if (newNode.Id.equals(id))
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


    public static double getfreValue(long time, int unitID, ArrayList<ArrayList<String>> mon, ArrayList<ArrayList<String>> thr, String judg) throws ClassNotFoundException, SQLException, ParseException {
        double p1 = 0.0;
        switch (judg){   //这里的abcde分别对应几种判断方法，可根据实际情况做相应更改
            case "Boolean":  //BooleanTree
                ArrayList<String> thr1= new ArrayList<>();
                if(thr.size()==1){
                    thr1=thr.get(0);
                }else{
                    thr1=thr.get(unitID-1);
                }
                BoolTree bool1 = new BoolTree();
                p1 = bool1.BooleanTree(mon.get(0).get(unitID-1),time,thr1.get(0));
                return p1;
            case "Booltime":
                BoolTree bool2 = new BoolTree();
                ArrayList<String> measureID1 = new ArrayList<String>();
                for (ArrayList<String> d:mon) {
                    measureID1.add(d.get(unitID-1));
                }
                p1 = bool2.BooltimeTree(measureID1,time);
                return p1;
            case "Floatean":  //FloateanTree
                FloatTree float2 = new FloatTree();
                ArrayList<String> thr2= new ArrayList<>();
                if(thr.size()==1){
                    thr2=thr.get(0);
                }else{
                    thr2=thr.get(unitID-1);
                }
                if (thr2.size()==1){
                    thr2.add("null");
                }
                p1 = float2.FloateanTree(mon.get(0).get(unitID-1), time,thr2.get(0),thr2.get(1));
                return p1;
            case "Trend":  //TrendTree
                FloatTree float3 = new FloatTree();
                ArrayList<String> thr3= new ArrayList<>();
                if(thr.size()==1){
                    thr3=thr.get(0);
                }else{
                    thr3=thr.get(unitID-1);
                }
                if (thr3.size()==1){
                    thr3.add("null");
                }
                p1 = float3.TrendTree(mon.get(0).get(unitID-1), time,thr3.get(0),thr3.get(1));
                return p1;
            case "SpeedTrend":  //SpeedTrendTree
                FloatTree float4 = new FloatTree();
                ArrayList<String> thr4= new ArrayList<>();
                if(thr.size()==1){
                    thr4=thr.get(0);
                }else{
                    thr4=thr.get(unitID-1);
                }
                if (thr4.size()==1){
                    thr4.add("null");
                }
                p1 = float4.SpeedTrendTree(mon.get(0).get(unitID-1), time,thr4.get(0),thr4.get(1));
                return p1;
            case "Special1":  //SumFloateanTree
                FloatTree float5 = new FloatTree();
                ArrayList<String> thr5= new ArrayList<>();
                if(thr.size()==1){
                    thr5=thr.get(0);
                }else{
                    thr5=thr.get(unitID-1);
                }
                if (thr5.size()==1){
                    thr5.add("null");
                }
                ArrayList<String> measureID = new ArrayList<String>();
                for (ArrayList<String> d:mon) {
                    measureID.add(d.get(unitID-1));
                }
                p1 = float5.SumFloateanTree(measureID, time,thr5.get(0),thr5.get(1));
                return p1;
            case "Special2":
                SpecialTree sp1 = new SpecialTree();
                ArrayList<String> thr6= new ArrayList<>();
                if(thr.size()==1){
                    thr6=thr.get(0);
                }else{
                    thr6=thr.get(unitID-1);
                }
                if (thr6.size()==1){
                    thr6.add("null");
                }
                ArrayList<String> measureID2 = new ArrayList<String>();
                for (ArrayList<String> d:mon) {
                    measureID2.add(d.get(unitID-1));
                }
                p1=sp1.SpecialTree2(measureID2,time,thr6.get(1));
                return p1;
            case "Special3":
                ArrayList<String> thr7= new ArrayList<>();
                if(thr.size()==1){
                    thr7=thr.get(0);
                }else{
                    thr7=thr.get(unitID-1);
                }
                SpecialTree sp2 = new SpecialTree();
                if (thr7.size()==1){
                    thr7.add("null");
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

//                        System.out.println(data1);
//            Node temp = new Node();
            Node temp = new Node();
            temp.name = data1.get(1);
            temp.pid = data1.get(2);
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
                    temp.threshold.add(qwe);
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
                    for (String d:data1.get(7).split(";")) {
                        ArrayList<String> newarray = new ArrayList<>();
                        newarray.addAll(Arrays.asList(d.split(",")));
                        temp.threshold.add((ArrayList<String>)newarray.clone());
//                                newarray.clear();
                    }
//                    temp.threshold.addAll(Arrays.asList(data1.get(7).split(",")));
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
        asd.InitialNodes(Inodes,d1,1,1);
        for (Node d2:Inodes){
            if (d2.getId().equals("4311")){
//                System.out.println(d2.getThreshold().get(0).equals("null"));
            }
//            System.out.println(d2.getId()+" "+d2.getName()+" " +d2.getFather()+" " +d2.getChildren()+" " +d2.getMonitorid()+" " +d2.getThreshold()+" " +d2.getJudgment());
        }

    }

}
