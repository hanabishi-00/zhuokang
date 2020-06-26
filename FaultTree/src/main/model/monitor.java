package main.model;

import main.config.db2xml;
import main.service.BoolTree;
import main.service.FloatTree;
import main.service.SpecialTree;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TransferQueue;

public class monitor {
    public static double getfreValue1(long time, int unitID, ArrayList<ArrayList<String>> mon, ArrayList<ArrayList<String>> thr, String judg, int timeinter) throws ClassNotFoundException, SQLException, ParseException {
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
                p1 = bool1.BooleanTree1(mon.get(0).get(unitID-1),time,thr1.get(0),timeinter);
                return p1;
            case "Booltime":
                BoolTree bool2 = new BoolTree();
                ArrayList<String> measureID1 = new ArrayList<String>();
                for (ArrayList<String> d:mon) {
                    measureID1.add(d.get(unitID-1));
                }
                p1 = bool2.BooltimeTree1(measureID1,time,timeinter);
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
                p1 = float2.FloateanTree1(mon.get(0).get(unitID-1), time,thr2.get(0),thr2.get(1),timeinter);
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
                p1 = float3.TrendTree1(mon.get(0).get(unitID-1), time,thr3.get(0),thr3.get(1),timeinter);
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
                p1 = float4.SpeedTrendTree1(mon.get(0).get(unitID-1), time,thr4.get(0),thr4.get(1),timeinter);
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
                p1 = float5.SumFloateanTree1(measureID, time,thr5.get(0),thr5.get(1),timeinter);
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
                p1=sp1.SpecialTree21(measureID2,time,thr6.get(1),timeinter);
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
                p1=sp2.SpecialTree31(mon.get(0).get(unitID-1),time,timeinter);
                return p1;
            default:return p1;
        }
    }

    public static void  InitialNodes1(ArrayList<Node> Inodes, long time, int Uid, int kind, int timeinter) throws ClassNotFoundException,SQLException{
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
                        temp.freq =  getfreValue1(time,Uid,temp.getMonitorid(),temp.getThreshold(),temp.getJudgment(),timeinter);
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

    }


    public static boolean valuemonit(int timeinter,long date1){
        boolean flag=false;
//        long date1 = System.currentTimeMillis()/1000;
//        date1=1513670191;
        for(int kind=1;kind<=2;kind++) {
            for (int Uid = 1; Uid <= 4; Uid++) {
//                long startTime = System.currentTimeMillis();
//                System.out.println(date1);
//                date1=1513670201;
//                db2xml savexml=new db2xml();
//                savexml.saveFile(kind);
                ArrayList<Node> Inodes = new ArrayList<>();
                try {
                    InitialNodes1(Inodes, date1, Uid, kind,timeinter);
                    for (Node d1:Inodes){
//                        System.out.println(d1.getName());
//                        System.out.println(d1.getFreq());
                        if(d1.getJudgment()!=null && !d1.getJudgment().equals("null")){
                            if(!d1.getJudgment().equals("Special1") && d1.getFreq()!=0.0) {
//                                getfreValue1(date1,Uid,d1.getMonitorid(),d1.getThreshold(),d1.getJudgment(),timeinter)!=0.0;
//                                System.out.println(d1.getName());
//                                System.out.println(d1.getFreq());
                                flag = true;
                                return flag;
                            }

                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public static void main(String[] args){
//        long startTime = System.currentTimeMillis();
//        long date1 = System.currentTimeMillis()/1000;
//        System.out.println(valuemonit(120,date1));
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime-startTime+"ms");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sd.format(new Date(1592968764000l)));
    }
}
