package com.github.zipcodewilmington;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author xt0fer
 * @version 1.0.0
 * @date 5/27/21 11:02 AM
 */
public class Hangman {
    public String secretWord = "";
    char[] secretWordArray;
    public int guessCounter = 0;
    public String letterGuess = "";
    public String correctGuessString = "";
    public String[] wordChoice = {"boat", "done", "more", "cart"};
    public String[] wordProgress = {"_","_","_","_"};

    public int revealedIndex = -1;
    public void updateGuessNoti(){
        System.out.println("You now have " + (10 - guessCounter) + " guesses left");
    }


    public void gameoverScreen(){
        System.out.println("You have ran out of guesses!!!" + "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "███▀▀▀██┼███▀▀▀███┼███▀█▄█▀███┼██▀▀▀\n" +
                "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼█┼┼┼██┼██┼┼┼\n" +
                "██┼┼┼▄▄▄┼██▄▄▄▄▄██┼██┼┼┼▀┼┼┼██┼██▀▀▀\n" +
                "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██┼┼┼\n" +
                "███▄▄▄██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██▄▄▄\n" +
                "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "███▀▀▀███┼▀███┼┼██▀┼██▀▀▀┼██▀▀▀▀██▄┼\n" +
                "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██▀▀▀┼██▄▄▄▄▄▀▀┼\n" +
                "██┼┼┼┼┼██┼┼┼██┼┼█▀┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                "███▄▄▄███┼┼┼─▀█▀┼┼─┼██▄▄▄┼██┼┼┼┼┼██▄\n" +
                "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼████▄┼┼┼▄▄▄▄▄▄▄┼┼┼▄████┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼▀▀█▄█████████▄█▀▀┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼█████████████┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼██▀▀▀███▀▀▀██┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼██┼┼┼███┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼█████▀▄▀█████┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼┼███████████┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼▄▄▄██┼┼█▀█▀█┼┼██▄▄▄┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼▀▀██┼┼┼┼┼┼┼┼┼┼┼██▀▀┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼\n" +
                "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n");
    }

    public void playAgain(){
        System.out.println("Keep playing? Y/N");
        Scanner scan = new Scanner(System.in);
        String keepPlaying = scan.nextLine().toLowerCase();
        if(keepPlaying.equals("y")){
            System.out.println("Okay! get ready!!!");
            revealedIndex = -1;
            correctGuessString = "";
            guessCounter = 0;
            wordProgress[0] = "_";
            wordProgress[1] = "_";
            wordProgress[2] = "_";
            wordProgress[3] = "_";
            welcome();
        }else if(keepPlaying.equals("n")){
            System.out.println("Okay! see you again another time!");
        }

    }

    public void answerGauge(){
        if(correctGuessString.length()==4){
            System.out.println("You Win! The word was "+ secretWord);
            System.out.println("Thanks for playing");
            playAgain();
        }else{
            getGuess();
        }
    }

    public void welcome(){
        Random rand = new Random();
        int randNum = rand.nextInt(3);
        secretWord = wordChoice[randNum];
        secretWordArray = secretWord.toCharArray();
        System.out.println("Welcome to hangman");
        getGuess();
    }
    public void getGuess(){
        System.out.println("Enter a letter!: ");
        Scanner scan = new Scanner(System.in);
        letterGuess = scan.nextLine();
        getComparison();

    }
    public void getComparison(){
        char temp = letterGuess.charAt(0);
        for(int j = 0; j<correctGuessString.length(); j++){
            if (temp == correctGuessString.charAt(j)){
                System.out.println("you have already guessed this letter! try again");
                revealedIndex = 5;
                getGuess();
            }
        }
        //temp == secretWord.charAt(i)
        for(int i = 0; i<secretWord.length();i++) {
            if (secretWordArray[i] == temp) {
                revealedIndex = i;
                correctGuessString += letterGuess;
            }
        }
        displayProgress();
    }
    public void displayProgress(){
        String s = secretWord;
        if(revealedIndex>=0){
            System.out.println("Correct! that letter is contained in the word!");
        }else{
            System.out.println("So sorry! that letter is not in the word");
        }
        if(revealedIndex == 0){
            wordProgress[0] = letterGuess;
            System.out.println(Arrays.toString(wordProgress));
            revealedIndex = -1;
        }else if(revealedIndex == 1){
            wordProgress[1] = letterGuess;
            System.out.println(Arrays.toString(wordProgress));
            revealedIndex = -1;
        }else if(revealedIndex == 2){
            wordProgress[2] = letterGuess;
            System.out.println(Arrays.toString(wordProgress));
            revealedIndex = -1;
        }else if(revealedIndex == 3){
            wordProgress[3] = letterGuess;
            System.out.println(Arrays.toString(wordProgress));
            revealedIndex = -1;
        }
        guessCounter += 1;
        if(guessCounter == 10){
            gameoverScreen();
        }else{
            updateGuessNoti();
            answerGauge();
        }

    }
    public static void main(String[] args){
        Hangman hm = new Hangman();
        hm.welcome();
    }
}