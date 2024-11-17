package com.alimert.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1000", "Record not found"),
    TOKEN_IS_EXPIRED("1001", "Token expired"),
    GENERAL_EXCEPTION("1002", "An error occurred"),
    USERNAME_NOT_FOUND("1003", "Username not found"),
    REFRESH_TOKEN_NOT_FOUND("1004", "Refresh token not found"),
    USERNAME_OR_PASSWORD_INCORRECT("1005", "Username or password incorrect"),
    REFRESH_TOKEN_EXPIRED("1006", "Refresh token expired"),
    CUSTOMER_MONEY_IS_NOT_ENOUGH("1007", "Customer money is not enough"),
    CURRENCY_RATES_IS_OCCURED("1008", "Currency rates is occured");

    private String code;

    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
