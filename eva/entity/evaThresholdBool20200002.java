package hdy.eva.entity;

public class evaThresholdBool20200002 {
    private Integer id;

    private String threshold;

    public evaThresholdBool20200002(Integer id, String threshold) {
        this.id = id;
        this.threshold = threshold;
    }

    public evaThresholdBool20200002() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold == null ? null : threshold.trim();
    }
}