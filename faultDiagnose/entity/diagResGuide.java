package hdy.faultDiagnose.entity;

public class diagResGuide {
    private Integer recordId;

    private Integer time;

    public diagResGuide(Integer recordId, Integer time) {
        this.recordId = recordId;
        this.time = time;
    }

    public diagResGuide() {
        super();
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}