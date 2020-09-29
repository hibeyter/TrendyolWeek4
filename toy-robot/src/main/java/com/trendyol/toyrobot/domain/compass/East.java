package com.trendyol.toyrobot.domain.compass;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;



@JsonSerialize
public class East implements Compass {


    @Override
    public void move(Position position) {
        position.moveX();
    }

    @Override
    public Compass turnLeft() {
        return new North();
    }

    @Override
    public Compass turnRight() {
        return new South();
    }


    @Override
    public String name() {
        return "East";
    }
}
