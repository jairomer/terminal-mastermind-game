package com.master.upm.TerminalMastermind;

import com.master.upm.Mastermind.Color;
import com.master.upm.Mastermind.Combination;
import com.master.upm.Mastermind.FeedbackCode;

import java.util.ArrayList;

public class CodeToStringAdapter {

    // Colors WHITE, BLACK and null are not considered, as
    // they should never inserted by a player.
    final static char DEFINED[] = {
        'r',    // RED
        'b',    // BLUE
        'y',    // YELLOW
        'g',    // GREEN
        'o',    // ORANGE
        'p',    // PURPLE
    };

    private static boolean colorDefined(char color) {
        for (int i=0; i<DEFINED.length; ++i) {
            if (DEFINED[i] == color)
                return true;
        }
        return false;
    }

    private static Color charToColor(char c) {
        switch (c) {
            case 'r': return Color.RED;
            case 'b': return Color.BLUE;
            case 'y': return Color.YELLOW;
            case 'g': return Color.GREEN;
            case 'o': return Color.ORANGE;
            case 'p': return Color.PURPLE;
            default:
                break;
        }
        return Color.NULL;
    }

    private static String colorToStr(Color c) {
        switch (c) {
            case RED:    return "r";
            case BLUE:   return "b";
            case YELLOW: return "y";
            case GREEN:  return "g";
            case ORANGE: return "o";
            case PURPLE: return "p";
            default:
                break;
        }
        return "*";
    }

    public static boolean isValidColorString(String colorInput) {
        for (char c: colorInput.toCharArray()) {
            if (!colorDefined(c))
                return false;
        }
        return true;
    }

    /**
     * preconditions:
     *  - String colors must be valid.
     */
    public static Combination getCombinationFromString(String stringCombo) {
        assert isValidColorString(stringCombo) : "Colors not DEFINED on combination input..";

        ArrayList<Color> comboColors = new ArrayList();
        for (char c: stringCombo.toCharArray())
            comboColors.add(charToColor(c));

        return new Combination(comboColors);
    }

    public static String getStringFromCombination(Combination combo) {
        ArrayList<Color> comboColors = combo.getColors();

        String output = "";
        for (Color color: comboColors)
            output = output + colorToStr(color);

        return output;
    }

    public static String getFeedbackToString(FeedbackCode feedback) {
        int whiteCount = 0;
        int blackCount = 0;

        for (Color c: feedback.getColors()) {
            if (c == Color.WHITE)
                whiteCount++;
            else if (c == Color.BLACK)
                blackCount++;
        }

        return String.format("%d blacks and %d whites", blackCount, whiteCount);

    }
}
