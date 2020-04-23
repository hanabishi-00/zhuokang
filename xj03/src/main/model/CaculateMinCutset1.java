package main.model;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class CaculateMinCutset1 {
    private DefaultTreeModel faultTree;
    private DefaultMutableTreeNode root;
    private ArrayList<Node> nodes;
    private long time;
    private int unitNo;
    private String system;

    /**
     * “与”运算，将array1的每行与array2的每行相加，大于1或小于-1则置为1与-1。
     * @param array1 二维矩阵
     * @param array2 二维矩阵
     * @return  二维矩阵
     */
    public static ArrayList<ArrayList<Integer>> matAdd(ArrayList<ArrayList<Integer>> array1,
                                                ArrayList<ArrayList<Integer>> array2){
        ArrayList<ArrayList<Integer>> newarray = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> midarray = new ArrayList<Integer>();
        for (int i=0;i<array1.size();i++){
            for (int j=0;j<array2.size();j++){
                for (int k=0; k<array1.get(i).size();k++){
                    int sum = array1.get(i).get(k)+array2.get(j).get(k);
                    if (sum>1){
                        sum = 1;
                    }else if(sum<-1){
                        sum =-1;
                    }
                    midarray.add(sum);
                }
                newarray.add((ArrayList<Integer>)midarray.clone());
                midarray.clear();
            }
        }
        return newarray;
    }

    /**
     * 将array1与array1竖向合并
     * @param array1 二维矩阵
     * @param array2 二维矩阵
     * @return 二维矩阵
     */
    public static ArrayList<ArrayList<Integer>> matOr(ArrayList<ArrayList<Integer>> array1,
                                                      ArrayList<ArrayList<Integer>> array2){
        ArrayList<ArrayList<Integer>> newarray = (ArrayList<ArrayList<Integer>>)array1.clone();
        newarray.addAll(array2);
        return newarray;
    }

    /**
     * 基本功能大体同matAdd一样，此外，若相加结果某行中有0，且对应的array1与array2是-1与1，则删除该行
     * @param array1 二维数组
     * @param array2 二维数组
     * @return 二维数组
     */
    public static ArrayList<ArrayList<Integer>> matAdd1(ArrayList<ArrayList<Integer>> array1,
                                                       ArrayList<ArrayList<Integer>> array2){
        ArrayList<ArrayList<Integer>> newarray = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> midarray = new ArrayList<Integer>();
        for (int i=0;i<array1.size();i++){
            for (int j=0;j<array2.size();j++){
                for (int k=0; k<array1.get(i).size();k++){
                    int sum = array1.get(i).get(k)+array2.get(j).get(k);
                    if (sum>1){
                        sum = 1;
                    }
                    if (sum<-1){
                        sum = -1;
                    }
                    if (sum==0 && array1.get(i).get(k)!=0){
                        midarray.clear();
                        break;
                    }
                    midarray.add(sum);
                }
                if (midarray.size() == 0){continue;}
                newarray.add((ArrayList<Integer>)midarray.clone());
                midarray.clear();
            }
        }
        return newarray;
    }


    /**
     * 定义X运算，两个一维数组运算，如果两矩阵中相同位置上的元素都为 1，则将矩阵此位置元素更改为 0
     * @param array1 一维数组
     * @param array2 一维数组
     * @return 一维数组
     */
    public static ArrayList<Integer> clacX(ArrayList<Integer> array1,ArrayList<Integer> array2){
        ArrayList<Integer> newarray = new ArrayList<Integer>();
        for (int i=0;i<array1.size();i++){
            newarray.add(Math.max(array1.get(i) - array2.get(i), 0));
        }
        return newarray;
    }



    /**
     *     Y运算，首先计算矩阵D中数值为1的元素个数N，将矩阵D竖向罗列N次生成矩阵E，
     *      保留矩阵E中第i(1≤i≤N)行前i个元素1，将其他所有元素赋值为0。
     *      最后，将矩阵中每行最后 一个数值为 1 的元素赋值为－1。
     * @param array1 一维矩阵
     * @return 二维矩阵
     */
    public static ArrayList<ArrayList<Integer>> clacY(ArrayList<Integer> array1){
        ArrayList<Integer> clonearray = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> newarray = new ArrayList<ArrayList<Integer>>();
        int sum1 = 0;
        for (int i:array1){
            if (i>0){
                sum1++;
            }
        }
        for (int i=0;i<sum1;i++){
            clonearray = (ArrayList<Integer>)array1.clone();
            int sum = 0;
            for (int j=0;j<clonearray.size();j++){
                if(clonearray.get(j)==1){
                    sum++;
                }
                if(sum==i+1 && clonearray.get(j)==1){
                    clonearray.set(j,-1);
                }else if(sum>i+1){
                    clonearray.set(j,0);
                }
            }
            newarray.add(clonearray);
        }
        return newarray;
    }


    /**
     * 计算矩阵中非零个数
     * @param array1 一维矩阵
     * @return 整型
     */
    public static int onenum(ArrayList<Integer> array1){
        int sum =0;
        for (int i:array1){
            if (i!=0){
                sum++;
            }
        }
        return sum;
    }


    /**
     * 按照割集中底事件个数排序
     * @param array1 二维矩阵
     * @return 二维矩阵
     */
    public static ArrayList<ArrayList<Integer>> matsort(ArrayList<ArrayList<Integer>> array1){
        ArrayList<ArrayList<Integer>> newarray = new ArrayList<ArrayList<Integer>>();
        newarray.add(array1.get(0));
        for (int i=1;i<array1.size();i++){
            int j;
            for (j=0;j<i;j++){
                if (onenum(array1.get(i))<onenum(newarray.get(j))){
                    newarray.add(j,array1.get(i));
                    break;
                }
            }
            if (j==i){
                newarray.add(array1.get(i));
            }
        }
        return newarray;
    }


    /**
     * 割集吸收简化     吸收简化程序中包括排序
     * @param array1  二维矩阵
     * @return 二维矩阵
     */
    public static ArrayList<ArrayList<Integer>> matAbsorb(ArrayList<ArrayList<Integer>> array1){
        ArrayList<ArrayList<Integer>> newarray = matsort(array1);
        ArrayList<ArrayList<Integer>> newarray1 = new ArrayList<>();
        ArrayList<Integer> delid = new ArrayList<>();
        int flag;
        for (int i=0;i<newarray.size()-1;i++){
            for (int j=i+1;j<newarray.size();j++){
                flag=1;
                for (int k=0;k<newarray.get(i).size();k++){
                    if (newarray.get(j).get(k)-newarray.get(i).get(k)<0){
                        flag=0;
                        break;
                    }
                }
                if (flag==1){
                    delid.add(0,j);
                }
            }
        }
        for (int i=0;i<newarray.size();i++) {
            if(!delid.contains(i)) {
                newarray1.add(newarray.get(i));
            }

        }
        return newarray1;
    }


    /**
     * 寻找名为name的节点
     * @param name 待寻找节点的name，String型
     * @param N1 被寻找的节点数组，一维矩阵
     * @return 节点
     */
    public static Node searchNode(String name, ArrayList<Node> N1) {

        int size = N1.size();
        for (int i = 0; i < size; i++) {
            Node newNode = N1.get(i);
            if (newNode.name.equals(name))
                return newNode;
        }
        return null;
    }



    /**
     *利用递归算法将故障树转化为矩阵并求出最小割集
     * @param node1 节点
     * @param faultNode 存放所有节点的数组
     * @param bot2mat0 底事件与01矩阵的对应关系，如第一个底事件对应[1 0 0 …… 0]
     * @return 最小割集 二维矩阵
     */
    public static ArrayList<ArrayList<Integer>> calcCut(Node node1,ArrayList<Node> faultNode, Map<Node,ArrayList<Integer>> bot2mat0){
        ArrayList<ArrayList<Integer>> newarray = new ArrayList<>();
        if(node1.children.size()!=0){           //不是叶节点（不是底事件）
//            System.out.println(node1.getName());
//            System.out.println(node1.children!=null);
            newarray = calcCut(searchNode(node1.getChildren().get(0),faultNode),faultNode,bot2mat0);
            if (node1.children.size()>1){
                for (int i=1;i<node1.children.size();i++){
                    if (node1.gate.equals("+")){
                        newarray = matOr(newarray,calcCut(searchNode(node1.getChildren().get(i),faultNode),faultNode,bot2mat0));
                    }else if(node1.gate.equals("0")){
                        newarray = matAdd1(newarray,calcCut(searchNode(node1.getChildren().get(i),faultNode),faultNode,bot2mat0));
                    }
                }
            }
        }else {
            newarray.add(bot2mat0.get(node1));
            return newarray;
        }
        return newarray;
    }


    /**
     * 最小割集不交化
     * @param array1 最小割集 二维矩阵
     * @return 最小割集不交化矩阵，二维矩阵
     */
    public static ArrayList<ArrayList<Integer>> noncross(ArrayList<ArrayList<Integer>> array1){
        ArrayList<ArrayList<Integer>> newarray = new ArrayList<>();
        ArrayList<ArrayList<Integer>> sortarray = matsort(array1);
        ArrayList<ArrayList<ArrayList<Integer>>> Harray = new ArrayList<>();
        ArrayList<Integer> Karray = new ArrayList<>();  //一维
        ArrayList<ArrayList<Integer>> Karray1 = new ArrayList<>();          //二维
        ArrayList<ArrayList<Integer>> Garray = new ArrayList<>();
        newarray.add((ArrayList<Integer>)sortarray.get(0).clone());
//        System.out.println(sortarray.size());
//        System.out.println(sortarray);
        for(int i=1;i<sortarray.size();i++){
//            System.out.println(i);
            Karray = (ArrayList<Integer>) sortarray.get(i).clone();
//            System.out.println("Karray:"+Karray);
            for (int j=0;j<i;j++){
//                System.out.println(sortarray.get(j));
//                System.out.println(clacX((ArrayList<Integer>) sortarray.get(j).clone(),Karray));
                Garray.add(clacX((ArrayList<Integer>) sortarray.get(j).clone(),Karray));
            }
//            System.out.println("Garray:"+Garray);
            Garray = matAbsorb(Garray);
//            System.out.println("Garray:"+Garray);
            for (ArrayList<Integer> integers : Garray) {
//                System.out.println("integers:"+integers);
//                System.out.println("clacy:"+clacY(integers));
                Harray.add(clacY(integers));
            }
//            System.out.println("Harray:"+Harray);
            for (int k=0;k<Harray.size()-1;k++){
                Harray.set(k+1,matAdd1(Harray.get(k),Harray.get(k+1)));
            }
            Karray1.add(Karray);
//            System.out.println("Karray1:"+Karray1);
            newarray.addAll(matAdd((ArrayList<ArrayList<Integer>>) Karray1.clone(),(ArrayList<ArrayList<Integer>>)Harray.get(Harray.size()-1).clone()));
            Karray1.clear();
            Harray.clear();
            Garray.clear();
        }
        return newarray;
    }

    
    /**
     * 根据最小割集计算顶事件概率
     * @param faultNode 所有节点矩阵
     * @return 顶事件发生概率
     */
    public static Double calcTopPre(ArrayList<Node> faultNode){
        ArrayList<ArrayList<Integer>> newarray = new ArrayList<>();
        ArrayList<Node> botarray = new ArrayList<>();   //用于存放故障树底事件节点
        Node topnode = new Node();                      //顶事件（根节点）
        for (Node node : faultNode) {                   //寻找底事件
            if (node.children.size() == 0) {
                botarray.add(node);
            }
            if (node.father == null){
                topnode = node;
            }
        }
        Map<Node,ArrayList<Integer>> bot2mat = new HashMap<>();    //存放底事件与割集矩阵的对应关系
        Map<Integer,Node> id2bot = new HashMap<>();     //存放顺序id与底事件的对应关系
        int botnum = botarray.size();                   //底事件个数
        ArrayList<Integer> oarray = new ArrayList<>();
        ArrayList<Integer> array1 = new ArrayList<>();
        for (int i=0;i<botnum;i++){
             oarray.add(0);
        }
        for (int i=0;i<botarray.size();i++){                    //将底事件转化为矩阵并记录
            array1 = (ArrayList<Integer>)oarray.clone();
            array1.set(i,1);
            bot2mat.put(botarray.get(i),(ArrayList<Integer>)array1.clone());
            id2bot.put(i,botarray.get(i));
        }
        newarray = calcCut(topnode,faultNode,bot2mat);     //最小割集
//        System.out.println(newarray);
        newarray = noncross(newarray);                      //不交化
//        System.out.println(newarray);
        double sum = 0.0;
        double mul = 1.0;
        for (ArrayList<Integer> integers : newarray) {
            mul = 1.0;
            for (int j = 0; j < integers.size(); j++) {
                if (integers.get(j) == 1) {
                    mul *= id2bot.get(j).getFreq();
                } else if (integers.get(j) == -1) {
                    mul *= (1 - id2bot.get(j).getFreq());
                }
            }
//            System.out.println(mul);
            sum += mul;
        }
        return sum;
    }

    /**
     *
     * @param faultNode 故障树节点
     * @return  fnode 存在故障的节点
     */
    public static ArrayList<Node> searchfaultnode(ArrayList<Node> faultNode){
        ArrayList<Node> fnode = new ArrayList<>();
        ArrayList<String> fnodename = new ArrayList<>();
        for (Node d1 :faultNode){
            if(d1.freq!=0.0){
                fnode.add(d1);
                fnodename.add(d1.getName());
            }
        }
        for (int i=0;i<fnode.size();i++){
            if(fnode.get(i).getFather()==null){
                continue;
            }
            if (!fnodename.contains(fnode.get(i).getFather())){
                if (searchNode(fnode.get(i).getFather(),faultNode).getGate().equals("0")){
                    int flag =1;
                    for(String n1:searchNode(fnode.get(i).getFather(),faultNode).getChildren()){
                        if(!fnodename.contains(n1)){
                            flag=0;
                            break;
                        }
                    }
                    if(flag==1){
                        fnode.add(searchNode(fnode.get(i).getFather(),faultNode));
                        fnodename.add(searchNode(fnode.get(i).getFather(),faultNode).getName());
                    }
                }
                else if(searchNode(fnode.get(i).getFather(),faultNode).getGate().equals("+")){
                    for (String n1:searchNode(fnode.get(i).getFather(),faultNode).getChildren()){
                        if(fnodename.contains(n1)){
                            fnode.add(searchNode(fnode.get(i).getFather(),faultNode));
                            fnodename.add(searchNode(fnode.get(i).getFather(),faultNode).getName());
                            break;
                        }
                    }
                }
            }
        }
        ArrayList<Node> fnode1 = new ArrayList<>();
        for(Node N1:fnode){
            if(N1.getFather()!=null){
                if(fnodename.contains(N1.getFather())){
                    fnode1.add(N1);
                }
            }
            else{fnode1.add(N1);}
        }
        return fnode1;
    }

    public static ArrayList<Node> parttree(String nodename,ArrayList<Node> faultNode){
        ArrayList<Node> newarray = new ArrayList<>();
        if(searchNode(nodename,faultNode).children.size()!=0){
            Node temp1 = new Node();
            temp1.setFather(null);
            temp1.setChildren(searchNode(nodename,faultNode).getChildren());
            temp1.setId(searchNode(nodename,faultNode).getId());
            temp1.setName(searchNode(nodename,faultNode).getName());
            temp1.setGate(searchNode(nodename,faultNode).getGate());
            temp1.setFreq(searchNode(nodename,faultNode).getFreq());
            newarray.add(temp1);
        }
        for(int i=0;i<newarray.size();i++){
            if(newarray.get(i).getChildren().size()!=0){
                for(String n1:newarray.get(i).getChildren()){
                    Node temp2 = new Node();
                    temp2.setId(searchNode(n1,faultNode).getId());
                    temp2.setFather(searchNode(n1,faultNode).getFather());
                    temp2.setChildren(searchNode(n1,faultNode).getChildren());
                    temp2.setName(searchNode(n1,faultNode).getName());
                    temp2.setGate(searchNode(n1,faultNode).getGate());
                    temp2.setFreq(searchNode(n1,faultNode).getFreq());
                    newarray.add(temp2);
                }
            }
        }
        return newarray;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> array0 = new ArrayList<Integer>();
        array0.add(8);
        array0.add(2);
        array0.add(4);
        ArrayList<Node> Fualtnodes = new ArrayList<Node>();
        ArrayList<Node> testnodes = new ArrayList<Node>();
        long date = 12345678;
        int Uid =1;
        MakeFaultTree.InitialNodes(testnodes, date,Uid);
//        System.out.println(testnodes.size());
//        for (Node d:testnodes){
//            System.out.println(d.getName());
//            System.out.println(d.getChildren().size());
//        }
        Fualtnodes = parttree("调速器压力油罐故障",testnodes);
        for (Node d:Fualtnodes){
            System.out.println(d.getName()+d.getChildren()+d.getFather()+d.getGate()+d.getFreq());
//            System.out.println(d.getChildren().size());
        }
        System.out.println(calcTopPre(Fualtnodes));
        for (Node d:testnodes){
            System.out.println(d.getName()+d.getChildren()+d.getFather()+d.getGate()+d.getFreq());
        }
    }
}

