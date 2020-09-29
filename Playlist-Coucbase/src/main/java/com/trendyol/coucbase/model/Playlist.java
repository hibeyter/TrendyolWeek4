package com.trendyol.coucbase.model;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Playlist {
    private String id;
    private String name;
    private String description;
    private int followers;
    private List<Track> tracks;
    private String userId;
    public Playlist() {
        this.id= UUID.randomUUID().toString();
        this.tracks=new ArrayList<>();
    }
}