package com.master.upm.Mastermind;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class SecretFactory {
    private int length;

    private Color getRandomColor() {
        int selection =  ThreadLocalRandom.current().nextInt(0, Color.CombinationValues().length);
        return Color.values()[selection];
    }

    public SecretFactory(int length) {
        this.length = length;
    }

    public Combination generateSecret() {
        ArrayList<Color> secretColors = new ArrayList();
        for (int i=0; i<length; ++i)
            secretColors.add(getRandomColor());
        return new Combination(secretColors);
    }

}