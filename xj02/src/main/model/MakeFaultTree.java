package main.model;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.lang.model.element.Element;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import main.service.Oilsysboolflag;
import main.service.Oilsysfloatflag;
import main.util.TimeUtils;


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

    /**
     * 节点信息初始化
     *
     * @param Inodes
     *            节点信息列表
     */
    @SuppressWarnings("rawtypes")
    public static void InitialNodes(ArrayList<Node> Inodes, ArrayList<Node> ComInodes,ArrayList<Node> DGInodes,long time) throws ClassNotFoundException,SQLException{
        //构建故障树
        String result = GovXMLUtils.GetXMLPath("FaultTree");
//        System.out.println("文件读取成功！");
//        System.out.println(result);
        result = "C:\\Users\\xiao\\Desktop\\xj02\\src\\main\\config\\FaultTree.xml";
//        result = "C:\\Users\\xiao\\Desktop\\xjFTA\\out\\production\\xjFTA\\main\\config";
//        System.out.println(new File(result).exists());
        File file = new File(result);
        if (file.exists()){
            try{
                SAXReader reader = new SAXReader();// 创建SAXReader
                Document document =reader.read(file);//从xml文件获取数据
//                System.out.println(1);
                Element rootElement = document.getRootElement();// 获取根节点 (WorkNode)

                /**
                 * 得到第一层节点
                 */
                List list = rootElement.elements("WorkNode");//得到所有(WorkNode)元素
                Iterator iter = list.iterator();//遍历元素

                //定义浮点型量和布尔型量
                //	BallValvefaultBool bv1=new BallValvefaultBool();
                //	BallValvefloatDay bv2 = new BallValvefloatDay();
                Oilsysboolflag ob1 = new Oilsysboolflag();
                Oilsysfloatflag of1 = new Oilsysfloatflag();
                while (iter.hasNext()) {
                    Element ele = (Element) iter.next();
                    Node temp = new Node();
                    temp.name = ele.attribute("Name").getValue();
                    temp.Id = ele.attribute("ID").getValue();
                    String[] childs = ele.attribute("children").getValue().split("，");
                    for (int i =0; i<childs.length; i++) {
                        if (!childs[i].equals("null")) {
                            temp.children.add(childs[i]);
                        }
                    }
                    temp.gate = ele.attribute("gate").getValue();
//                    Long time = TimeUtils.stringToLong(date,"yyyy-MM-dd HH:mm:ss")/1000;
//                    此处为temp.freq赋值，需利用相关函数，暂且空缺
                    if (temp.name.equals("压力油罐油压过高")) {
//                       temp.freq = ob1.getValue51111(time, 0);  //unitID为机组编号，0表示1号机组
                        temp.freq = 0;
                    } else if (temp.name.equals("压力油罐液位过低")) {
//                        temp.freq = ob1.getValue51112 (time, 0);
                        temp.freq = 0;
                    }else if (temp.name.equals("压力油罐油压降低")) {
                        temp.freq = of1.getValue51121 (time, 0);
                    }else if (temp.name.equals("油压装置自动补气投入信号")) {
                        temp.freq = ob1.getValue51122 (time, 0);
                    }else if (temp.name.equals("压力油罐油压增大")) {
                        temp.freq = of1.getValue51131 (time, 0);
                    }else if (temp.name.equals("油压装置自动补气撤出信号")) {
                        temp.freq = ob1.getValue51132 (time, 0);
                    }else if (temp.name.equals("压力油罐最高油位上升较快")) {
                        temp.freq = of1.getValue4121 (time, 0);
                    }else if (temp.name.equals("压力油罐压力降低速度过快")) {
                        temp.freq = of1.getValue4122 (time, 0);
                    }else if (temp.name.equals("1号油泵绝对打油速率下降")) {
                        temp.freq = of1.getValue4211 (time, 0);
                    }else if (temp.name.equals("2号油泵绝对打油速率下降")) {
                        temp.freq = of1.getValue4221 (time, 0);
                    }else if (temp.name.equals("3号油泵绝对打油速率下降")) {
                        temp.freq = of1.getValue4231 (time, 0);
                    }else if (temp.name.equals("1号泵加载异常")) {
                        temp.freq=(ob1.getValue52411_1 (time, 0))&(ob1.getValue52412_1 (time, 0))&(ob1.getValue52413_1 (time, 0))&(1 - (ob1.getValue52414_1 (time, 0)));
                    }else if (temp.name.equals("2号泵加载异常")) {
                        temp.freq=(ob1.getValue52411_2 (time, 0))&(ob1.getValue52412_2 (time, 0))&(ob1.getValue52413_2 (time, 0))&(1 - (ob1.getValue52414_2 (time, 0)));
                    }else if (temp.name.equals("3号泵加载异常")) {
                        temp.freq=(ob1.getValue52411_3 (time, 0))&(ob1.getValue52412_3 (time, 0))&(ob1.getValue52413_3 (time, 0))&(1 - (ob1.getValue52414_3 (time, 0)));
                    }else if (temp.name.equals("1号泵异常")) {
                        temp.freq = ob1.getValue52411_1 (time, 0)&(1-ob1.getValue52421_1(time, 0));
                    }else if (temp.name.equals("2号泵异常")) {
                        temp.freq = ob1.getValue52411_2 (time, 0)&(1-ob1.getValue52421_2(time, 0));
                    }else if (temp.name.equals("3号泵异常")) {
                        temp.freq = ob1.getValue52411_3 (time, 0)&(1-ob1.getValue52421_3(time, 0));
                    }else if (temp.name.equals("压油泵无启动信号")) {
                        temp.freq =1 - (ob1.getValue52431_1 (time, 0)) & (ob1.getValue52431_2 (time, 0)) & (ob1.getValue52431_3 (time, 0));
                    }else if (temp.name.equals("压力油罐油压过低")) {
                        temp.freq = ob1.getValue52432 (time, 0);
                    }else if (temp.name.equals("回油箱油混水报警")) {
//                        System.out.println("油混水");
                        temp.freq = ob1.getValue4311 (time, 0);
//                        System.out.println("结束时间："+time);
//                        temp.freq =1;
//                        System.out.println(temp.freq);
                    }else if (temp.name.equals("回油箱液位过低")) {
                        temp.freq = ob1.getValue54111 (time, 0);
                    }else if (temp.name.equals("总油量不变")) {
                        temp.freq = of1.getValue54121 (time, 0);
                    }else if (temp.name.equals("回油箱液位最小下降速度变大")) {
                        temp.freq = of1.getValue54122 (time, 0);
                   }else if (temp.name.equals("调速器漏油箱油位过高")) {
                        temp.freq = ob1.getValue351 (time, 0);
                    }else if (temp.name.equals("调速器漏油箱油泵运行信号异常")) {
//                        temp.freq =1 - ob1.getValue352 (time, 0);
                        temp.freq = 0;
                    }else {
                       temp.freq = 0;
                   }

//                    if (temp.name.equals("压力油罐油压过高")) {
//                        temp.freq = 1;  //unitID为机组编号，0表示1号机组
//                    } else if (temp.name.equals("压力油罐液位过低")) {
//                        temp.freq = 1;
//                    }else if (temp.name.equals("压力油罐油压降低")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("油压装置自动补气投入信号")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("压力油罐油压增大")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("油压装置自动补气撤出信号")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("压力油罐最高油位上升较快")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("压力油罐压力降低速度过快")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("1号油泵绝对打油速率下降")) {
//                        temp.freq = 1;
//                    }else if (temp.name.equals("2号油泵绝对打油速率下降")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("3号油泵绝对打油速率下降")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("油泵启动命令正常")) {
//                        temp.freq = 1;
//                    }else if (temp.name.equals("油泵卸载命令正常")) {
//                        temp.freq = 1;
//                    }else if (temp.name.equals("油泵加载命令正常")) {
//                        temp.freq = 1;
//                    }else if (temp.name.equals("油泵已加载信号异常")) {
//                        temp.freq =0;
//                    }else if (temp.name.equals("油泵已运行信号异常")) {
//                        temp.freq =0;
//                    }else if (temp.name.equals("压油泵无启动信号")) {
//                        temp.freq =0;
//                    }else if (temp.name.equals("压力油罐油压过低")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("回油箱油混水报警")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("回油箱液位过低")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("总油量不变")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("回油箱液位最小下降速度变大")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("调速器漏油箱油位过高")) {
//                        temp.freq = 0;
//                    }else if (temp.name.equals("调速器漏油箱油泵运行信号异常")) {
//                        temp.freq =0;
//                    }else {
//                        temp.freq = 0;
//                    }

                    if (!ele.attribute("father").getValue().equals("null")) {
                        temp.father = ele.attribute("father").getValue();
                    }
                    temp.gate = ele.attribute("gate").getValue();
                    Inodes.add(temp);

                }

            }catch (DocumentException | ParseException e) {
                e.printStackTrace();
            }
        }

//        Node temp = Inodes.get(0);
//        Inodes.set(0,Inodes.get(1));
//        Inodes.set(1, temp);

        for (int i = 0; i < Inodes.size();i++) {
            if (Inodes.get(i).father == null) {
                Node tempn = Inodes.get(0);
                Inodes.set(0, Inodes.get(i));
                Inodes.set(i, tempn);
            }
        }
//        System.out.println(Inodes.size());


//////空压机系统故障
        String Comresult = GovXMLUtils.GetXMLPath("ComFaultTree");
        Comresult = "C:\\Users\\xiao\\Desktop\\xj02\\src\\main\\config\\ComFaultTree.xml";

        File file4 = new File(Comresult);
        if (file4.exists()) {
            try {
                SAXReader reader = new SAXReader();// 创建SAXReader
                Document document = reader.read(file);//从xml文件获取数据
//                System.out.println(1);
                Element rootElement = document.getRootElement();// 获取根节点 (WorkNode)

                /**
                 * 得到第一层节点
                 */
                List list = rootElement.elements("WorkNode");//得到所有(WorkNode)元素
                Iterator iter = list.iterator();//遍历元素

                //定义浮点型量和布尔型量
                //	BallValvefaultBool bv1=new BallValvefaultBool();
                //	BallValvefloatDay bv2 = new BallValvefloatDay();
                Oilsysboolflag ob1 = new Oilsysboolflag();
                Oilsysfloatflag of1 = new Oilsysfloatflag();
                while (iter.hasNext()) {
                    Element ele = (Element) iter.next();
                    Node temp = new Node();
                    temp.name = ele.attribute("Name").getValue();
                    temp.Id = ele.attribute("ID").getValue();
                    String[] childs = ele.attribute("children").getValue().split("，");
                    for (int i = 0; i < childs.length; i++) {
                        if (!childs[i].equals("null")) {
                            temp.children.add(childs[i]);
                        }
                    }
                    temp.gate = ele.attribute("gate").getValue();
//                    Long time = TimeUtils.stringToLong(date,"yyyy-MM-dd HH:mm:ss")/1000;
//                    此处为temp.freq赋值，需利用相关函数，暂且空缺
                    if (temp.name.equals("中压空压机停止时长变短")) {
                       temp.freq = ob1.getValue4_4111(time, 0);  //unitID为机组编号，0表示1号机组
//                        temp.freq = 0;
                    } else if (temp.name.equals("中压空压机停止时长不变")) {
                        temp.freq = ob1.getValue4_4121 (time, 0);
//                        temp.freq = 0;
                    } else if (temp.name.equals("中压空压机运行时间增加")) {
                        temp.freq = (ob1.getValue4_4122_1(time, 0)) | (ob1.getValue4_4122_2(time, 0)) | (ob1.getValue4_4122_3(time, 0)) | (ob1.getValue4_4122_4(time, 0)) | (ob1.getValue4_4122_5(time, 0));
                    } else if (temp.name.equals("低压空压机停止时长变短")) {
                       temp.freq =(ob1.getValue4_4211_1(time, 0))|(ob1.getValue4_4211_2(time, 0))|(ob1.getValue4_4211_3(time, 0));  //unitID为机组编号，0表示1号机组
//                        temp.freq = 0;
                    } else if (temp.name.equals("低压空压机停止时长不变")) {
                        temp.freq =(ob1.getValue4_4221_1(time, 0))|(ob1.getValue4_4221_2(time, 0))|(ob1.getValue4_4221_3(time, 0));
//                        temp.freq = 0;
                    } else if (temp.name.equals("低压空压机运行时间增加")) {
                        temp.freq = (ob1.getValue4_4222_1(time, 0)) | (ob1.getValue4_4222_2(time, 0)) | (ob1.getValue4_4222_3(time, 0));
                    } else {
                        temp.freq = 0;
                    }


                    if (!ele.attribute("father").getValue().equals("null")) {
                        temp.father = ele.attribute("father").getValue();
                    }
                    temp.gate = ele.attribute("gate").getValue();
                    ComInodes.add(temp);

                }

            } catch (DocumentException | ParseException e) {
                e.printStackTrace();
            }
        }

//        Node temp = Inodes.get(0);
//        Inodes.set(0,Inodes.get(1));
//        Inodes.set(1, temp);

        for (int i = 0; i < ComInodes.size(); i++) {
            if (ComInodes.get(i).father == null) {
                Node Comtempn = ComInodes.get(0);
                ComInodes.set(0, ComInodes.get(i));
                ComInodes.set(i, Comtempn);
            }
        }

        //////顶盖排水系统故障
        String DGresult = GovXMLUtils.GetXMLPath("ComFaultTree");
        DGresult = "C:\\Users\\xiao\\Desktop\\xj02\\src\\main\\config\\DGFaultTree.xml";

        File file5 = new File(DGresult);
        if (file5.exists()) {
            try {
                SAXReader reader = new SAXReader();// 创建SAXReader
                Document document = reader.read(file);//从xml文件获取数据
//                System.out.println(1);
                Element rootElement = document.getRootElement();// 获取根节点 (WorkNode)

                /**
                 * 得到第一层节点
                 */
                List list = rootElement.elements("WorkNode");//得到所有(WorkNode)元素
                Iterator iter = list.iterator();//遍历元素

                //定义浮点型量和布尔型量
                //	BallValvefaultBool bv1=new BallValvefaultBool();
                //	BallValvefloatDay bv2 = new BallValvefloatDay();
                Oilsysboolflag ob1 = new Oilsysboolflag();
                Oilsysfloatflag of1 = new Oilsysfloatflag();
                while (iter.hasNext()) {
                    Element ele = (Element) iter.next();
                    Node temp = new Node();
                    temp.name = ele.attribute("Name").getValue();
                    temp.Id = ele.attribute("ID").getValue();
                    String[] childs = ele.attribute("children").getValue().split("，");
                    for (int i = 0; i < childs.length; i++) {
                        if (!childs[i].equals("null")) {
                            temp.children.add(childs[i]);
                        }
                    }
                    temp.gate = ele.attribute("gate").getValue();
//                    Long time = TimeUtils.stringToLong(date,"yyyy-MM-dd HH:mm:ss")/1000;
//                    此处为temp.freq赋值，需利用相关函数，暂且空缺
                    if (temp.name.equals("排水泵停泵时长缩短")) {
                       temp.freq = (ob1.getValue5_4111_1(time, 0))|(ob1.getValue5_4111_2(time,0));  //unitID为机组编号，0表示1号机组
//                        temp.freq = 0;
                    } else if (temp.name.equals("排水泵停泵时长过短")) {
                        temp.freq = (ob1.getValue5_4121_1(time, 0))|(ob1.getValue5_4121_2(time,0));
//                        temp.freq = 0;
                    } else if (temp.name.equals("排水泵绝对排水速率降低")) {
                        temp.freq = (ob1.getValue4_4211_1(time, 0)) | (ob1.getValue5_4211_2(time, 0));
                    } else {
                        temp.freq = 0;
                    }


                    if (!ele.attribute("father").getValue().equals("null")) {
                        temp.father = ele.attribute("father").getValue();
                    }
                    temp.gate = ele.attribute("gate").getValue();
                    DGInodes.add(temp);

                }

            } catch (DocumentException | ParseException e) {
                e.printStackTrace();
            }
        }

//        Node temp = Inodes.get(0);
//        Inodes.set(0,Inodes.get(1));
//        Inodes.set(1, temp);

        for (int i = 0; i < DGInodes.size(); i++) {
            if (DGInodes.get(i).father == null) {
                Node DGtempn =DGInodes.get(0);
                DGInodes.set(0,DGInodes.get(i));
                DGInodes.set(i, DGtempn);
            }
        }
//        System.out.println(Inodes.size());

//////////

    }

    /**
     * 将Document对象保存为一个xml文件到本地
     *
     * @param document
     *            需要保存的document对象
     * @param filename
     *            保存的文件名
     * @return true:保存成功 flase:失败
     */
    public static boolean doc2XmlFile(Document document, String filename) {
        boolean flag = true;
        try {
            /* 将document中的内容写入文件中 */
            // 默认为UTF-8格式，指定为"UTF-8"
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileWriter(new File(filename)), format);
            writer.write(document);
            writer.close();
        } catch (Exception ex) {
            flag = false;
            ex.printStackTrace();
        }
        return flag;
    }

    public Long getTime() {
        return time;
    }



    public void setTime(Long time) {
        this.time = time;
    }


    public ArrayList<Node> getEquations() {
        return equations;
    }

    public void setEquations(ArrayList<Node> equations) {
        this.equations = equations;
    }

}
