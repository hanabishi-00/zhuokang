package main.service;

import main.dao.post_request;
import main.util.DataBoolUtils;
import main.util.DataFloatUtils;

import java.util.ArrayList;

public class SpecialTree {
    public double SpecialTree2(ArrayList<String> id, long time, String Hlimit){
        double p=0.0;
        double hlimit=0.0;
        ArrayList<Integer> value1 = new ArrayList<>();
        ArrayList<Long> time1 = new ArrayList<>();
        DataBoolUtils data1 = post_request.queBool(id.get(0),time);
        value1 = data1.getValue();
        time1 = data1.getTime();
        int x=0;
        int y=0;
        long stime=0;
        long etime=0;
        for(int i=0;i<value1.size();i++){
            if(value1.get(i)==1){
                stime=time1.get(i);
                for(int j=i+1;j<value1.size();j++){
                    if(value1.get(j)==0){
                        etime = time1.get(j);
                        DataFloatUtils data2 = post_request.queFloat(id.get(1),etime,etime-stime);
                        ArrayList<Float> value2 = data2.getValue();
                        if(value2.size()==0){
                            value2.add((float) 0);
                        }
//                        ArrayList<Long> time2 = data2.getTime();
                        ArrayList<Double> pra = FloatTree.StandardDiv(value2);
                        if(!Hlimit.equals("null")){
                            hlimit= Double.parseDouble(Hlimit);
                        }else{
                            hlimit= pra.get(0)+3*pra.get(1);
                        }
                        int num=0;
                        for(float v2:value2){
                            if(v2>hlimit){
                                num+=1;
                            }
                        }
                        if(num>5){
                            x+=1;
                        }else{y+=1;}
                    }
                    i=j;
                    break;
                }
            }
        }
        if (x + y == 0) {
            p=0.0;
        }else{
            p=((double)x/(x+y));
        }
        return p;
    }

    public double SpecialTree3(String id, long time){
        double p=0.0;
        DataBoolUtils data = post_request.queBool(id,time);
        ArrayList<Integer> value1 = data.getValue();
        ArrayList<Long> time1 = data.getTime();
        int num=0;
        for(int I1:value1){
            if(I1==1){
                num+=1;
            }
        }
        if(num>5 && num<10){
            p=0.5;
        }else if(num>=10){
            p=0.99;
        }
        return p;
    }


    public double SpecialTree21(ArrayList<String> id, long time, String Hlimit, int timeinter){
        double p=0.0;
        double hlimit=0.0;
        ArrayList<Integer> value1 = new ArrayList<>();
        ArrayList<Long> time1 = new ArrayList<>();
        DataBoolUtils data1 = post_request.queBool(id.get(0),time,timeinter);
        value1 = data1.getValue();
        time1 = data1.getTime();
        int x=0;
        int y=0;
        long stime=0;
        long etime=0;
        for(int i=0;i<value1.size();i++){
            if(value1.get(i)==1){
                stime=time1.get(i);
                for(int j=i+1;j<value1.size();j++){
                    if(value1.get(j)==0){
                        etime = time1.get(j);
                        DataFloatUtils data2 = post_request.queFloat(id.get(1),etime,etime-stime);
                        ArrayList<Float> value2 = data2.getValue();
                        if(value2.size()==0){
                            value2.add((float) 0);
                        }
//                        ArrayList<Long> time2 = data2.getTime();
                        ArrayList<Double> pra = FloatTree.StandardDiv(value2);
                        if(!Hlimit.equals("null")){
                            hlimit= Double.parseDouble(Hlimit);
                        }else{
                            hlimit= pra.get(0)+3*pra.get(1);
                        }
                        int num=0;
                        for(float v2:value2){
                            if(v2>hlimit){
                                num+=1;
                            }
                        }
                        if(num>5){
                            x+=1;
                        }else{y+=1;}
                    }
                    i=j;
                    break;
                }
            }
        }
        if (x + y == 0) {
            p=0.0;
        }else{
            p=((double)x/(x+y));
        }
        return p;
    }

    public double SpecialTree31(String id, long time, int timeinter){
        double p=0.0;
        DataBoolUtils data = post_request.queBool(id,time,timeinter);
        ArrayList<Integer> value1 = data.getValue();
        ArrayList<Long> time1 = data.getTime();
        int num=0;
        for(int I1:value1){
            if(I1==1){
                num+=1;
            }
        }
        if(num>5 && num<10){
            p=0.5;
        }else if(num>=10){
            p=0.99;
        }
        return p;
    }
}
