package com.trendyol.toyrobot.domain;

import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trendyol.toyrobot.domain.compass.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@JsonSerialize
public class Rover {

    private String id;
    private Position position;
    private Compass compass;
    private List<Material> material;


    public void move(){
        compass.move(this.position);
        this.material.add(new Material(this.compass.name()));
    }
    public void turnLeft() {
        this.compass = compass.turnLeft();
        this.material.add(new Material(this.compass.name()));
    }

    public void turnRight() {
        this.compass = compass.turnRight();
        this.material.add(new Material(this.compass.name()));
    }

    public Rover() {
        this.id = UUID.randomUUID().toString();
        this.position = new Position(0, 0);
        this.compass = new North();
        this.material = new ArrayList<>();
    }
    public Rover(Position position) {
        this.id = UUID.randomUUID().toString();
        this.position = position;
        this.compass = new North();
        this.material = new ArrayList<>();
    }
    public Rover(Position position, Compass compass) {
        this.id = UUID.randomUUID().toString();
        this.position = position;
        this.compass = compass;
        this.material = new ArrayList<>();
    }

    public Rover(String id, Position position, Compass compass, List<Material> material) {
        this.id = id;
        this.position = position;
        this.compass = compass;
        this.material = material;
    }
}
