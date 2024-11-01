package com.alimert.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1000" , "Record not found"),
    TOKEN_IS_EXPIRED("1001" , "Token expired"),
    GENERAL_EXCEPTION("1001", "An error occurred");

    private String code;

    private String message;

    MessageType(String code, String message) {
    this.code = code;
    this.message = message;
    }


}
