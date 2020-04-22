package com.huake.device.domain.generator;

import javax.annotation.Generated;

public class PdmanDbVersion {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String dbVersion;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String versionDesc;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String createdTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDbVersion() {
        return dbVersion;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDbVersion(String dbVersion) {
        this.dbVersion = dbVersion;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getVersionDesc() {
        return versionDesc;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCreatedTime() {
        return createdTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}