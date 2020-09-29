package com.trendyol.toyrobot.domain.compass;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public interface Compass {
    void move(Position position);
    Compass turnLeft();
    Compass turnRight();
    String name();
}
