package hdy.faultDiagnose.entity;

public class diagSugg {
    private Integer suggId;

    private String repairCom;

    private String toolCom;

    private String runCom;

    public diagSugg(Integer suggId, String repairCom, String toolCom, String runCom) {
        this.suggId = suggId;
        this.repairCom = repairCom;
        this.toolCom = toolCom;
        this.runCom = runCom;
    }

    public diagSugg() {
        super();
    }

    public Integer getSuggId() {
        return suggId;
    }

    public void setSuggId(Integer suggId) {
        this.suggId = suggId;
    }

    public String getRepairCom() {
        return repairCom;
    }

    public void setRepairCom(String repairCom) {
        this.repairCom = repairCom == null ? null : repairCom.trim();
    }

    public String getToolCom() {
        return toolCom;
    }

    public void setToolCom(String toolCom) {
        this.toolCom = toolCom == null ? null : toolCom.trim();
    }

    public String getRunCom() {
        return runCom;
    }

    public void setRunCom(String runCom) {
        this.runCom = runCom == null ? null : runCom.trim();
    }
}