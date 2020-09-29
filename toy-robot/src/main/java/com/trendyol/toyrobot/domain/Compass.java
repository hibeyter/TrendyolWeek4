package com.trendyol.toyrobot.domain;

import java.util.Arrays;
import java.util.List;

public enum Compass {
    NORTH, EAST, SOUTH, WEST;

    private static final List<Compass> list = Arrays.asList(Compass.values());

    public static int getId(Compass c) {
        return list.indexOf(c);
    }

    public static Compass findCompass(int i) {
        if (i < 0) i += list.size();
        return list.get(i % list.size());
    }

}
