package com.master.upm.Mastermind;

import java.util.ArrayList;

public class Combination {
    private Color ccolors[];
    private int length;

    private boolean colorInList (Color color, ArrayList<Color> list) {
        for (Color c : list) {
            if (c == color)
                return true;
        }
        return false;
    }

    /**
     * Preconditions: At least 1 color is given inside the parameter list.
    */
    public Combination(ArrayList<Color> colors) {
        assert colors.size() > 0 : "Empty color list forbiden.";

        this.length = colors.size();
        this.ccolors = new Color[colors.size()];
        for (int i = 0; i < colors.size(); ++i)
            ccolors[i] = colors.get(i);
    }

    /**
     * Given a secret combination, this will yield a feedback code with respect
     * the this combination.
     *
     * preconditions: The secret and the combination have the same size.
     */
    public FeedbackCode getFeedbackCodeFromSecret(Combination secret) {
        assert secret.getLength() == this.length : "Combinations not comparable.";

        ArrayList<Color> secretColors = new ArrayList();
        for (Color c: secret.getColors())
            secretColors.add(c);

        ArrayList<FBColor> feedbackColors = new ArrayList();

        for (int i=0; i<this.length; ++i) {
            if (ccolors[i] == secretColors.get(i)) {
                feedbackColors.add(FBColor.BLACK);
            }
            else {
                if (this.colorInList(ccolors[i], secretColors))
                    feedbackColors.add(FBColor.WHITE);
                else
                    feedbackColors.add(FBColor.NULL);
            }
        }

        return new FeedbackCode(feedbackColors);
    }

    public ArrayList<Color> getColors() {
        ArrayList<Color> colors = new ArrayList();
        for (int i=0; i<this.length; ++i)
            colors.add(ccolors[i]);
        return colors;
    }

    public int getLength() {
        return this.length;
    }
}
