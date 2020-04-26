package main.util;

import java.util.ArrayList;
/**
 * 数据存放基本类
 * */
public class DataFloatUtils {
    private ArrayList<Long> time;
    private ArrayList<Float> value;
    private ArrayList<Integer> flag;
    private String id;


    public DataFloatUtils(ArrayList<Long> time, ArrayList<Float> value, String id, ArrayList<Integer> flag){
        this.setTime(time);
        this.setValue(value);
        this.setId(id);
        this.setFlag(flag);
    }
    public DataFloatUtils(String id){
        this.setTime(new ArrayList<Long>());
        this.setValue(new ArrayList<Float>());
        this.setFlag(new ArrayList<Integer>());
        this.setId(id);
    }


    public void addTime(Long x){
        this.getTime().add(x);
    }

    public void addValue(Float y){
        this.getValue().add(y);
    }

    public void addflag(Integer z){
        this.getFlag().add(z);
    }

    public ArrayList<Long> getTime() {
        return time;
    }

    public ArrayList<Integer> getFlag() {
        return flag;
    }

    public void setTime(ArrayList<Long> time) {
        this.time = time;
    }

    public void setFlag(ArrayList<Integer> flag) {
        this.flag = flag;
    }

    public ArrayList<Float> getValue() {
        return value;
    }

    public void setValue(ArrayList<Float> value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
