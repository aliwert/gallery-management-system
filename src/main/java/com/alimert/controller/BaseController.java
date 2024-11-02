package com.alimert.controller;

public class BaseController {

    public <T> RootEntity<T> ok(T payload) {
        return RootEntity.ok(payload);
    }

    public <T> RootEntity<T> error(String errMessage) {
        return RootEntity.error(errMessage);
    }
}
