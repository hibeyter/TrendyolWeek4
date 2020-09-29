package com.trendyol.coucbase.common;

public class Queries {

    public static  String createIndexPlayListId =
            "CREATE INDEX `adv_id` ON `playlist`(`id`)";
    public static  String createIndexUserId =
            "CREATE INDEX `adv_userId` ON `playlist`(`userId`)";
    public static String getAllPlayListWithUserId =
            "Select `playlist`.* from playlist where userId='%s'";
}
