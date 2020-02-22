package hdy.eva.entity;

public class evaThresholdGuideKey {
    private Integer time;

    private String type;

    public evaThresholdGuideKey(Integer time, String type) {
        this.time = time;
        this.type = type;
    }

    public evaThresholdGuideKey() {
        super();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}