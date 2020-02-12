package main.model;

import main.dao.DaoTree;
import main.util.TimeUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import main.util.TimeUtils;
import main.util.mysqlUtil;

public class FaultDiagnosis {
    private ArrayList<FaultFeature> Faults;
    private ArrayList<Node> nodes;
    public static List<Symptomkind> symkind = new ArrayList<Symptomkind>();
    private ArrayList<String> faultData;

    /**
     * 构造故障树诊断类
     *
     * @param nodeslist 故障树节点信息列表
     */
    public FaultDiagnosis(ArrayList<Node> nodeslist) {
        nodes = nodeslist;
        Faults = new ArrayList<FaultFeature>();
    }

    public FaultDiagnosis() { super(); }

    public ArrayList<String> readValue4sql(String id, String table, long starttime, long endtime) {
        Connection dbConn = null;
        Statement stmt = null;
        ResultSet rs1 = null;
        String sql1 = null;
        ArrayList<String> valueresult = new ArrayList<String>();
        if (starttime > endtime) {
            long tmp = starttime;
            starttime = endtime;
            endtime = tmp;
        }
        try {
            dbConn = GovDBConfig.getconnection();// DriverManager.getConnection(testURL,
            // userName, userPwd);
            System.out.println("Connection Successful!"); // 如果连接成功
            // 控制台输出Connection
            // Successful!
            stmt = dbConn.createStatement();
            /**
             * 这里假设测点数据库的表名为table，其中列名为id：测点id，Name：测点名称，
             * value:测点测到的数值，Time:测点记录的时间
             * 若实际数据库表格发生变化，则在此处做出相应的调整。
             */
            System.out.println("读取数据");
            sql1 = "SELECT * FROM"+table+" WHERE Time>"+starttime*1000+" AND Time<"+endtime*1000+" AND id="+id+" ORDER BY time;";
            rs1 = stmt.executeQuery(sql1);
            while (rs1.next()) {
                valueresult.add(rs1.getString("value"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            GovDBConfig.closeConnection(rs1, stmt, dbConn);
        }
        return valueresult;
    }


    public Node searchNode(String name, ArrayList<Node> N1) {

        int size = N1.size();
        for (int i = 0; i < size; i++) {
            Node newNode = N1.get(i);
            if (newNode.name.equals(name))
                return newNode;
        }
        return null;
    }

    public ArrayList<Node> FaultTreeDiagnosis(long date) throws ClassNotFoundException, SQLException, Exception{
//        Date date = new Date();
//        date = TimeUtils.DatetoString(date);
        ArrayList<Node> Fualtnodes = new ArrayList<Node>();
        ArrayList<Node> testnodes = new ArrayList<Node>();
        ArrayList<Node> Comnodes = new ArrayList<Node>();
        ArrayList<Node> DGnodes = new ArrayList<Node>();
        MakeFaultTree.InitialNodes(testnodes,Comnodes,DGnodes,date);
        mysqlUtil.saveFaultTree(testnodes);
        mysqlUtil.saveFaultTree(Comnodes);
        mysqlUtil.saveFaultTree(DGnodes);
//        System.out.println(testnodes.size());
        ////////
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < testnodes.size(); i++) {        //找出底事件
            // if(nodes.get(i).gate == null)
//            System.out.println(testnodes.get(i).children.size());
            if (testnodes.get(i).children.size() == 0) {
                result.add(testnodes.get(i).name);
            }
        }
//        System.out.println(result);
//        System.out.println(result.size());
        int k = 0;
        for (String d :result){
            Node d1 = searchNode(d, testnodes);
//            System.out.println(d1.name);
            while((d1.father != null)){

                if (d1.freq == 0){
                    break;
                }
                Fualtnodes.add(d1);
                Node d1father = searchNode(d1.father, testnodes);
                if (d1father == null){
                    break;
                }

//                System.out.println(d1father.gate);
//                String gate1 = d1father.getGate();
//                System.out.println(gate1.equals("0"));
//                System.out.println(d1father.father);
//                System.out.println("d1father.gate");
//                System.out.println(d1father==null);
                if (d1father.gate.equals("+")) {
                    d1father.setFreq(d1.freq);
                    d1 = searchNode(d1.father, testnodes);

                    d1.setChildren(d1father.children);
//                    d1.setFreq(d1father.freq);
//                    d1.setGate(d1father.gate);
//                    d1.setName(d1father.name);
//                    d1.setFather(d1father.father);

//                    d1 = d1father;

                } else if (d1father.gate.equals("0")) {

                    for (int j = 0; j < d1father.children.size(); j++) {
                        if (j == 0) {
                            d1father.setFreq(1.0);
//                            System.out.println(d1father.children);
//                            System.out.println(d1father.children);
                            d1father.setFreq((double) ((int) d1father.freq & (int) searchNode(d1father.children.get(j), testnodes).freq));
                        } else {
                            d1father.setFreq((double) ((int) d1father.freq & (int) searchNode(d1father.children.get(j), testnodes).freq));
                        }
                    }
                    d1 = searchNode(d1.father, testnodes);
//                    System.out.println(d1.name);
//                    break;
//                    d1.setChildren(d1father.children);
//                    d1.setFreq(d1father.freq);
//                    d1.setGate(d1father.gate);
//                    d1.setName(d1father.name);
//                    d1.setFather(d1father.father);
//                    d1 = d1father;
                }
//                System.out.println(d1.name);
//                System.out.println(d1.father);
//                System.out.println(d1.father == null);
                if (d1.father == null){
                    break;
                }
            }
            if (d1.father == null){
                Fualtnodes.add(d1);
            }
        }
//////空压机系统故障
             List<String> Comresult = new ArrayList<String>();
        for (int i = 0; i < Comnodes.size(); i++) {        //找出底事件
            // if(Comnodes.get(i).gate == null)
//            System.out.println(Comnodes.get(i).children.size());
            if (Comnodes.get(i).children.size() == 0) {
                Comresult.add(Comnodes.get(i).name);
            }
        }
//        System.out.println(Comresult);
//        System.out.println(Comresult.size());

        for (String d :Comresult){
            Node d1 = searchNode(d, Comnodes);
//            System.out.println(d1.name);
            while((d1.father != null)){

                if (d1.freq == 0){
                    break;
                }
                Fualtnodes.add(d1);
                Node d1father = searchNode(d1.father, Comnodes);
                if (d1father == null){
                    break;
                }

//                System.out.println(d1father.gate);
//                String gate1 = d1father.getGate();
//                System.out.println(gate1.equals("0"));
//                System.out.println(d1father.father);
//                System.out.println("d1father.gate");
//                System.out.println(d1father==null);
                if (d1father.gate.equals("+")) {
                    d1father.setFreq(d1.freq);
                    d1 = searchNode(d1.father, Comnodes);

                    d1.setChildren(d1father.children);
//                    d1.setFreq(d1father.freq);
//                    d1.setGate(d1father.gate);
//                    d1.setName(d1father.name);
//                    d1.setFather(d1father.father);

//                    d1 = d1father;

                } else if (d1father.gate.equals("0")) {

                    for (int j = 0; j < d1father.children.size(); j++) {
                        if (j == 0) {
                            d1father.setFreq(1.0);
//                            System.out.println(d1father.children);
//                            System.out.println(d1father.children);
                            d1father.setFreq((double) ((int) d1father.freq & (int) searchNode(d1father.children.get(j), Comnodes).freq));
                        } else {
                            d1father.setFreq((double) ((int) d1father.freq & (int) searchNode(d1father.children.get(j), Comnodes).freq));
                        }
                    }
                    d1 = searchNode(d1.father, Comnodes);
//                    System.out.println(d1.name);
//                    break;
//                    d1.setChildren(d1father.children);
//                    d1.setFreq(d1father.freq);
//                    d1.setGate(d1father.gate);
//                    d1.setName(d1father.name);
//                    d1.setFather(d1father.father);
//                    d1 = d1father;
                }
//                System.out.println(d1.name);
//                System.out.println(d1.father);
//                System.out.println(d1.father == null);
                if (d1.father == null){
                    break;
                }
            }
            if (d1.father == null){
                Fualtnodes.add(d1);
            }
        }
/////顶盖排水系统故障
        List<String> DGresult = new ArrayList<String>();
        for (int i = 0; i < DGnodes.size(); i++) {        //找出底事件
            // if(DGnodes.get(i).gate == null)
//            System.out.println(DGnodes.get(i).children.size());
            if (DGnodes.get(i).children.size() == 0) {
                DGresult.add(DGnodes.get(i).name);
            }
        }
//        System.out.println(DGresult);
//        System.out.println(DGresult.size());

        for (String d :DGresult){
            Node d1 = searchNode(d, DGnodes);
//            System.out.println(d1.name);
            while((d1.father != null)){

                if (d1.freq == 0){
                    break;
                }
                Fualtnodes.add(d1);
                Node d1father = searchNode(d1.father, DGnodes);
                if (d1father == null){
                    break;
                }

//                System.out.println(d1father.gate);
//                String gate1 = d1father.getGate();
//                System.out.println(gate1.equals("0"));
//                System.out.println(d1father.father);
//                System.out.println("d1father.gate");
//                System.out.println(d1father==null);
                if (d1father.gate.equals("+")) {
                    d1father.setFreq(d1.freq);
                    d1 = searchNode(d1.father, DGnodes);

                    d1.setChildren(d1father.children);
//                    d1.setFreq(d1father.freq);
//                    d1.setGate(d1father.gate);
//                    d1.setName(d1father.name);
//                    d1.setFather(d1father.father);

//                    d1 = d1father;

                } else if (d1father.gate.equals("0")) {

                    for (int j = 0; j < d1father.children.size(); j++) {
                        if (j == 0) {
                            d1father.setFreq(1.0);
//                            System.out.println(d1father.children);
//                            System.out.println(d1father.children);
                            d1father.setFreq((double) ((int) d1father.freq & (int) searchNode(d1father.children.get(j), DGnodes).freq));
                        } else {
                            d1father.setFreq((double) ((int) d1father.freq & (int) searchNode(d1father.children.get(j), DGnodes).freq));
                        }
                    }
                    d1 = searchNode(d1.father, DGnodes);
//                    System.out.println(d1.name);
//                    break;
//                    d1.setChildren(d1father.children);
//                    d1.setFreq(d1father.freq);
//                    d1.setGate(d1father.gate);
//                    d1.setName(d1father.name);
//                    d1.setFather(d1father.father);
//                    d1 = d1father;
                }
//                System.out.println(d1.name);
//                System.out.println(d1.father);
//                System.out.println(d1.father == null);
                if (d1.father == null){
                    break;
                }
            }
            if (d1.father == null){
                Fualtnodes.add(d1);
            }
        }



        return Fualtnodes;
    }

    public static void main(String[] args) throws ParseException, Exception {
        long date1 =1513651344;
//        String date = TimeUtils.longToString(date1*1000,"yyyy-MM-dd HH:mm:ss");
        FaultDiagnosis c = new FaultDiagnosis();

        ArrayList<Node> faultnodes = null;
        try {
            faultnodes = c.FaultTreeDiagnosis(date1);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Node> faultnodes_copy = new ArrayList<>();
        for (Node f1:faultnodes){
            if(!faultnodes_copy.contains(f1)){
                faultnodes_copy.add(f1);
            }
        }
        System.out.println(faultnodes_copy.size());
        System.out.println("激活的节点：");
        for (Node d1 :faultnodes_copy){
            String name1 = d1.name;
            System.out.println(name1);
        }
        if (faultnodes_copy.size()!=0){
            mysqlUtil.savediagres(date1,faultnodes_copy);
        }
//        ArrayList<Integer> asd = new ArrayList<>();
//        asd.add(1);
//        asd.add(2);
//        asd.add(1);
//        System.out.println(asd.indexOf(1));

//        System.out.println(faultnodes.size());
//        System.out.println("激活的节点：");
//        for (Node d1 :faultnodes){
//            String name1 = d1.name;
//            System.out.println(name1);
//        }
//        String tr = "123";
//        System.out.println(tr+date1);
    }
}
