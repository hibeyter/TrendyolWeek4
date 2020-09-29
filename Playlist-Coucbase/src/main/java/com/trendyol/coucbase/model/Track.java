package com.trendyol.coucbase.model;


import lombok.Data;

@Data
public class Track {
   private  String name;
   private  String length;
   private  String artist;

   public Track() {
   }
}
