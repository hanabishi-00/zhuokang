package com.huake.device.domain.generator;

import javax.annotation.Generated;

public class TreDeterThreshold {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer modelNumber;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String modelName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Float warnThreshold;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getModelNumber() {
        return modelNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setModelNumber(Integer modelNumber) {
        this.modelNumber = modelNumber;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getModelName() {
        return modelName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Float getWarnThreshold() {
        return warnThreshold;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setWarnThreshold(Float warnThreshold) {
        this.warnThreshold = warnThreshold;
    }
}