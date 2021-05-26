package com.shootemup.g53.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ColorOperation {
    public static String generateColor(Random rand) {
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        return parseColor(r, g, b);
    }

    public static String parseColor(int r, int g, int b) {
        return String.format("#%02x%02x%02x", r, g, b);
    }

    public static List<Integer> parseColor(String color) {
        return Arrays.asList(
                Integer.valueOf(color.substring(1,3), 16),
                Integer.valueOf(color.substring(3,5), 16),
                Integer.valueOf(color.substring(5,7), 16)
        );
    }

    public static String invertColor(String color) {
        List<Integer> rgb = parseColor(color);
        return parseColor(255 - rgb.get(0),255 - rgb.get(1),255 - rgb.get(2));
    }
}
