package main.dao;

import java.util.List;

public class Response {

    private String success;

    private String message;

    private List<Point> result;

    private Long timestamp;

    private int code;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Point> getResult() {
        return result;
    }

    public void setResult(List<Point> result) {
        this.result = result;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", timestamp=" + timestamp +
                ", code=" + code +
                '}';
    }
}
