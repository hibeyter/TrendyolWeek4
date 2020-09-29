package com.trendyol.toyrobot.domain.compass;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;



@JsonSerialize
public class West implements Compass {
    @Override
    public void move(Position position) {
            position.backX();
    }

    @Override
    public Compass turnLeft() {
        return new South();
    }

    @Override
    public Compass turnRight() {
        return new North();
    }

    @Override
    public String name() {
        return "West";
    }

}
