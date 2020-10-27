package com.master.upm.Mastermind;

import java.util.ArrayList;

public class Combination extends Code {

    private boolean verifyValidColors(ArrayList<Color> colors) {
        for (Color c: colors) {
            if (c == Color.BLACK || c == Color.WHITE || c == Color.NULL)
                return false;
        }
        return true;
    }

    /**
     * Preconditions:
     *  - At least 1 color is given inside the parameter list.
     *  - Colors cannot contain WHITE, BLACK or NULL.
    */
    public Combination(ArrayList<Color> colors) {
        assert colors.size() > 0 : "Empty color list forbiden.";
        assert verifyValidColors(colors) : "Colors BLACK, WHITE and NULL are forbidden in combinations.";

        super.length = colors.size();
        super.colors = new Color[colors.size()];
        for (int i = 0; i < colors.size(); ++i)
            super.colors[i] = colors.get(i);
    }

    /**
     * Given a secret combination, this will yield a feedback code with respect
     * the this combination.
     *
     * preconditions: The secret and the combination have the same size.
     */
    public FeedbackCode getFeedbackCodeFromSecret(Combination secret) {
        assert secret.getLength() == super.length : "Combinations not comparable.";

        ArrayList<Color> secretColors = new ArrayList();
        for (Color c: secret.getColors())
            secretColors.add(c);

        ArrayList<Color> feedbackColors = new ArrayList();

        for (int i=0; i<this.length; ++i) {
            if (super.colors[i] == secretColors.get(i)) {
                feedbackColors.add(Color.BLACK);
            }
            else {
                if (this.colorInList(super.colors[i], secretColors))
                    feedbackColors.add(Color.WHITE);
                else
                    feedbackColors.add(Color.NULL);
            }
        }
        return new FeedbackCode(feedbackColors);
    }


}
