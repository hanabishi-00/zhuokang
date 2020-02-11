/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package main.util;

import java.util.ArrayList;

/**
 * @author LENOVO
 * @date 2020/1/2 16:23
 */
public class DataBoolUtils {
    private ArrayList<Long> time;
    private ArrayList<Integer> value;
    private ArrayList<Integer> flag;
    private String id;


    public DataBoolUtils(ArrayList<Long> time, ArrayList<Integer> value, String id, ArrayList<Integer> flag){
        this.setTime(time);
        this.setValue(value);
        this.setId(id);
        this.setFlag(flag);
    }
    public DataBoolUtils(String id){
        this.setTime(new ArrayList<Long>());
        this.setValue(new ArrayList<Integer>());
        this.setFlag(new ArrayList<Integer>());
        this.setId(id);
    }


    public void addTime(Long x){
        this.getTime().add(x);
    }

    public void addValue(Integer y){
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

    public ArrayList<Integer> getValue() {
        return value;
    }

    public void setValue(ArrayList<Integer> value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}