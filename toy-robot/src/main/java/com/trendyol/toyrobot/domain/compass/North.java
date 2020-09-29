package com.trendyol.toyrobot.domain.compass;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonSerialize
public class North implements Compass {



    @Override
    public void move(Position position) {
        position.moveY();
    }

    @Override
    public Compass turnLeft() {
        return new West();
    }

    @Override
    public Compass turnRight() {
        return new East();
    }
    @Override
    public String name() {
        return "North";
    }

}
