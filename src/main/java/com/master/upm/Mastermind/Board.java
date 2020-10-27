package com.master.upm.Mastermind;

import java.util.ArrayList;

public class Board {
    private int length;
    private ArrayList<Combination> pCombinations;
    private ArrayList<FeedbackCode> feedbackCodes;
    private Combination secretCombo;

    public Board(int l) {
        assert l > 0 : "Length cannot be smaller than 1";
        this.length = l;
        pCombinations = new ArrayList();
        feedbackCodes = new ArrayList();

        SecretFactory sf = new SecretFactory(this.length);
        secretCombo = sf.generateSecret();
    }

    public FeedbackCode pushNewCombination(Combination combo) {
        assert combo.getLength() == this.length : "Not equal lengths";

        pCombinations.add(combo);
        FeedbackCode feedback = combo.getFeedbackCodeFromSecret(secretCombo);
        feedbackCodes.add(feedback);
        return feedback;
    }

    public ArrayList<Combination> getCombinationList() {
        return this.pCombinations;
    }

    public ArrayList<FeedbackCode> getFeedbackList() {
        return this.feedbackCodes;
    }

    public int getAttempts() {
        return pCombinations.size();
    }

    public Combination getSecret() {
        return secretCombo;
    }

}