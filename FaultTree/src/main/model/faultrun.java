package main.model;

import main.config.db2xml;
import main.dao.ResultSave;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;


public class faultrun{
    public static void faultmainrun(){
        long date1 = System.currentTimeMillis()/1000;
//        System.out.println(date1);
        for(int kind=1;kind<=2;kind++) {
            for (int Uid = 1; Uid <= 4; Uid++) {
//                long startTime = System.currentTimeMillis();
//                System.out.println(date1);
//                date1=1513670201;
//                db2xml savexml=new db2xml();
//                savexml.saveFile(kind);
                ArrayList<Node> Inodes = new ArrayList<>();
                try {
                    MakeFaultTree.InitialNodes(Inodes,date1,Uid,kind);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Double fre = CaculateMinCutset1.calcTopPre(Inodes);
//                String[] name={"球阀本体","球阀油系统"};
//                System.out.println(Uid+"号机组"+name[kind-1]);
//                System.out.println("根节点故障概率："+fre);
//                System.out.println("故障节点：");
                for(Node d1:CaculateMinCutset1.searchfaultnode(Inodes)){
                    if(d1.getFather()==null){
                        d1.setFreq(fre);
                    }
                    if (d1.getChildren().size()!=0 && d1.getFather()!=null) {
                        d1.setFreq(CaculateMinCutset1.calcTopPre(CaculateMinCutset1.parttree(d1.getName(), Inodes)));
                    }
//                    System.out.println("故障节点编号："+d1.getId());
//                    System.out.println("故障节点名称："+d1.getName());
//                    System.out.println("故障发生概率："+d1.getFreq()+"\r\n");
                }
                if(CaculateMinCutset1.searchfaultnode(Inodes).size()!=0) {
                    ResultSave.savediagres(date1, String.valueOf(Uid), kind, CaculateMinCutset1.searchfaultnode(Inodes));
                    ResultSave.savediagresguide(date1, String.valueOf(Uid), kind);
                    ResultSave.savediagreport(date1,String.valueOf(Uid),kind);
                }
//                long endTime = System.currentTimeMillis();
//                System.out.println(endTime-startTime+"ms");
            }
            }
        }

    public static void main(String[] args){
        faultmainrun();
    }
}
