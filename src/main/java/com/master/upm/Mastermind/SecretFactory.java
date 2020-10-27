package com.master.upm.Mastermind;

import java.util.Random;
import java.util.ArrayList;

public class SecretFactory {
    private Random randomIntegerGenerator;
    private int length;

    private Color getRandomColor() {
        int selection = (int) randomIntegerGenerator.nextFloat() * Color.values().length;
        return Color.values()[selection];
    }

    public SecretFactory(int length) {
        this.length = length;
        randomIntegerGenerator = new Random();
    }

    public Combination generateSecret() {
        ArrayList<Color> secretColors = new ArrayList();
        for (int i=0; i<length; ++i)
            secretColors.add(getRandomColor());
        return new Combination(secretColors);
    }

}