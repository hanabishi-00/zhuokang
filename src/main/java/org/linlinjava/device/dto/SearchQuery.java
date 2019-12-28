package org.linlinjava.device.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SearchQuery {
    private String device;
    private String level;
    private List<String> points;
    private LocalDateTime start;
    private LocalDateTime end;

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

    public List<String> getPoints() {
        return points;
    }

    public void setPoints(List<String> points) {
        this.points = points;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
