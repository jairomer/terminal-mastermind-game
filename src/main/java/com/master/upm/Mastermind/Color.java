package com.master.upm.Mastermind;

public enum Color {
    RED,
    BLUE,
    YELLOW,
    GREEN,
    ORANGE,
    PURPLE,
    WHITE,
    BLACK,
    NULL;

    public static Color[] CombinationValues() {
        return new Color[] {
            RED,
            BLUE,
            YELLOW,
            GREEN,
            ORANGE,
            PURPLE
        };
    }
}
