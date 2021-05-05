package com.company;

//Kurtis Daniels
//Computer Science 160 Section 178
//created 4/14/21
//Created to complete the semester final project

//define a new class called Player that will be used to store the information for each player
public class Player
{
    //define the data fields of te class, which are the player's name, an array to store the dice rolls they make, and their total score
    String name;
    int[] rolls = new int[60];
    int[] totalOfRolls = new int[30];
    int score = 0;
    int[] scorePerTurn = new int[30];
    String[] selection = new String[30];
    int roundsSinceBust = 0;

    //a getter for scorePerTurn
    public int[] getScorePerTurn() {
        return scorePerTurn;
    }

    public void setScorePerTurn() {
        this.scorePerTurn[roundsSinceBust] = score;
    }

    public String[] getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection[roundsSinceBust - 1] = selection;
    }

    //a getter for the getTotalOfRolls data field
    public int[] getTotalOfRolls() {
        return totalOfRolls;
    }

    //a setter for the totalOfRolls field
    public void setTotalOfRolls(int totalOfRolls) {
        this.totalOfRolls[roundsSinceBust] = totalOfRolls;
    }

    //a getter for the roundsSince bust field
    public int getRoundsSinceBust() {
        return roundsSinceBust;
    }

    //a setter for the roundsSinceBust data field
    public void setRoundsSinceBust(int roundsSinceBust) {
        this.roundsSinceBust = roundsSinceBust;
    }

    //the constructor for the player object, accepts a string that is the player's name
    public Player(String name)
    {
        this.name = name;
    }

    //a getter for the rolls array data field
    public int[] getRolls()
    {
        return rolls;
    }

    //a setter for the rolls array data field
    public void setRolls(int roll1, int roll2)
    {
        this.rolls[(2*this.roundsSinceBust)] = roll1;
        this.rolls[(2*(this.roundsSinceBust)+1)] = roll2;
    }

    //a getter for the score integer data field
    public int getScore()
    {
        return score;
    }

    //a setter for the score integer data field
    public void setScore(int score)
    {
        this.score = score;
    }

    //a getter for the name String data field
    public String getName()
    {
        return name;
    }

    //a setter for the name String data field
    public void setName(String name)
    {
        this.name = name;
    }

    //a method to call when the player busts
    public void bust()
    {
        this.score = 0;
        this.rolls = new int[60];
        this.roundsSinceBust = 0;
        this.totalOfRolls = new int[30];
        this.scorePerTurn = new int[30];
        this.selection = new String [30];
    }

    //a method to retrieve a specific roll
    public int getRollsAt (int index)
    {
        return rolls[index];
    }

    //a method to return what the player's score was at a specified turn
    public int getScoreAtTurn (int turn)
    {
        return scorePerTurn[turn];
    }

    //a method to get the player's selected choice at a specified turn
    public String getSelectionAtTurn(int turn)
    {
        return selection[turn];
    }

    //a method yo return what the total of the rolls was at a designated turn
    public int getTotalAtTurn (int turn)
    {
        return totalOfRolls[turn];
    }
}
