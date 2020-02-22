package hdy.eva.entity;

public class evaModelGuide extends evaModelGuideKey {
    private Integer version;

    public evaModelGuide(Integer time, String type, Integer version) {
        super(time, type);
        this.version = version;
    }

    public evaModelGuide() {
        super();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}