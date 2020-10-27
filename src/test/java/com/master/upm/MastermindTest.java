package com.master.upm.Mastermind;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.master.upm.Mastermind.Combination;
import com.master.upm.Mastermind.Color;
import com.master.upm.Mastermind.Board;
import com.master.upm.Mastermind.Color;
import com.master.upm.Mastermind.FeedbackCode;
import com.master.upm.Mastermind.SecretFactory;

import java.util.ArrayList;

import org.junit.Test;

public class MastermindTest
{

    @Test
    public void feedBackCodeisAllBlacks() {
        ArrayList<Color> arr = new ArrayList();
        arr.add(Color.BLACK);
        FeedbackCode fc = new FeedbackCode(arr);
        assertTrue(fc.isAllBlacks());

        ArrayList<Color> arr2 = new ArrayList();
        arr2.add(Color.BLACK);
        arr2.add(Color.BLACK);
        arr2.add(Color.BLACK);
        FeedbackCode fc2 = new FeedbackCode(arr2);
        assertTrue(fc2.isAllBlacks());

        assertTrue(fc2.equals(fc2));

        ArrayList<Color> arr3 = new ArrayList();
        arr3.add(Color.NULL);
        arr3.add(Color.BLACK);
        arr3.add(Color.BLACK);
        FeedbackCode fc3 = new FeedbackCode(arr3);
        assertFalse(fc3.isAllBlacks());

        assertFalse(fc3.equals(fc2));

        ArrayList<Color> arr4 = new ArrayList();
        arr4.add(Color.WHITE);
        arr4.add(Color.BLACK);
        arr4.add(Color.BLACK);
        FeedbackCode fc4 = new FeedbackCode(arr4);
        assertFalse(fc4.isAllBlacks());

        assertFalse(fc4.equals(fc3));
    }

    @Test
    public void combinationCodeCanGenerateFeedbackFromSecret() {
        ArrayList<Color> secretColors_1 = new ArrayList();
        secretColors_1.add(Color.RED);
        secretColors_1.add(Color.RED);
        secretColors_1.add(Color.RED);

        Combination secret_1 = new Combination(secretColors_1);
        Combination combo_1 = new Combination(secretColors_1);

        FeedbackCode feedback_1 = combo_1.getFeedbackCodeFromSecret(secret_1);

        assertTrue(feedback_1.isAllBlacks());

        secretColors_1.set(1, Color.BLUE);
        Combination secret_2 = new Combination(secretColors_1);

        FeedbackCode feedback_2 = combo_1.getFeedbackCodeFromSecret(secret_2);

        assertFalse(feedback_2.isAllBlacks());
    }

    @Test
    public void secretFactoryCombinationsAreValid() {
        SecretFactory sf = new SecretFactory(3);
        Combination secret = sf.generateSecret();
        Combination combo = new Combination(secret.getColors());
        FeedbackCode fc = combo.getFeedbackCodeFromSecret(secret);

        assertTrue(fc.isAllBlacks());

        ArrayList<Color> arr = new ArrayList(secret.getColors());
        if (arr.get(1) != Color.RED)
            arr.set(1, Color.RED);
        else
            arr.set(1, Color.BLUE);
        Combination combo2 = new Combination(arr);
        FeedbackCode fc2 = combo2.getFeedbackCodeFromSecret(secret);

        assertFalse(fc2.isAllBlacks());
    }

    @Test
    public void boardIsVerifyingNewCombination() {
        int codeLength = 3;
        Board board = new Board(codeLength);

        Combination secret = board.getSecret();

        ArrayList<Color> colors_1 = new ArrayList();
        colors_1.add(Color.RED);
        colors_1.add(Color.RED);
        colors_1.add(Color.RED);
        Combination Combo1 = new Combination(colors_1);

        colors_1.set(1, Color.BLUE);
        Combination Combo2 = new Combination(colors_1);

        colors_1.set(2, Color.GREEN);
        Combination Combo3 = new Combination(colors_1);

        FeedbackCode fc_1a = board.pushNewCombination(Combo1);
        FeedbackCode fc_2a = board.pushNewCombination(Combo2);
        FeedbackCode fc_3a = board.pushNewCombination(Combo3);

        FeedbackCode fc_1b = Combo1.getFeedbackCodeFromSecret(secret);
        FeedbackCode fc_2b = Combo2.getFeedbackCodeFromSecret(secret);
        FeedbackCode fc_3b = Combo3.getFeedbackCodeFromSecret(secret);

        assertTrue (fc_1a.equals(fc_1b));
        assertTrue (fc_2a.equals(fc_2b));
        assertTrue (fc_3a.equals(fc_3b));

        ArrayList<Combination> combos = board.getCombinationList();
        ArrayList<FeedbackCode> feedbacks = board.getFeedbackList();

        assertTrue(Combo1.equals(combos.get(0)));
        assertTrue(Combo2.equals(combos.get(1)));
        assertTrue(Combo3.equals(combos.get(2)));

        assertTrue(fc_1a.equals(feedbacks.get(0)));
        assertTrue(fc_2a.equals(feedbacks.get(1)));
        assertTrue(fc_3a.equals(feedbacks.get(2)));
    }

}
