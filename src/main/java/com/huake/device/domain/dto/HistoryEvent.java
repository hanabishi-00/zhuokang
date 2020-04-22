package com.huake.device.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class HistoryEvent {
    @ApiModelProperty("开始时间")
    private int start;
    @ApiModelProperty("结束时间")
    private int end;
    @ApiModelProperty("类型列表")
    private List<Integer> type;
    @ApiModelProperty("关键词")
    private String keyword;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<Integer> getType() {
        return type;
    }

    public void setType(List<Integer> type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
