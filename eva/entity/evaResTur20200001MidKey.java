package hdy.eva.entity;

public class evaResTur20200001MidKey {
    private Integer timeOnline;

    private Byte unit;

    public evaResTur20200001MidKey(Integer timeOnline, Byte unit) {
        this.timeOnline = timeOnline;
        this.unit = unit;
    }

    public evaResTur20200001MidKey() {
        super();
    }

    public Integer getTimeOnline() {
        return timeOnline;
    }

    public void setTimeOnline(Integer timeOnline) {
        this.timeOnline = timeOnline;
    }

    public Byte getUnit() {
        return unit;
    }

    public void setUnit(Byte unit) {
        this.unit = unit;
    }
}