package com.trendyol.toyrobot.domain.compass;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;



@JsonSerialize
public class South implements Compass {
    @Override
    public void move(Position position) {
        position.backY();
    }

    @Override
    public Compass turnLeft() {
        return new East();
    }

    @Override
    public Compass turnRight( ) {
        return new West();
    }

    @Override
    public String name() {
        return "South";
    }
}
