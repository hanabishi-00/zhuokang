package com.huake.device.domain.generator;

import javax.annotation.Generated;

public class DiagResult {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String recordId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer nodeId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Float freq;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer suggId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRecordId() {
        return recordId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getNodeId() {
        return nodeId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Float getFreq() {
        return freq;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFreq(Float freq) {
        this.freq = freq;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getSuggId() {
        return suggId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSuggId(Integer suggId) {
        this.suggId = suggId;
    }
}