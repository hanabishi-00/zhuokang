package main.dao;

public class FaultTreeResult {

    String time;
    String record_id;//报告编号
    int sugg_id;//建议编号
    String repair_com;//检修建议
    String tool_com;//工具建议
    String run_com;//运行建议
    int node_id;//节点编号
    float freq;//发生概率


   /* String faultName;//故障名称
    String faultLocation;//故障部位
    int unitNo;//机组编号
    String recommendation;//故障建议*/



    public String getTime() { return time; }

    public String getRecord_id() { return record_id; }
    public void setRecord_id(String record_id) { this.record_id = record_id; }

    public int getSugg_id() { return sugg_id; }
    public void setSugg_id(int sugg_id) { this.sugg_id = sugg_id; }

    public int getNode_id() { return node_id; }
    public void setNode_id(int node_id) { this.node_id = node_id; }

    public float getFreq() { return freq; }
    public void setFreq(float freq) { this.freq = freq; }


   /*

    public String getFaultName() { return faultName; }
    public void setFaultName(String faultName) { this.faultName = faultName; }
    public String getFaultLocation() { return faultLocation; }
    public void setFaultLocation(String faultLocation) { this.faultLocation = faultLocation; }
    public int getUnitNo() { return unitNo; }
    public void setUnitNo(int unitNo) { this.unitNo = unitNo; }
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }*/

}
