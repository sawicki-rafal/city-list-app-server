package com.sawraf.citylist.exception.message;

/**
 * Message codes used in response messages for Exceptions
 */
public enum MessageCode {
    ERROR_ENTITY_NOT_FOUND("citylistapp.error.entity.notfound"),
    ERROR_VALIDATION("citylistapp.error.validation"),
    ERROR_UNKNOWN("citylistapp.error.unknown");

    private final String key;

    MessageCode(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
