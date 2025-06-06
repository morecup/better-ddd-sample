package org.morecup.jimmerddd.java.sample.admin.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class R<T> {
    //    @field:Schema(description = "状态码，200成功，500失败", defaultValue = "200")
    /**
     * 状态码（如200成功）
     */
    private final int code;
    //    @field:Schema(description = "提示信息",defaultValue = "操作成功")
    /**
     * 提示消息
     */
    private final String message;
    private final T data;
    //    @field:Schema(description = "时间戳", defaultValue = "")
    /**
     * 时间戳
     */
    private final String timestamp;

    public R(int code, String message, T data, String timestamp) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    // 快速判断是否成功
    @JsonIgnore
    public boolean isSuccess() {
        return code == ResultType.Success.getCode();
    }

    public static R<Void> success() {
        return new R<>(ResultType.Success.getCode(), "操作成功", null, String.valueOf(System.currentTimeMillis()));
    }

    public static <T> R<T> success(T data) {
        return new R<>(ResultType.Success.getCode(), "操作成功", data, String.valueOf(System.currentTimeMillis()));
    }

    // 错误响应（无数据）
    public static R<Void> error() {
        return new R<>(ResultType.ERROR.getCode(), "操作失败", null, String.valueOf(System.currentTimeMillis()));
    }

    public static <T> R<T> error(String msg) {
        return new R<>(ResultType.ERROR.getCode(), msg, null, String.valueOf(System.currentTimeMillis()));
    }
}