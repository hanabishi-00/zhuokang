package hdy.eva.entity;

public class evaResTur20200001LeafOnlineKey {
    private Integer time;

    private Byte unit;

    public evaResTur20200001LeafOnlineKey(Integer time, Byte unit) {
        this.time = time;
        this.unit = unit;
    }

    public evaResTur20200001LeafOnlineKey() {
        super();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Byte getUnit() {
        return unit;
    }

    public void setUnit(Byte unit) {
        this.unit = unit;
    }
}