package com.master.upm;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

import com.master.upm.Mastermind.Color;
import com.master.upm.Mastermind.Combination;
import com.master.upm.Mastermind.FeedbackCode;
import com.master.upm.TerminalMastermind.CodeToStringAdapter;

import org.junit.Test;

public class TerminalViewTest
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void ValidityOperateCorrectly() {
        String validColorInput      = "rybo";
        String invalidColorInput    = "ññsdFw";

        assertTrue(
            CodeToStringAdapter.isValidColorString(validColorInput));
        assertFalse(
            CodeToStringAdapter.isValidColorString(invalidColorInput));
    }

    @Test
    public void canConvertCombinationToAndFromString() {
        String validColors = "rgb";
        Combination combo =
            CodeToStringAdapter.getCombinationFromString(validColors);

        ArrayList<Color> comboColors = combo.getColors();
        assertTrue(combo.getLength() == 3);
        assertTrue(comboColors.size() == 3);

        assertTrue(comboColors.get(0) == Color.RED);
        assertTrue(comboColors.get(1) == Color.GREEN);
        assertTrue(comboColors.get(2) == Color.BLUE);

        String recovered = new String(
            CodeToStringAdapter.getStringFromCombination(combo));

        assertTrue(recovered.contentEquals(validColors));
    }

    @Test
    public void canConvertFeedbackToString() {
        ArrayList<Color> feedbackColors = new ArrayList();
        feedbackColors.add(Color.BLACK);
        feedbackColors.add(Color.WHITE);
        feedbackColors.add(Color.NULL);

        FeedbackCode fc = new FeedbackCode(feedbackColors);

        String converted =
            CodeToStringAdapter.getFeedbackToString(fc);

        assertTrue(converted.contentEquals("1 blacks and 1 whites"));
    }

}
