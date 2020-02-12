package main.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class Oilsysbool {

    //压力油罐油压过高
    /*public double getValue51111(long time, int unitID) throws ClassNotFoundException, SQLException {
        String measureID[] =new String[]{"336", "2250", "4164", "6078"}; //四个机组的相关测点ID
        BoolTree bool51111 = new BoolTree();
        double p1 = bool51111.BooleanTree("XJdatabase_boolean", measureID[unitID], time);
        return p1;
    }*/
    public double getValue51111(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"336", "2250", "4164", "6078"}; //四个机组的相关测点ID
        BoolTree bool51111 = new BoolTree();
//        double p1 = bool51111.BooleanTree("XJdatabase_boolean", measureID[unitID],time);
        double p1 = bool51111.BooleanTree(measureID[unitID],time);
        return p1;
    }
    //压力油罐液位过低
    public double getValue51112(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1531", "3445", "5359", "7273"}; //四个机组的相关测点ID
        BoolTree bool51112 = new BoolTree();
        double p1 = bool51112.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //油压装置自动补气投入信号
    public double getValue51122(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1560", "3474", "5388", "7302"}; //四个机组的相关测点ID
        BoolTree bool51122 = new BoolTree();
        double p1 = bool51122.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //油压装置自动补气撤出信号
    public double getValue51132(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1561", "3475", "5389", "7303"}; //四个机组的相关测点ID
        BoolTree bool51132 = new BoolTree();
        double p1 = bool51132.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //1号油泵启动命令正常
    public double getValue52411_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1551", "3465", "5379", "7293"}; //四个机组的相关测点ID
        BoolTree bool52411_1 = new BoolTree();
        double p1 = bool52411_1.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //2号油泵启动命令正常
    public double getValue52411_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1554", "3468", "5382", "7296"}; //四个机组的相关测点ID
        BoolTree bool52411_2 = new BoolTree();
        double p1 = bool52411_2.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //3号油泵启动命令正常
    public double getValue52411_3(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1557", "3471", "5385", "7299"}; //四个机组的相关测点ID
        BoolTree bool52411_3 = new BoolTree();
        double p1 = bool52411_3.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //1号油泵卸载命令正常
    public double getValue52412_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1553", "3467", "5381", "7295"}; //四个机组的相关测点ID
        BoolTree bool52412_1 = new BoolTree();
        double p1 = bool52412_1.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //2号油泵卸载命令正常
    public double getValue52412_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1556", "3470", "5384", "7298"}; //四个机组的相关测点ID
        BoolTree bool52412_2 = new BoolTree();
        double p1 = bool52412_2.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //3号油泵卸载命令正常
    public double getValue52412_3(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1559", "3473", "5387", "7301"}; //四个机组的相关测点ID
        BoolTree bool52412_3 = new BoolTree();
        double p1 = bool52412_3.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //1号油泵加载命令正常
    public double getValue52413_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1552", "3466", "5380", "7294"}; //四个机组的相关测点ID
        BoolTree bool52411_1 = new BoolTree();
        double p1 = bool52411_1.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //2号油泵加载命令正常
    public double getValue52413_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1555", "3469", "5383", "7297"}; //四个机组的相关测点ID
        BoolTree bool52411_2 = new BoolTree();
        double p1 = bool52411_2.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //3号油泵加载命令正常
    public double getValue52413_3(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1558", "3472", "5386", "7300"}; //四个机组的相关测点ID
        BoolTree bool52411_3 = new BoolTree();
        double p1 = bool52411_3.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //1号油泵已加载信号正常
    public double getValue52414_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1511", "3425", "5339", "7253"}; //四个机组的相关测点ID
        BoolTree bool52414_1 = new BoolTree();
        double p1 = bool52414_1.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //2号油泵已加载信号正常
    public double getValue52414_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1515", "3429", "5343", "7257"}; //四个机组的相关测点ID
        BoolTree bool52414_2 = new BoolTree();
        double p1 = bool52414_2.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //3号油泵已加载信号正常
    public double getValue52414_3(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1545", "3459", "5373", "7287"}; //四个机组的相关测点ID
        BoolTree bool52414_3 = new BoolTree();
        double p1 = bool52414_3.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //1号油泵已运行信号正常
    public double getValue52421_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1510", "3424", "5338", "7252"}; //四个机组的相关测点ID
        BoolTree bool52421_1 = new BoolTree();
        double p1 = bool52421_1.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //2号油泵已运行信号正常
    public double getValue52421_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1514", "3428", "5342", "7256"}; //四个机组的相关测点ID
        BoolTree bool52421_2 = new BoolTree();
        double p1 = bool52421_2.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //3号油泵已运行信号正常
    public double getValue52421_3(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1544", "3458", "5372", "7286"}; //四个机组的相关测点ID
        BoolTree bool52421_3 = new BoolTree();
        double p1 = bool52421_3.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //1号油泵启动命令异常
    public double getValue52431_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1551", "3465", "5379", "7293"}; //四个机组的相关测点ID
        BoolTree bool52411_1 = new BoolTree();
        double p1 = bool52411_1.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //2号油泵启动命令异常
    public double getValue52431_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1554", "3468", "5382", "7296"}; //四个机组的相关测点ID
        BoolTree bool52411_2 = new BoolTree();
        double p1 = bool52411_2.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //3号油泵启动命令异常
    public double getValue52431_3(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1557", "3471", "5385", "7299"}; //四个机组的相关测点ID
        BoolTree bool52411_3 = new BoolTree();
        double p1 = bool52411_3.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //压力油罐油压过低
    public double getValue52432(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"274", "2188", "4102", "6016"}; //四个机组的相关测点ID
        BoolTree bool52432 = new BoolTree();
        double p1 = bool52432.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //回油箱油混水报警
    public double getValue4311(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"312", "2226", "4140", "6054"}; //四个机组的相关测点ID
        BoolTree bool4311 = new BoolTree();
        double p1 = bool4311.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //回油箱液位过低
    public double getValue54111(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"1535", "3449", "5363", "7277"}; //四个机组的相关测点ID
        BoolTree bool54111 = new BoolTree();
        double p1 = bool54111.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //调速器漏油箱油位过高
    public double getValue351(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"686", "2600", "4514", "6428"}; //四个机组的相关测点ID
        BoolTree bool351 = new BoolTree();
        double p1 = bool351.BooleanTree(measureID[unitID], time);
        return p1;
    }

    //调速器漏油箱油泵运行信号异常
    public double getValue352(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"688", "2602", "4516", "6430"}; //四个机组的相关测点ID
        BoolTree bool352 = new BoolTree();
        double p1 = bool352.BooleanTree(measureID[unitID], time);
        return p1;
    }
////////空压机系统故障
    //中压空压机停止时长变短
 public double getValue4_4111(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"8163","8163","8163","8163"}; //四个机组的相关测点ID
        BoolTree bool4_4111= new BoolTree();
        double p1 = bool4_4111.BooleanTree(measureID[unitID], time);
       return p1;
    }
  //中压空压机停止时长不变
 public double getValue4_4121(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"8163","8163","8163","8163"}; //四个机组的相关测点ID
        BoolTree bool4_4121= new BoolTree();
        double p1 = bool4_4121.BooleanTree(measureID[unitID], time);
      return p1;
    }
//1号中压空压机运行时间增加
 public double getValue4_4122_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"7768","7768","7768","7768"}; //四个机组的相关测点ID
        BoolTree bool4_4122_1= new BoolTree();
        double p1 = bool4_4122_1.BooleanTree(measureID[unitID], time);
       return p1;
    }
//2号中压空压机运行时间增加
 public double getValue4_4122_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"7771","7771","7771","7771"}; //四个机组的相关测点ID
        BoolTree bool4_4122_2= new BoolTree();
        double p1 = bool4_4122_2.BooleanTree(measureID[unitID], time);
       return p1;
    }
//3号中压空压机运行时间增加
 public double getValue4_4122_3(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"7774","7774","7774","7774"}; //四个机组的相关测点ID
        BoolTree bool4_4122_3= new BoolTree();
        double p1 = bool4_4122_3.BooleanTree(measureID[unitID], time);
      return p1;
    }
//4号中压空压机运行时间增加
 public double getValue4_4122_4(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"7777","7777","7777","7777"}; //四个机组的相关测点ID
        BoolTree bool4_4122_4= new BoolTree();
        double p1 = bool4_4122_4.BooleanTree(measureID[unitID], time);
      return p1;
    }
//5号中压空压机运行时间增加
 public double getValue4_4122_5(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"7780","7780","7780","7780"}; //四个机组的相关测点ID
        BoolTree bool4_4122_5= new BoolTree();
        double p1 = bool4_4122_5.BooleanTree(measureID[unitID], time);
      return p1;
    }
//1号低压空压机停止时长变短
 public double getValue4_4211_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"8259","8259","8259","8259"}; //四个机组的相关测点ID
        BoolTree bool4_4211_1= new BoolTree();
        double p1 = bool4_4211_1.BooleanTree(measureID[unitID], time);
      return p1;
    }
//2号低压空压机停止时长变短
 public double getValue4_4211_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"8256","8256","8256","8256"}; //四个机组的相关测点ID
        BoolTree bool4_4211_2= new BoolTree();
        double p1 = bool4_4211_2.BooleanTree(measureID[unitID], time);
      return p1;
    }
//3号低压空压机停止时长变短
 public double getValue4_4211_3(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"8256","8256","8256","8256"}; //四个机组的相关测点ID
        BoolTree bool4_4211_3= new BoolTree();
        double p1 = bool4_4211_3.BooleanTree(measureID[unitID], time);
       return p1;
    }
//1号低压空压机停止时长不变
 public double getValue4_4221_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"8259","8259","8259","8259"}; //四个机组的相关测点ID
        BoolTree bool4_4221_1= new BoolTree();
        double p1 = bool4_4221_1.BooleanTree(measureID[unitID], time);
       return p1;
    }
//2号低压空压机停止时长不变
 public double getValue4_4221_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"8256","8256","8256","8256"}; //四个机组的相关测点ID
        BoolTree bool4_4221_2= new BoolTree();
        double p1 = bool4_4221_2.BooleanTree(measureID[unitID], time);
       return p1;
    }
//3号低压空压机停止时长不变
 public double getValue4_4221_3(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"8256","8256","8256","8256"}; //四个机组的相关测点ID
        BoolTree bool4_4221_3= new BoolTree();
        double p1 = bool4_4221_3.BooleanTree(measureID[unitID], time);
       return p1;
    }
//1号低压空压机运行时间增加
 public double getValue4_4222_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"7790","7790","7790","7790"}; //四个机组的相关测点ID
        BoolTree bool4_4222_1= new BoolTree();
        double p1 = bool4_4222_1.BooleanTree(measureID[unitID], time);
       return p1;
    }
//2号低压空压机运行时间增加
 public double getValue4_4222_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"7793","7793","7793","7793"}; //四个机组的相关测点ID
        BoolTree bool4_4222_2= new BoolTree();
        double p1 = bool4_4222_2.BooleanTree(measureID[unitID], time);
     return p1;
    }
//3号低压空压机运行时间增加
 public double getValue4_4222_3(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"7796","7796","7796","7796"}; //四个机组的相关测点ID
        BoolTree bool4_4222_3= new BoolTree();
        double p1 = bool4_4222_3.BooleanTree(measureID[unitID], time);
       return p1;
    }

    ///////顶盖排水系统故障
    //1号排水泵停泵时长缩短
    public double getValue5_4111_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"653","2567","4481","6395"}; //四个机组的相关测点ID
        BoolTree bool5_4111_1= new BoolTree();
        double p1 =bool5_4111_1.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //2号排水泵停泵时长缩短
    public double getValue5_4111_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"654","2568","4482","6396"}; //四个机组的相关测点ID
        BoolTree bool5_4111_2= new BoolTree();
        double p1 =bool5_4111_2.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //1号排水泵停泵时长过短
    public double getValue5_4121_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"653","2567","4481","6395"}; //四个机组的相关测点ID
        BoolTree bool5_4121_1= new BoolTree();
        double p1 =bool5_4121_1.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //2号排水泵停泵时长缩短
    public double getValue5_4121_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"654","2568","4482","6396"}; //四个机组的相关测点ID
        BoolTree bool5_4121_2= new BoolTree();
        double p1 =bool5_4121_2.BooleanTree(measureID[unitID], time);
        return p1;
    }
    //1号顶盖排水泵故障
    public double getValue5_4211_1(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"655","2569","4483","6397"}; //四个机组的相关测点ID
        BoolTree bool5_4211_1= new BoolTree();
        double p1 =bool5_4211_1.BooleanTree(measureID[unitID], time);
       return p1;
    }
    //2号顶盖排水泵故障
    public double getValue5_4211_2(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"656","2570","4484","6398"}; //四个机组的相关测点ID
        BoolTree bool5_4211_2= new BoolTree();
        double p1 =bool5_4211_2.BooleanTree(measureID[unitID], time);
        return p1;
    }



}
