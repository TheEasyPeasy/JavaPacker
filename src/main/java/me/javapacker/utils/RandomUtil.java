package me.javapacker.utils;


import java.util.Random;

public class RandomUtil {

    private final static Random r = new Random();

    public static int fromRange(int rangeNumber){
        Random rand = new Random();
        int randomNum = rand.nextInt((rangeNumber - 1) + 1) + 1;
        return randomNum;
    }

    public static int fromRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return r.nextInt((max - min) + 1) + min;
    }

}