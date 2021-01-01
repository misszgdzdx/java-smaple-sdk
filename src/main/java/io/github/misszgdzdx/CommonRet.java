package io.github.misszgdzdx;

import java.io.Serializable;

/**
 * 统一信息处理类
 */
public class CommonRet<T> implements Serializable {

    private static final long serialVersionUID = -3257807242523489792L;

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public static class Builder {

        public static <T> CommonRet<T> success() {
            CommonRet<T> ret = new CommonRet<>();
            ret.code = 0;
            return ret;
        }

        public static <T> CommonRet<T> success(T data) {
            CommonRet<T> ret = new CommonRet<>();
            ret.code = 0;
            ret.data = data;
            return ret;
        }

        public static <T> CommonRet<T> error(int code, String msg) {
            CommonRet<T> ret = new CommonRet<>();
            ret.code = code;
            ret.msg = msg;
            return ret;
        }

    }

    public static <T> CommonRet<T> REP() {
        return new CommonRet<>();
    }

    public CommonRet<T> initCode(int code) {
        this.code = code;
        return this;
    }

    public CommonRet<T> initMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public CommonRet<T> initData(T data) {
        this.data = data;
        return this;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
