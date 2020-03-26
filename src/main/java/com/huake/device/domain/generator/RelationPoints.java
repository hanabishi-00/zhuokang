package com.huake.device.domain.generator;

import javax.annotation.Generated;

public class RelationPoints {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long relationId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String relationType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Float percent;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getType() {
        return type;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setType(String type) {
        this.type = type;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getRelationId() {
        return relationId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getRelationType() {
        return relationType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Float getPercent() {
        return percent;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPercent(Float percent) {
        this.percent = percent;
    }
}