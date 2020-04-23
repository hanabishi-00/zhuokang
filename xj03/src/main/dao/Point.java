package main.dao;

public class Point {

    private String id;

    private String unit;

    private Long tm;

    private float value;

    private int flag;

    private int dataType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getTm() {
        return tm;
    }

    public void setTm(Long tm) {
        this.tm = tm;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id='" + id + '\'' +
                ", unit='" + unit + '\'' +
                ", tm=" + tm +
                ", value=" + value +
                ", flag=" + flag +
                ", dataType=" + dataType +
                '}';
    }
}
