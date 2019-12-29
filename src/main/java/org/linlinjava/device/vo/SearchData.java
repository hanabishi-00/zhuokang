package org.linlinjava.device.vo;

import java.util.ArrayList;
import java.util.List;

public class SearchData {
    private List<String> xList = new ArrayList<>();
    private List<SearchData2> yList = new ArrayList<>();

    public List<String> getxList() {
        return xList;
    }

    public void setxList(List<String> xList) {
        this.xList = xList;
    }

    public List<SearchData2> getyList() {
        return yList;
    }

    public void setyList(List<SearchData2> yList) {
        this.yList = yList;
    }

}
