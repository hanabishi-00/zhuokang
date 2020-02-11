package main.service;

import java.awt.geom.CubicCurve2D;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import main.dao.DaoTree;
import main.util.DataFloatUtils;

public class FloatTree {
    //  模拟量读取
//    public double FloateanTree(String table, String id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double FloateanTree(String id, long time, double Hlimite, double Llimite) throws ClassNotFoundException, SQLException, ParseException {
        DaoTree as = new DaoTree();
//        DataUtils data = as.queFloat(table, id, time);
        DataFloatUtils data = as.queFloat(id, time);
        double C2 = Hlimite; //高报警
        double C1 = Llimite; //低报警
        ArrayList<Float> value = data.getValue();
//        System.out.println(value.size());
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        for (Float d : value) {
            if (C2 != 0 && d <= C2 && C1 != 0 && d >= C1) {
                y += 1;
            } else if (C1 != 0 && d <= C1) {
                x += 1;
            } else if (C2 != 0 && d >= C2) {
                x += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = (double)(x/(x+y));
        }
        return p;
    }
    //得到模拟量，得到趋势值
//    public double TrendTree(String table, String id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double TrendTree(String id, long time, double Hlimite, double Llimite) throws ClassNotFoundException, SQLException, ParseException {
        DaoTree as = new DaoTree();
//        DataUtils data = as.queFloat(table, id, time);
        DataFloatUtils data = as.queFloat(id, time);
        double C2 = Hlimite; //高报警
        double C1 = Llimite; //低报警
        ArrayList<Float> value = data.getValue();
        ArrayList<Long> time1 = data.getTime();  //此处time1的数据类型为Long
//        System.out.println(value.size());
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        for (int i=0;i<value.size()-1;i++) {
            double d = (value.get(i+1)-value.get(i))/(double)(time1.get(i+1)-time1.get(i));
            if (C2 != 0 && d <= C2 && C1 != 0 && d >= C1) {
                y += 1;
            } else if (C1 != 0 && d <= C1) {
                x += 1;
            } else if (C2 != 0 && d >= C2) {
                x += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = (double)(x/(x+y));
        }
        return p;
    }
    //得到模拟量，得到模拟量变化速度的值
    //大于某个值即认为测量量的变化速率过大，小于某个值即认为测量量的变化速率过小
//    public double SpeedTrendTree(String table, String id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double SpeedTrendTree(String id, long time, double Hlimite, double Llimite) throws ClassNotFoundException, SQLException, ParseException {
        DaoTree as = new DaoTree();
//        DataUtils data = as.queFloat(table, id, time);
        DataFloatUtils data = as.queFloat(id, time);
        double C2 = Hlimite; //高报警
        double C1 = Llimite; //低报警
        ArrayList<Float> value = data.getValue();
        ArrayList<Long> time1 = data.getTime();  //此处time1的数据类型为Long
//        System.out.println(value.size());
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        for (int i=0;i<value.size()-2;i++) {
            double d1 = Math.abs((value.get(i+1)-value.get(i))/(double)(time1.get(i+1)-time1.get(i)));
            double d2 = Math.abs((value.get(i+2)-value.get(i+1))/(double)(time1.get(i+2)-time1.get(i+1)));
            double d = d2 - d1;                            //d为测点值变化的变化趋势
            if (C2 != 0 && d <= C2 && C1 != 0 && d >= C1) {
                y += 1;
            } else if (C1 != 0 && d <= C1) {
                x += 1;
            } else if (C2 != 0 && d >= C2) {
                x += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = (double)(x/(x+y));
        }
        return p;
    }
    //  多模拟量读取并求和
//    public double SumFloateanTree(String table, ArrayList<String> id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double SumFloateanTree(ArrayList<String> id, long time, double Hlimite, double Llimite) throws ClassNotFoundException, SQLException, ParseException {
        DaoTree as = new DaoTree();
//        ArrayList<Double> d = new ArrayList<Double>(table.size());
        ArrayList<Float> d1 = new ArrayList<Float>();
//        d.clear();
        for (int i = 0; i<id.size();i++){
//            DataUtils data = as.queFloat(table, id.get(i), time);
            DataFloatUtils data = as.queFloat(id.get(i), time);
            ArrayList<Float> value = data.getValue();

            if (i == 0) {

                for (int j = 0; j < value.size(); j++) {
                    d1.add(value.get(j));
                }
            }
            else  {
                for (int j = 0; j < value.size(); j++) {
                    d1.set(j, value.get(j)+d1.get(j));
                }
            }

        }
//        DataUtils data = as.queFloat(table, id, time);
        double C2 = Hlimite; //高报警
        double C1 = Llimite; //低报警
//        ArrayList<Double> value = data.getValue();
//        System.out.println(value.size());
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        for (Float d : d1) {
            if (C2 != 0 && d <= C2 && C1 != 0 && d >= C1) {
                y += 1;
            } else if (C1 != 0 && d <= C1) {
                x += 1;
            } else if (C2 != 0 && d >= C2) {
                x += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = (double)(x/(x+y));
        }
        return p;
    }
}
