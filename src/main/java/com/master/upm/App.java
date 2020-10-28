package com.master.upm;

import java.io.Console;

import com.master.upm.TerminalMastermind.ConsoleController;

public class App
{
    public static void main( String[] args )
    {
        final int MAX_ATTEMPTS = 10;
        final int CODE_LEN = 4;

        ConsoleController game = new ConsoleController(CODE_LEN, MAX_ATTEMPTS);
        game.startGameLoop();
    }

}
