package com.boubyan.boubyantask.enums;

public enum ResponseStatus {

    RESERVED("reserved"),
    CANCELLED("cancelled");

    final String value;

    ResponseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
