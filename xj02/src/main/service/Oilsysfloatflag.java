package main.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Oilsysfloatflag {
    //压力油罐油压降低
    public int getValue51121(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"403", "1115", "1827", "2539"}; //四个机组的相关测点ID
        FloatTree float51111 = new FloatTree();
//        double p1 = float51111.TrendTree("测点表名", measureID[unitID], time,0,0);
        double p1 = float51111.TrendTree(measureID[unitID], time,0,0);
        int flag = 0;
        if((p1)>0.001){
            flag=1;
        }else {
            flag=0;
        }
        return flag;
    }
    //压力油罐油压增大
    public int getValue51131(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"403", "1115", "1827", "2539"}; //四个机组的相关测点ID
        FloatTree float51111 = new FloatTree();
        double p1 = float51111.TrendTree(measureID[unitID], time,0,0);
        int flag = 0;
        if((p1)>0.001){
            flag=1;
        }else {
            flag=0;
        }
        return flag;
    }

    //压力油罐最高油位上升较快
    public int getValue4121(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"405", "1117", "1829", "2541"}; //四个机组的相关测点ID
        FloatTree float4121 = new FloatTree();
        double p1 = float4121.TrendTree(measureID[unitID], time,0,0);
        int flag = 0;
        if((p1)>0.001){
            flag=1;
        }else {
            flag=0;
        }
        return flag;
    }
    //压力油罐压力降低速度过快
    public int getValue4122(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"403", "1115", "1827", "2539"}; //四个机组的相关测点ID
        FloatTree float4122 = new FloatTree();
        double p1 = float4122.TrendTree(measureID[unitID], time,0,0);
        int flag = 0;
        if((p1)>0.001){
            flag=1;
        }else {
            flag=0;
        }
        return flag;
    }

    //1号油泵绝对打油速率下降
    public int getValue4211(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"405", "1117", "1829", "2541"}; //四个机组的相关测点ID
        FloatTree float4211 = new FloatTree();
        double p1 = float4211.TrendTree(measureID[unitID], time,0,0);
        int flag = 0;
        if((p1)>0.001){
            flag=1;
        }else {
            flag=0;
        }
        return flag;
    }
    //2号油泵绝对打油速率下降
    public int getValue4221(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"405", "1117", "1829", "2541"}; //四个机组的相关测点ID
        FloatTree float4221 = new FloatTree();
        double p1 = float4221.TrendTree(measureID[unitID], time,0,0);
        int flag = 0;
        if((p1)>0.001){
            flag=1;
        }else {
            flag=0;
        }
        return flag;
    }
    //3号油泵绝对打油速率下降
    public int getValue4231(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] = new String[]{"405", "1117", "1829", "2541"}; //四个机组的相关测点ID
        FloatTree float4231 = new FloatTree();
        double p1 = float4231.TrendTree(measureID[unitID], time, 0, 0);
        int flag = 0;
        if((p1)>0.001){
            flag=1;
        }else {
            flag=0;
        }
        return flag;
    }

    //总油量不变
    public int getValue54121(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID1[] =new String[]{"405", "1117", "1829", "2541"}; //四个机组的压力油罐液位测点ID
        String measureID2[] =new String[]{"404", "1116", "1828", "2540"}; //四个机组的回油箱液位测点ID
        ArrayList<String> measureID = new ArrayList<String>(Arrays.asList(measureID1[unitID],measureID2[unitID]));
        FloatTree float54121 = new FloatTree();
        double p1 = float54121.SumFloateanTree(measureID, time,0,0);
        int flag = 0;
        if((p1)>0.001){
            flag=1;
        }else {
            flag=0;
        }
        return flag;
    }

    //回油箱液位最小下降速率变大
    public int getValue54122(long time, int unitID) throws ClassNotFoundException, SQLException, ParseException {
        String measureID[] =new String[]{"404", "1116", "1828", "2540"}; //四个机组的相关测点ID
        FloatTree float54122 = new FloatTree();
        double p1 = float54122.SpeedTrendTree(measureID[unitID], time,0,0);
        int flag = 0;
        if((p1)>0.001){
            flag=1;
        }else {
            flag=0;
        }
        return flag;
    }
}
