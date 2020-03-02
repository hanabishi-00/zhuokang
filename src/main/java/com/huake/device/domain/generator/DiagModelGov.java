package com.huake.device.domain.generator;

import javax.annotation.Generated;

public class DiagModelGov {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer nodeId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer fatherId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String gate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getNodeId() {
        return nodeId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getFatherId() {
        return fatherId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getGate() {
        return gate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setGate(String gate) {
        this.gate = gate;
    }
}