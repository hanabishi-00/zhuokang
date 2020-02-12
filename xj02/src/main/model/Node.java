package main.model;
import java.util.ArrayList;

public class Node {
    String Id;
    String name;
    ArrayList<String> children = new ArrayList<String>();
    String gate;
    double freq;
    String father;

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
}
