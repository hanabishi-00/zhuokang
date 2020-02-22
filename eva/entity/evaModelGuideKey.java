package hdy.eva.entity;

public class evaModelGuideKey {
    private Integer time;

    private String type;

    public evaModelGuideKey(Integer time, String type) {
        this.time = time;
        this.type = type;
    }

    public evaModelGuideKey() {
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