package com.github.zipcodewilmington;


import java.util.Random;
import java.util.Scanner;

/**
 * @author xt0fer
 * @version 1.0.0
 * @date 5/27/21 11:02 AM
 */
public class Hangman {
    public static void main(String[] args) {
        String[] wordGuess = {"cat", "apple", "java", "animal", "zipcode"};
        boolean isPlaying = true;
        while (isPlaying) {
            System.out.println("Let's Play Wordguess version 1.0");
            int randomNumber = new Random().nextInt(wordGuess.length);
            char[] randomWordGuessed = wordGuess[randomNumber].toCharArray();
            int numberOfTriesAllowed = randomWordGuessed.length;
            char[] playerGuess = new char[numberOfTriesAllowed];

            for (int i = 0; i < playerGuess.length; i++) {
                playerGuess[i] = '_';
            }
            isPlaying = playGuessGame(randomWordGuessed, numberOfTriesAllowed, playerGuess);
        }
        System.out.println("Game Over.");
    }

    private static boolean playGuessGame(char[] randomWordGuessed, int numberOfTriesAllowed, char[] playerGuess) {
        boolean isPlaying = true;
        boolean isWordGuessed = false;
        int playerTries = 0;
        Scanner scanner = new Scanner(System.in);
        while (!isWordGuessed && playerTries != numberOfTriesAllowed) {
            System.out.println("Current Guesses: ");
            printMatchingLines(playerGuess);
            System.out.printf("You have %d tries left.\n", numberOfTriesAllowed - playerTries);
            System.out.println("Enter a single character: ");
            char input = scanner.nextLine().charAt(0);
            playerTries = playerTries + 1;

            if (input == '-') {
                isWordGuessed = true;
                isPlaying = false;
            } else {
                for (int i = 0; i < randomWordGuessed.length; i++) {
                    if (randomWordGuessed[i] == input) {
                        playerGuess[i] = input;
                    }
                }

                if (isTheWordGuessed(playerGuess)) {
                    isWordGuessed = true;
                    System.out.println("Congratulations, You Won!");
                }
            }
        }
        if (!isWordGuessed) {
            System.out.println("You Lost! You ran out of guesses.");
        }
        System.out.println("Would you like to play again? (yes/no) ");
        String choice = scanner.nextLine();
        if (choice.equals("no")) {
            isPlaying = false;
        }
        return isPlaying;
    }

    public static void printMatchingLines(char array[]) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static boolean isTheWordGuessed(char[] wordsGuessed) {
        boolean result = true;
        for (int i = 0; i < wordsGuessed.length; i++) {
            if (wordsGuessed[i] == '_') {
                result = false;
            }
        }
        return result;
    }
}
