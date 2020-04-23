package main.model;

import main.config.db2xml;
import main.dao.ResultSave;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TimerTask;


public class fualtrun extends TimerTask {
    public static void fualtmainrun() throws SQLException, ClassNotFoundException {
        long date1 = System.currentTimeMillis()/1000;
        for(int kind=1;kind<=2;kind++) {
            for (int Uid = 1; Uid <= 5; Uid++) {
                long startTime = System.currentTimeMillis();
//                long date1=1514871810;
                db2xml savexml=new db2xml();
                savexml.saveFile(2);
                ArrayList<Node> Inodes = new ArrayList<>();
                MakeFaultTree.InitialNodes(Inodes,date1,Uid,kind);
                Double fre = CaculateMinCutset1.calcTopPre(Inodes);
                System.out.println("根节点故障概率："+fre);
                System.out.println("故障节点：");
                for(Node d1:CaculateMinCutset1.searchfaultnode(Inodes)){
                    if(d1.getFather()==null){
                        d1.setFreq(fre);
                    }
                    if (d1.getChildren().size()!=0 && d1.getFather()!=null) {

                        d1.setFreq(CaculateMinCutset1.calcTopPre(CaculateMinCutset1.parttree(d1.getName(), Inodes)));
                    }
                    System.out.println(d1.getName()+d1.getFreq());
                }
                ResultSave.savediagres(date1,String.valueOf(Uid),kind,CaculateMinCutset1.searchfaultnode(Inodes));
                ResultSave.savediagresguide(date1,String.valueOf(Uid),kind);
//        ArrayList<String> qwe=new ArrayList<>();
//        qwe.add("qwe");
//        qwe.add("rty");
//        System.out.println(qwe.contains("qwe"));
                long endTime = System.currentTimeMillis();
                System.out.println(endTime-startTime+"ms");
            }
            }
        }
        int Uid=1;  //机组编号
        int kind = 2;//区分本体与油系统

    public void run(){
        try {
            fualtmainrun();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
