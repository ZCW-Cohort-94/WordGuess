package com.github.zipcodewilmington.sample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Wordguess {
    public static void gameRun() {
        gameInit();
        String secretWord = getRandomWord().toLowerCase();
        char[] guessArray = {'-', '-', '-', '-'};
        System.out.println(guessArray);
        int numGuesses = secretWord.length();
        System.out.println("You have " + numGuesses + " guesses remaining!");

        boolean gameOver = false;

        while (gameOver == false) {
            System.out.println("Enter a letter!");
            Scanner prompt = new Scanner(System.in);
            char guess = prompt.next().charAt(0);
            if (numGuesses == 1) {
                gameOver = true;
                System.out.println("You have lost!");
                System.out.println("The secret word was " + secretWord);
            }

            char[] answerArray = secretWord.toCharArray();
            for (int i = 0; i < answerArray.length; i++) {
                if (answerArray[i] == guess) {
                    guessArray[i] = guess;
                }
            }
            System.out.println(guessArray);

            if (guessArray[0] != guess || guessArray[1] != guess || guessArray[2] != guess || guessArray[3] != guess) {
                numGuesses--;
            }
        }

    }
    //Jake Lawhorne


    public static void gameInit() {

        System.out.println("Let's play Word Guess!");
    }

    public static ArrayList<String> generateWordArrayList() {
        ArrayList<String> wordList = new ArrayList<>();


        try {
            Scanner fileIn = new Scanner(new File(
                    "/Users/jake/Desktop/Projects/WordGuess/src/main/java/com/github/zipcodewilmington/sample/words.txt"));

            while (fileIn.hasNext()) {
                String lineIn = fileIn.nextLine();
                wordList.add(lineIn);
            }

        } catch (IOException e) {
            System.out.println("File not found");

        }
        return wordList;
    }

    public static String getRandomWord() {
        int RandomWordLine = (int) (Math.random() * 69 + 1);
        ArrayList<String> wordList = generateWordArrayList();
        String randomWord = wordList.get(RandomWordLine);

        return randomWord;
    }

    public static boolean playAgain() {
        System.out.println("Care to play again? y/n");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        do {
            gameRun();
        } while (playAgain());

    }
}