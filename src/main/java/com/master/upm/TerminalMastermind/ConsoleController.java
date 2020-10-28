package com.master.upm.TerminalMastermind;

import java.util.Scanner;

import javax.print.PrintService;

import com.master.upm.Mastermind.Board;
import com.master.upm.Mastermind.FeedbackCode;
import com.master.upm.Mastermind.Combination;
import java.util.ArrayList;

public final class ConsoleController {

    private Board mastermindBoard;
    private final int maxAttemptsCount;
    private Scanner reader;

    private String askForNewCombination() {
        String userInput = "";
        boolean notCorrectCode = true;

        // Ask a combination until a valid one is inserted.
        while (notCorrectCode)
        {
            System.out.print("Propose a combination: ");
            userInput = reader.next();

            if (!CodeToStringAdapter.isValidColorString(userInput))
                printErrorWrongColors();
            else if (userInput.length() != mastermindBoard.getCodeLength())
                printErrorWrongLength();
            else
                notCorrectCode = false;
        }
        return userInput;
    }

    private static void printCodeShadow(int codeLength) {
        String shadow = "";
        for (int i=0; i<codeLength; ++i)
            shadow += "*";
        System.out.println(shadow);
    }

    private void printBoardState() {
        ArrayList<Combination> insertedCombos = mastermindBoard.getCombinationList();
        ArrayList<FeedbackCode> currentFeedbacks = mastermindBoard.getFeedbackList();

        for (int i=0; i<mastermindBoard.getAttempts(); ++i) {
            System.out.print(
                CodeToStringAdapter.getStringFromCombination(
                    insertedCombos.get(i)
                )
            );
            System.out.print(" --> ");
            System.out.println(
                CodeToStringAdapter.getFeedbackToString(
                    currentFeedbacks.get(i)
                )
            );
        }
    }

    private static void printErrorWrongLength() {
        System.out.println("Wrong proposed combination length");
    }

    private static void printErrorWrongColors() {
        System.out.println("Wrong colors, they must be: "
            + CodeToStringAdapter.getDefinedColors());
    }

    private static void printWinning() {
        System.out.println("You have won!!! ;-)");
    }

    private static void printLossing() {
        System.out.println("You have lost!! :-(");
    }

    private boolean askResumeGame() {
        boolean validInput = false;
        String answer = "";

        while (!validInput) {
            System.out.print("RESUME? (y/n): ");
            answer = reader.next();
            validInput = answer.contentEquals("y") || answer.contentEquals("n");
        }
        return answer.contentEquals("y");
    }

    public ConsoleController(final int codeLength, final int maxAttempts) {
        this.reader = new Scanner(System.in);
        this.maxAttemptsCount = maxAttempts;
        this.mastermindBoard = new Board(codeLength);
    }

    public void startGameLoop() {
        int attempts = 0;
        boolean solutionFound = false;
        String combo;
        System.out.println("----- MASTERMIND -----");
        do {
            do {
                System.out.println();
                System.out.println(String.format("%d attempt(s):", attempts));
                printCodeShadow(mastermindBoard.getCodeLength());
                this.printBoardState();

                combo = askForNewCombination();
                FeedbackCode feedback = mastermindBoard.pushNewCombination(
                    CodeToStringAdapter.getCombinationFromString(combo)
                );

                solutionFound = feedback.isAllBlacks();
                attempts = mastermindBoard.getAttempts();

                System.out.println();
            } while (attempts < maxAttemptsCount && !solutionFound);

            System.out.println();
            System.out.println(String.format("%d attempt(s):", attempts));
            printCodeShadow(mastermindBoard.getCodeLength());
            this.printBoardState();

            if (solutionFound)
                ConsoleController.printWinning();
            else
                ConsoleController.printLossing();
            mastermindBoard = new Board(mastermindBoard.getCodeLength());
            attempts = 0;
        } while (askResumeGame());
        reader.close();
    }
}