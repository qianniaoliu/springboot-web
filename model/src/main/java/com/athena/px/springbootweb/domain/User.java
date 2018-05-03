package com.athena.px.springbootweb.domain;


import com.athena.px.springbootweb.annotation.ValidUserName;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/4/23 9:50
 */
public class User {
    @NotNull
    private int id;
    @ValidUserName
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
