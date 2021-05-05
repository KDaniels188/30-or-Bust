package com.company;

//Kurtis Daniels
//Computer Science 160 Section 178
//created 4/14/21
//Created to complete the semester final project

//create a new class called die to represent a die and its capabilities
public class Die
{

    //a method to allow for rolling the die
    public int roll()
    {
        //return an integer between one and 6 inclusive
        return ((int)(Math.random() * 6)) + 1;
    }
}
