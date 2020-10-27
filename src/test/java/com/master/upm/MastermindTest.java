package com.master.upm.Mastermind;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.master.upm.Mastermind.Combination;
import com.master.upm.Mastermind.FBColor;
import com.master.upm.Mastermind.Color;
import com.master.upm.Mastermind.FeedbackCode;
import com.master.upm.Mastermind.SecretFactory;

import java.util.ArrayList;

import org.junit.Test;

public class MastermindTest
{

    @Test
    public void feedBackCodeisAllBlacks() {
        ArrayList<FBColor> arr = new ArrayList();
        arr.add(FBColor.BLACK);
        FeedbackCode fc = new FeedbackCode(arr);
        assertTrue(fc.isAllBlacks());

        ArrayList<FBColor> arr2 = new ArrayList();
        arr2.add(FBColor.BLACK);
        arr2.add(FBColor.BLACK);
        arr2.add(FBColor.BLACK);
        FeedbackCode fc2 = new FeedbackCode(arr2);
        assertTrue(fc2.isAllBlacks());

        ArrayList<FBColor> arr3 = new ArrayList();
        arr3.add(FBColor.NULL);
        arr3.add(FBColor.BLACK);
        arr3.add(FBColor.BLACK);
        FeedbackCode fc3 = new FeedbackCode(arr3);
        assertFalse(fc3.isAllBlacks());

        ArrayList<FBColor> arr4 = new ArrayList();
        arr4.add(FBColor.WHITE);
        arr4.add(FBColor.BLACK);
        arr4.add(FBColor.BLACK);
        FeedbackCode fc4 = new FeedbackCode(arr4);
        assertFalse(fc4.isAllBlacks());
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

}
