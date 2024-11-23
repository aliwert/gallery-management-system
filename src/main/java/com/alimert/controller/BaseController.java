package com.alimert.controller;

public class BaseController {

    public <T> RootEntity<T> ok(T payload) {
        return RootEntity.ok(payload);
    }

    public <T> RootEntity<T> error(String errMessage) {
        return RootEntity.error(errMessage);
    }
    public static <T> RootEntity<T> noContent() {
        RootEntity<T> entity = new RootEntity<>();
        entity.setStatus(204);
        return entity;
    }
}
