package com.huake.device.domain.generator;

import javax.annotation.Generated;

public class DiagSugg {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer suggId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String repairCom;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String toolCom;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String runCom;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getSuggId() {
        return suggId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSuggId(Integer suggId) {
        this.suggId = suggId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRepairCom() {
        return repairCom;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRepairCom(String repairCom) {
        this.repairCom = repairCom;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getToolCom() {
        return toolCom;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setToolCom(String toolCom) {
        this.toolCom = toolCom;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRunCom() {
        return runCom;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRunCom(String runCom) {
        this.runCom = runCom;
    }
}