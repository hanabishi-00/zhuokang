package org.linlinjava.device.vo;

import java.util.ArrayList;
import java.util.List;

public class SearchData2 {
    private String label = "";
    private List<Integer> value = new ArrayList<>();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Integer> getValue() {
        return value;
    }

    public void setValue(List<Integer> value) {
        this.value = value;
    }
}
