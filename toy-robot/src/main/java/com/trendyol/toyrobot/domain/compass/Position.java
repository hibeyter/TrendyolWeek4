package com.trendyol.toyrobot.domain.compass;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@JsonSerialize
public class Position implements Serializable {
    private int x;
    private int y;

    public void moveX(){
        this.x=this.x+1;
    }
    public void moveY(){
        this.y=this.y+1;
    }
    public void backX(){
        this.x=this.x-1;
    }
    public void backY(){
        this.y=this.y-1;
    }


}
