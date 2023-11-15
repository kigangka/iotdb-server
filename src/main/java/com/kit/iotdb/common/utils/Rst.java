package com.kit.iotdb.common.utils;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 返回结果
 *
 * @author kit
 * @version 1.0
 * @date 2023/11/9 11:23
 */
public class Rst extends LinkedHashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;    // 序列化版本号
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 500;

    public Rst() {
    }

    public Rst(int code, String msg, Object data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    /**
     * 获取code
     *
     * @return code
     */
    public Integer getCode() {
        return (Integer) this.get("code");
    }

    /**
     * 获取msg
     *
     * @return msg
     */
    public String getMsg() {
        return (String) this.get("msg");
    }

    /**
     * 获取data
     *
     * @return data
     */
    public Object getData() {
        return (Object) this.get("data");
    }

    /**
     * 给code赋值，连缀风格
     *
     * @param code code
     * @return 对象自身
     */
    public Rst setCode(int code) {
        this.put("code", code);
        return this;
    }

    /**
     * 给msg赋值，连缀风格
     *
     * @param msg msg
     * @return 对象自身
     */
    public Rst setMsg(String msg) {
        this.put("msg", msg);
        return this;
    }

    /**
     * 给data赋值，连缀风格
     *
     * @param data data
     * @return 对象自身
     */
    public Rst setData(Object data) {
        this.put("data", data);
        return this;
    }

    // 构建成功
    public static Rst ok() {
        return new Rst(CODE_SUCCESS, "ok", null);
    }

    public static Rst ok(String msg) {
        return new Rst(CODE_SUCCESS, msg, null);
    }

    public static Rst code(int code) {
        return new Rst(code, null, null);
    }

    public static Rst data(Object data) {
        return new Rst(CODE_SUCCESS, "ok", data);
    }

    // 构建失败
    public static Rst error() {
        return new Rst(CODE_ERROR, "error", null);
    }

    public static Rst error(String msg) {
        return new Rst(CODE_ERROR, msg, null);
    }

    @Override
    public String toString() {
        return "{"
                + "\"code\": " + this.getCode()
                + ", \"msg\": " + transValue(this.getMsg())
                + ", \"data\": " + transValue(this.getData())
                + "}";
    }

    private String transValue(Object value) {
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        return String.valueOf(value);
    }
}
