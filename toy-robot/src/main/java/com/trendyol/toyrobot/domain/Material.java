package com.trendyol.toyrobot.domain;

import lombok.ToString;

@ToString
public class Material {
    private String name;

    public Material(String name) {
        this.name = name;
    }

    public Material() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
