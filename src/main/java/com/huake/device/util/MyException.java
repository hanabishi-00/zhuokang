package com.huake.device.util;

import com.huake.device.util.ResponseUtil;

public class MyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Object object;

    public MyException(Object object) {
        this.object = object;
    }

    public MyException(String msg) {
        this.object = ResponseUtil.fail(-1, msg);
    }

    public MyException(int code, String msg) {
        this.object = ResponseUtil.fail(code, msg);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
