package com.huake.device.domain.vo;

import java.util.ArrayList;
import java.util.List;

public class SearchData2 {
    private String label = "";
    private List<Object> data = new ArrayList<>();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
