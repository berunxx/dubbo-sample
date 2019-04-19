package com.sample.util;

import lombok.Data;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 22:14
 * @Description:
 */
@Data
public class JSONResult {
    private int status;

    private String msg;

    private Object data;

    public JSONResult(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JSONResult() {
        new JSONResult(200, "", null);
    }
    public static JSONResult ok() {
        return new JSONResult(200, "", null);
    }
    public static JSONResult ok(Object data) {
        return new JSONResult(200, "", data);
    }

    public static JSONResult ok(String msg, Object data) {
        return new JSONResult(200, msg, data);
    }

    public static JSONResult error(String msg) {
        return new JSONResult(500, msg, null);
    }

    public static JSONResult error(int code, String msg) {
        return new JSONResult(code, msg, null);
    }

}
