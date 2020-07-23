package main.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import main.util.DataFloatUtils;
import main.dao.post_request;

public class FloatTree {

    int timeint1=3600;
    int timeint=timeint1*3;



    //计算方差，当没有阈值给定时，采用3σ原则进行判断
    public static ArrayList<Double> StandardDiv(ArrayList<Float> x){
        int m=x.size();
        ArrayList<Double> result = new ArrayList<>();
        double sum=0;
        for(int i=0;i<m;i++){
            sum+=x.get(i);
        }
        double dAve=sum/m;
        double dVar=0;
        for(int i=0;i<m;i++){
            dVar+=(x.get(i)-dAve)*(x.get(i)-dAve);
        }
        result.add(dAve);
        result.add(Math.sqrt(dVar/m));
        return result;
    }



    //  模拟量读取
//    public double FloateanTree(String table, String id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double FloateanTree(String id, long time, String Llimite, String Hlimite) throws ClassNotFoundException, SQLException, ParseException {
        DataFloatUtils data = post_request.queFloat(id, time);
        if(data.getValue().size()==0){
            return 0.0;
        }
        ArrayList<Float> value = data.getValue();
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        if((Hlimite.equals("null")) && (Llimite.equals("null"))){
            DataFloatUtils data1 = post_request.queFloat(id, time, timeint);
            ArrayList<Float> value1 = data1.getValue();
            ArrayList<Double> par = StandardDiv(value1);
            for (Float d : value) {
                if(d<=par.get(0)+3*par.get(1) && d>=par.get(0)-3*par.get(1)){
                    y+=1;
                }else if(d>=par.get(0)+3*par.get(1) || d<=par.get(0)-3*par.get(1)){
                    x+=1;
                }
            }
        }

        for (Float d : value) {
            if (!(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)) {
                y += 1;
            }else if((Hlimite.equals("null")) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)){
                y += 1;
            }else if((Llimite.equals("null")) && !(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite)){
                y += 1;
            }
            else if (!(Llimite.equals("null")) && d <= Double.parseDouble(Llimite)) {
                x += 1;
            } else if (!(Hlimite.equals("null")) && d >= Double.parseDouble(Hlimite)) {
                x += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = ((double)x/(x+y));
        }
        if(p<=0.0001){
            p=0.0;
        }
        return p;
    }
    //得到模拟量，得到趋势值
//    public double TrendTree(String table, String id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double TrendTree(String id, long time, String Llimite, String Hlimite){
        DataFloatUtils data = post_request.queFloat(id, time);
        if(data.getValue().size()<=1){
            return 0.0;
        }
        ArrayList<Float> value = data.getValue();
        ArrayList<Long> time1 = data.getTime();  //此处time1的数据类型为Long
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        if((Hlimite.equals("null")) && (Llimite.equals("null"))){
            ArrayList<Float> value2 = new ArrayList<>();
            for (int i=0;i<value.size()-1;i++) {
                value2.add((value.get(i+1)-value.get(i))/(time1.get(i+1)-time1.get(i)));
            }
            DataFloatUtils data1 = post_request.queFloat(id, time, timeint);
            ArrayList<Float> value3 = new ArrayList<>();
            ArrayList<Float> value1 = data1.getValue();
            ArrayList<Long> time2 = data1.getTime();
            for (int i=0;i<value1.size()-1;i++) {
                value3.add((value1.get(i+1)-value1.get(i))/(time2.get(i+1)-time2.get(i)));
            }
            ArrayList<Double> par = StandardDiv(value3);
            for (Float d : value2) {
                if(d<=par.get(0)+3*par.get(1) && d>=par.get(0)-3*par.get(1)){
                    y+=1;
                }else if(d>=par.get(0)+3*par.get(1) || d<=par.get(0)-3*par.get(1)){
                    x+=1;
                }
            }
        }

        for (int i=0;i<value.size()-1;i++) {
            double d = (value.get(i+1)-value.get(i))/(double)(time1.get(i+1)-time1.get(i));
            if (!(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)) {
                y += 1;
            }else if((Hlimite.equals("null")) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)){
                y += 1;
            }else if((Llimite.equals("null")) && !(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite)){
                y += 1;
            }
            else if (!(Llimite.equals("null")) && d <= Double.parseDouble(Llimite)) {
                x += 1;
            } else if (!(Hlimite.equals("null")) && d >= Double.parseDouble(Hlimite)) {
                x += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = ((double)x/(x+y));
        }
        if(p<=0.0001){
            p=0.0;
        }
        return p;
    }
    //得到模拟量，得到模拟量速度变化速度的值
    //大于某个值即认为测量量的变化速率过大，小于某个值即认为测量量的变化速率过小
//    public double SpeedTrendTree(String table, String id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double SpeedTrendTree(String id, long time, String Llimite, String Hlimite){
        DataFloatUtils data = post_request.queFloat(id, time);
        if(data.getValue().size()<=2){
            return 0.0;
        }
        ArrayList<Float> value = data.getValue();
        ArrayList<Long> time1 = data.getTime();  //此处time1的数据类型为Long
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        if((Hlimite.equals("null")) && (Llimite.equals("null"))){
            ArrayList<Float> value2 = new ArrayList<>();
            for (int i=0;i<value.size()-2;i++) {
                value2.add((value.get(i+2)-value.get(i+1))/(time1.get(i+2)-time1.get(i+1))-(value.get(i+1)-value.get(i))/(time1.get(i+1)-time1.get(i)));
            }
            DataFloatUtils data1 = post_request.queFloat(id, time, timeint);
            ArrayList<Float> value3 = new ArrayList<>();
            ArrayList<Float> value1 = data1.getValue();
            ArrayList<Long> time2 = data1.getTime();
            for (int i=0;i<value1.size()-2;i++) {
                value3.add((value1.get(i+2)-value1.get(i+1))/(time2.get(i+2)-time2.get(i+1))-(value1.get(i+1)-value1.get(i))/(time2.get(i+1)-time2.get(i)));
            }
            ArrayList<Double> par = StandardDiv(value3);
            for (Float d : value2) {
                if(d<=par.get(0)+3*par.get(1) && d>=par.get(0)-3*par.get(1)){
                    y+=1;
                }else if(d>=par.get(0)+3*par.get(1) || d<=par.get(0)-3*par.get(1)){
                    x+=1;
                }
            }
        }

        for (int i=0;i<value.size()-2;i++) {
            double d1 = (value.get(i+1)-value.get(i))/(double)(time1.get(i+1)-time1.get(i));
            double d2 = (value.get(i+2)-value.get(i+1))/(double)(time1.get(i+2)-time1.get(i+1));
            double d = d2 - d1;                            //d为测点值变化的变化趋势
            if (!(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)) {
                y += 1;
            }else if((Hlimite.equals("null")) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)){
                y += 1;
            }else if((Llimite.equals("null")) && !(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite)){
                y += 1;
            }
            else if (!(Llimite.equals("null")) && d <= Double.parseDouble(Llimite)) {
                x += 1;
            } else if (!(Hlimite.equals("null")) && d >= Double.parseDouble(Hlimite)) {
                x += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = ((double)x/(x+y));
        }
        if(p<=0.0001){
            p=0.0;
        }
        return p;
    }
    //  多模拟量读取并求和
//    public double SumFloateanTree(String table, ArrayList<String> id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double SumFloateanTree(ArrayList<String> id, long time, String Llimite, String Hlimite){
        ArrayList<Float> d1 = new ArrayList<Float>();
        ArrayList<Long> d2 = new ArrayList<>();
        for (int i = 0; i<id.size();i++){
            DataFloatUtils data = post_request.queFloat(id.get(i), time);
            if(data.getValue().size()<=0){
                return 0.0;
            }
            ArrayList<Float> value = data.getValue();
            ArrayList<Long> t1=data.getTime();

            if (i == 0) {
                for (int j = 0; j < value.size(); j++) {
                    d1.add(value.get(j));
                    d2.add(t1.get(j));
                }
            }
            else  {
                ArrayList<Float> d3=new ArrayList<>();
                ArrayList<Long> d4=new ArrayList<>();
                for (int j = 0; j < value.size(); j++) {
                    for (int k = 0; k < d1.size(); k++) {
                        if(t1.get(j).equals(d2.get(k))) {
                            d3.add(value.get(j) + d1.get(k));
                            d4.add(d2.get(k));
                            break;
                        }
                    }
                }
                d1= (ArrayList<Float>) d3.clone();
                d2= (ArrayList<Long>) d4.clone();
            }

        }
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        if((Hlimite.equals("null")) && (Llimite.equals("null"))){
            ArrayList<Float> value3 = new ArrayList<>();
            ArrayList<Long> time3 = new ArrayList<>();
            for (int i = 0; i<id.size();i++){
                DataFloatUtils data = post_request.queFloat(id.get(i), time, timeint);
                if(data.getValue().size()<=0){
                    return 0.0;
                }
                ArrayList<Float> value = data.getValue();
                ArrayList<Long> t1=data.getTime();
                if (i == 0) {
                    for (int j = 0; j < value.size(); j++) {
                        value3.add(value.get(j));
                        time3.add(t1.get(j));
                    }
                }
                else  {
                    ArrayList<Float> d3=new ArrayList<>();
                    ArrayList<Long> d4=new ArrayList<>();
                    for (int j = 0; j < value.size(); j++) {
                        for (int k = 0; k < value3.size(); k++) {
                            if(t1.get(j).equals(time3.get(k))) {
                                d3.add(value.get(j) + value3.get(k));
                                d4.add(time3.get(k));
                                break;
                            }
                        }
                    }
                    value3= (ArrayList<Float>) d3.clone();
                    time3= (ArrayList<Long>) d4.clone();
                }
            }

            ArrayList<Double> par = StandardDiv(value3);
            for (Float d : d1) {
                if(d<=par.get(0)+3*par.get(1) && d>=par.get(0)-3*par.get(1)){
                    x+=1;
                }else if(d>=par.get(0)+3*par.get(1) || d<=par.get(0)-3*par.get(1)){
                    y+=1;
                }
            }
        }

        for (Float d : d1) {
            if (!(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)) {
                x += 1;
            }else if((Hlimite.equals("null")) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)){
                x += 1;
            }else if((Llimite.equals("null")) && !(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite)){
                x += 1;
            }
            else if (!(Llimite.equals("null")) && d <= Double.parseDouble(Llimite)) {
                y += 1;
            } else if (!(Hlimite.equals("null")) && d >= Double.parseDouble(Hlimite)) {
                y += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = ((double)x/(x+y));
        }
        if(p<=0.0001){
            p=0.0;
        }
        return p;
    }


    //    public double FloateanTree(String table, String id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double FloateanTree1(String id, long time, String Llimite, String Hlimite,int timeinter) throws ClassNotFoundException, SQLException, ParseException {
        DataFloatUtils data = post_request.queFloat(id, time,timeinter);
        if(data.getValue().size()==0){
            return 0.0;
        }
        ArrayList<Float> value = data.getValue();
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        if((Hlimite.equals("null")) && (Llimite.equals("null"))){
            DataFloatUtils data1 = post_request.queFloat(id, time, 3*timeinter);
            ArrayList<Float> value1 = data1.getValue();
            ArrayList<Double> par = StandardDiv(value1);
            for (Float d : value) {
                if(d<=par.get(0)+3*par.get(1) && d>=par.get(0)-3*par.get(1)){
                    y+=1;
                }else if(d>=par.get(0)+3*par.get(1) || d<=par.get(0)-3*par.get(1)){
                    x+=1;
                }
            }
        }

        for (Float d : value) {
            if (!(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)) {
                y += 1;
            }else if((Hlimite.equals("null")) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)){
                y += 1;
            }else if((Llimite.equals("null")) && !(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite)){
                y += 1;
            }
            else if (!(Llimite.equals("null")) && d <= Double.parseDouble(Llimite)) {
                x += 1;
            } else if (!(Hlimite.equals("null")) && d >= Double.parseDouble(Hlimite)) {
                x += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = ((double)x/(x+y));
        }
        if(p<=0.0001){
            p=0.0;
        }
        return p;
    }
    //得到模拟量，得到趋势值
//    public double TrendTree(String table, String id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double TrendTree1(String id, long time, String Llimite, String Hlimite,int timeinter){
        DataFloatUtils data = post_request.queFloat(id, time,timeinter);
        if(data.getValue().size()<=1){
            return 0.0;
        }
        ArrayList<Float> value = data.getValue();
        ArrayList<Long> time1 = data.getTime();  //此处time1的数据类型为Long
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        if((Hlimite.equals("null")) && (Llimite.equals("null"))){
            ArrayList<Float> value2 = new ArrayList<>();
            for (int i=0;i<value.size()-1;i++) {
                value2.add((value.get(i+1)-value.get(i))/(time1.get(i+1)-time1.get(i)));
            }
            DataFloatUtils data1 = post_request.queFloat(id, time, 3*timeinter);
            ArrayList<Float> value3 = new ArrayList<>();
            ArrayList<Float> value1 = data1.getValue();
            ArrayList<Long> time2 = data1.getTime();
            for (int i=0;i<value1.size()-1;i++) {
                value3.add((value1.get(i+1)-value1.get(i))/(time2.get(i+1)-time2.get(i)));
            }
            ArrayList<Double> par = StandardDiv(value3);
            for (Float d : value2) {
                if(d<=par.get(0)+3*par.get(1) && d>=par.get(0)-3*par.get(1)){
                    y+=1;
                }else if(d>=par.get(0)+3*par.get(1) || d<=par.get(0)-3*par.get(1)){
                    x+=1;
                }
            }
        }

        for (int i=0;i<value.size()-1;i++) {
            double d = (value.get(i+1)-value.get(i))/(double)(time1.get(i+1)-time1.get(i));
            if (!(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)) {
                y += 1;
            }else if((Hlimite.equals("null")) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)){
                y += 1;
            }else if((Llimite.equals("null")) && !(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite)){
                y += 1;
            }
            else if (!(Llimite.equals("null")) && d <= Double.parseDouble(Llimite)) {
                x += 1;
            } else if (!(Hlimite.equals("null")) && d >= Double.parseDouble(Hlimite)) {
                x += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = ((double)x/(x+y));
        }
        if(p<=0.0001){
            p=0.0;
        }
        return p;
    }
    //得到模拟量，得到模拟量速度变化速度的值
    //大于某个值即认为测量量的变化速率过大，小于某个值即认为测量量的变化速率过小
//    public double SpeedTrendTree(String table, String id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double SpeedTrendTree1(String id, long time, String Llimite, String Hlimite,int timeinter){
        DataFloatUtils data = post_request.queFloat(id, time,timeinter);
        if(data.getValue().size()<=2){
            return 0.0;
        }
        ArrayList<Float> value = data.getValue();
        ArrayList<Long> time1 = data.getTime();  //此处time1的数据类型为Long
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        if((Hlimite.equals("null")) && (Llimite.equals("null"))){
            ArrayList<Float> value2 = new ArrayList<>();
            for (int i=0;i<value.size()-2;i++) {
                value2.add((value.get(i+2)-value.get(i+1))/(time1.get(i+2)-time1.get(i+1))-(value.get(i+1)-value.get(i))/(time1.get(i+1)-time1.get(i)));
            }
            DataFloatUtils data1 = post_request.queFloat(id, time, 3*timeinter);
            ArrayList<Float> value3 = new ArrayList<>();
            ArrayList<Float> value1 = data1.getValue();
            ArrayList<Long> time2 = data1.getTime();
            for (int i=0;i<value1.size()-2;i++) {
                value3.add((value1.get(i+2)-value1.get(i+1))/(time2.get(i+2)-time2.get(i+1))-(value1.get(i+1)-value1.get(i))/(time2.get(i+1)-time2.get(i)));
            }
            ArrayList<Double> par = StandardDiv(value3);
            for (Float d : value2) {
                if(d<=par.get(0)+3*par.get(1) && d>=par.get(0)-3*par.get(1)){
                    y+=1;
                }else if(d>=par.get(0)+3*par.get(1) || d<=par.get(0)-3*par.get(1)){
                    x+=1;
                }
            }
        }

        for (int i=0;i<value.size()-2;i++) {
            double d1 = (value.get(i+1)-value.get(i))/(double)(time1.get(i+1)-time1.get(i));
            double d2 = (value.get(i+2)-value.get(i+1))/(double)(time1.get(i+2)-time1.get(i+1));
            double d = d2 - d1;                            //d为测点值变化的变化趋势
            if (!(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)) {
                y += 1;
            }else if((Hlimite.equals("null")) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)){
                y += 1;
            }else if((Llimite.equals("null")) && !(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite)){
                y += 1;
            }
            else if (!(Llimite.equals("null")) && d <= Double.parseDouble(Llimite)) {
                x += 1;
            } else if (!(Hlimite.equals("null")) && d >= Double.parseDouble(Hlimite)) {
                x += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = ((double)x/(x+y));
        }
        if(p<=0.0001){
            p=0.0;
        }
        return p;
    }
    //  多模拟量读取并求和
//    public double SumFloateanTree(String table, ArrayList<String> id, long time, double Hlimite, double Llimite)throws ClassNotFoundException, SQLException {
    public double SumFloateanTree1(ArrayList<String> id, long time, String Llimite, String Hlimite,int timeinter){
        ArrayList<Float> d1 = new ArrayList<Float>();
        ArrayList<Long> d2 = new ArrayList<>();
        for (int i = 0; i<id.size();i++){
            DataFloatUtils data = post_request.queFloat(id.get(i), time,timeinter);
            if(data.getValue().size()<=0){
                return 0.0;
            }
            ArrayList<Float> value = data.getValue();
            ArrayList<Long> t1=data.getTime();

            if (i == 0) {
                for (int j = 0; j < value.size(); j++) {
                    d1.add(value.get(j));
                    d2.add(t1.get(j));
                }
            }
            else  {
                ArrayList<Float> d3=new ArrayList<>();
                ArrayList<Long> d4=new ArrayList<>();
                for (int j = 0; j < value.size(); j++) {
                    for (int k = 0; k < d1.size(); k++) {
                        if(t1.get(j).equals(d2.get(k))) {
                            d3.add(value.get(j) + d1.get(k));
                            d4.add(d2.get(k));
                            break;
                        }
                    }
                }
                d1= (ArrayList<Float>) d3.clone();
                d2= (ArrayList<Long>) d4.clone();
            }

        }
        double p = 0;
        int x = 0; //x表示越限的次数
        int y = 0; //y表示不越限的次数

        if((Hlimite.equals("null")) && (Llimite.equals("null"))){
            ArrayList<Float> value3 = new ArrayList<>();
            ArrayList<Long> time3 = new ArrayList<>();
            for (int i = 0; i<id.size();i++){
                DataFloatUtils data = post_request.queFloat(id.get(i), time, 3*timeinter);
                if(data.getValue().size()<=0){
                    return 0.0;
                }
                ArrayList<Float> value = data.getValue();
                ArrayList<Long> t1=data.getTime();
                if (i == 0) {
                    for (int j = 0; j < value.size(); j++) {
                        value3.add(value.get(j));
                        time3.add(t1.get(j));
                    }
                }
                else  {
                    ArrayList<Float> d3=new ArrayList<>();
                    ArrayList<Long> d4=new ArrayList<>();
                    for (int j = 0; j < value.size(); j++) {
                        for (int k = 0; k < value3.size(); k++) {
                            if(t1.get(j).equals(time3.get(k))) {
                                d3.add(value.get(j) + value3.get(k));
                                d4.add(time3.get(k));
                                break;
                            }
                        }
                    }
                    value3= (ArrayList<Float>) d3.clone();
                    time3= (ArrayList<Long>) d4.clone();
                }
            }

            ArrayList<Double> par = StandardDiv(value3);
            for (Float d : d1) {
                if(d<=par.get(0)+3*par.get(1) && d>=par.get(0)-3*par.get(1)){
                    x+=1;
                }else if(d>=par.get(0)+3*par.get(1) || d<=par.get(0)-3*par.get(1)){
                    y+=1;
                }
            }
        }

        for (Float d : d1) {
            if (!(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)) {
                x += 1;
            }else if((Hlimite.equals("null")) && !(Llimite.equals("null")) && d >= Double.parseDouble(Llimite)){
                x += 1;
            }else if((Llimite.equals("null")) && !(Hlimite.equals("null")) && d <= Double.parseDouble(Hlimite)){
                x += 1;
            }
            else if (!(Llimite.equals("null")) && d <= Double.parseDouble(Llimite)) {
                y += 1;
            } else if (!(Hlimite.equals("null")) && d >= Double.parseDouble(Hlimite)) {
                y += 1;
            }
        }
        if (x + y == 0) {
            p = 0;
        }else{
            p = ((double)x/(x+y));
        }
        if(p<=0.0001){
            p=0.0;
        }
        return p;
    }
}
