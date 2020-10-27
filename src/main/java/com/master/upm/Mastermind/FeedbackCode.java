package com.master.upm.Mastermind;

import java.util.ArrayList;

public class FeedbackCode {
    private FBColor colors[];
    private int length;

    /**
     * Preconditions: At least 1 feedback color is given inside the parameter list.
    */
    public FeedbackCode(ArrayList<FBColor> fbcolors) {
        assert fbcolors.size() > 0 : "Empty color list forbiden.";
        length = fbcolors.size();
        colors = new FBColor[fbcolors.size()];
        for (int i = 0; i < fbcolors.size(); ++i)
            colors[i] = fbcolors.get(i);
    }

    public boolean isAllBlacks() {
        for (int i=0; i<length; ++i) {
            if (colors[i] != FBColor.BLACK)
                return false;
        }
        return true;
    }
}
