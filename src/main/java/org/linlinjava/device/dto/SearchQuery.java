package org.linlinjava.device.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class SearchQuery {
    private String device;
    private String level;
    private List<Map<String, String>> points;
    private Long start;
    private Long end;

    @Override
    public String toString() {
        return "SearchQuery{" +
                "device='" + device + '\'' +
                ", level='" + level + '\'' +
                ", points=" + points +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Map<String, String>> getPoints() {
        return points;
    }

    public void setPoints(List<Map<String, String>> points) {
        this.points = points;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
