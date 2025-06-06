package org.morecup.jimmerddd.java.sample.admin.base;

public enum ResultType {
    Success(200),
    ERROR(500);

    private final int code;

    ResultType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}