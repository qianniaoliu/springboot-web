package com.athena.px.wechat.enums;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/1 19:19
 */
public enum Chat {
    LOGIN("LOGIN"),
    LOGOUT("LOGOUT"),
    CHAT("CHAT");

    Chat(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
