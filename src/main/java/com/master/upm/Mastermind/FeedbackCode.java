package com.master.upm.Mastermind;

import java.util.ArrayList;

public class FeedbackCode extends Code {

    private boolean verifyValidColors(ArrayList<Color> colors) {
        for (Color c: colors) {
            if (c != Color.BLACK && c != Color.WHITE && c != Color.NULL)
                return false;
        }
        return true;
    }

    /**
     * Preconditions:
     *  - At least 1 feedback color is given inside the parameter list.
     *  - Feedback colors must be either WHITE, BLACK or NULL.
    */
    public FeedbackCode(ArrayList<Color> Colors) {
        assert Colors.size() > 0 : "Empty color list forbiden.";
        assert verifyValidColors(Colors) : "Colors other than BLACK, WHITE and NULL are forbidden in feedback codes.";

        super.length = Colors.size();
        super.colors = new Color[Colors.size()];
        for (int i = 0; i < Colors.size(); ++i)
            super.colors[i] = Colors.get(i);
    }

    public boolean isAllBlacks() {
        for (int i=0; i<length; ++i) {
            if (colors[i] != Color.BLACK)
                return false;
        }
        return true;
    }
}
