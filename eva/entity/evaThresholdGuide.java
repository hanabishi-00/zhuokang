package hdy.eva.entity;

public class evaThresholdGuide extends evaThresholdGuideKey {
    private Integer version;

    public evaThresholdGuide(Integer time, String type, Integer version) {
        super(time, type);
        this.version = version;
    }

    public evaThresholdGuide() {
        super();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}