package hdy.faultDiagnose.entity;

public class diagGov {
    private Integer nodeId;

    private Float freq;

    private Integer suggId;

    public diagGov(Integer nodeId, Float freq, Integer suggId) {
        this.nodeId = nodeId;
        this.freq = freq;
        this.suggId = suggId;
    }

    public diagGov() {
        super();
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Float getFreq() {
        return freq;
    }

    public void setFreq(Float freq) {
        this.freq = freq;
    }

    public Integer getSuggId() {
        return suggId;
    }

    public void setSuggId(Integer suggId) {
        this.suggId = suggId;
    }
}