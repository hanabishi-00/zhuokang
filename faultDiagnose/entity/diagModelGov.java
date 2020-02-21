package hdy.faultDiagnose.entity;

public class diagModelGov {
    private Integer nodeId;

    private Integer fatherId;

    private String gate;

    public diagModelGov(Integer nodeId, Integer fatherId, String gate) {
        this.nodeId = nodeId;
        this.fatherId = fatherId;
        this.gate = gate;
    }

    public diagModelGov() {
        super();
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate == null ? null : gate.trim();
    }
}