package main.model;
import java.util.ArrayList;

public class Node {
    String Id;
    String name;
    ArrayList<String> children = new ArrayList<String>();
    String gate;
    double freq;
    String father;
    ArrayList<ArrayList<String>> monitorid = new ArrayList<>();     //叶节点对应的测点
    String judgment;                //判据类型
    ArrayList<ArrayList<String>> threshold = new ArrayList<>();     //阈值


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId(){return Id;}
    public void setId(String Id){this.Id = Id;}
    public ArrayList<String> getChildren() {
        return children;
    }
    public void setChildren(ArrayList<String> children) {
        this.children = children;
    }
    public String getGate() {
        return gate;
    }
    public void setGate(String gate) {
        this.gate = gate;
    }
    public String getFather() {
        return father;
    }
    public void setFather(String father) {
        this.father = father;
    }
    public float getX() {
        return (float) freq;
    }
    public void setX(double freq) {
        this.freq = freq;
    }
    public void setFreq(Double freq) {
        this.freq = freq;
    }
    public Double getFreq() {
        return freq;
    }
    public ArrayList<ArrayList<String>> getMonitorid() {
        return monitorid;
    }
    public void setMonitorid(ArrayList<ArrayList<String>> monitorid) {
        this.monitorid = monitorid;
    }
    public ArrayList<ArrayList<String>> getThreshold() {
        return threshold;
    }
    public void setThreshold(ArrayList<ArrayList<String>> threshold) {
        this.threshold = threshold;
    }
    public String getJudgment() {
        return judgment;
    }
    public void setJudgment(String judgment) {
        this.judgment = judgment;
    }


}
