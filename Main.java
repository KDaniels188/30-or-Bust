package com.company;


//Kurtis Daniels
//Computer Science 160 Section 178
//created 4/14/21
//Created to complete the semester final project


//import the Scanner class
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        //create two die objects to use during the game
        Die dieOne = new Die();
        Die dieTwo = new Die();

        //create a universal settings object
        Settings settings = new Settings();

        //create a new scanner named input that can accept data from the console
        Scanner input = new Scanner(System.in);

        //create two boolean variables to regulate our gameplay loop.
        boolean win = false;
        boolean continuePlay = true;

        //define a variable to hold the user's response to any prompts
        int userResponse;

        //create an int to store the current round
        int round = 1;

        //create integers to hold the dice values when they're rolled and the sum of the two dice rolls
        int die1;
        int die2;
        int diceTotal;

        //define a string called divider that we can print whenever we want to segment off a chunk of information visually
        String divider = "\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501";

        //define arrays to hold possible phrases for certain situations
        String[] headcountAsk = {"How many players do we have today?", "How many people would like to play?",
                "How many competitors will there be?"};
        String[] confirmation = {"Yes", "No"};
        String[] playInitializer = {"Read the rules", "Begin play"};
        String[] gameCommence = {"Let's jump right in!", "Let the game begin.", "Let's play!"};

        String[][] greeting = {
                {"Hello there, ", "Nice to meet you, ", "Good to have you, "},
                {", good to have you.\n", ". Here's to a good game!\n", ", let's have some fun!\n"}
        };
        String[][] turnQuotes = {
                {"\nIt is ", "\n", "\nYour turn, "},
                {"'s turn.", ", it's your turn.", ""}
        };
        String[] turnStartOptions = {"View history card",  "Roll"};
        String[] consolation = {"Better luck next time!", "You'll get it yet!", "You're still in this!", "Maybe next round will be better!"};
        String[][] congratulation = {
                {"\nCongratulations, ", "\n", "\nWell played ", "\nWe have our winner! It's ", "\nGreat job "},
                {", you've won!", "has won! Great job!", ", you've won!", "!", ", a well earned victory!"}
        };

        //welcome the players to the game and ask for a headcount
        settings.typePrint("Hello, and welcome to 30 or Bust!");

        //start a loop for the program
        while (true) {
            //prompt the player whether they would like to read the rules or play
            userResponse = optionSelect("Would you like to read the rules, begin the game, or change the settings?", playInitializer, settings);

            //if the user would like to read the rules...
            if (userResponse == 0) {

                //print the rules
                settings.typePrint("30 or Bust!\n" + "The goal of the game is to reach a score of exactly 30.\n" + "Each player has two dice. The players take turns rolling their dice. When a player rolls both dice, \nthe player may keep the total of both dice or the face value of either die to add to that players total. \nA player MUST select either the face value of one of the dice, or the total value of both dice. \nIf a player's score goes over 30, then that player's score is reset to zero and play continues until one player \nobtains a score of exactly 30. The first player to score exactly 30 is the Winner.\n");
            }

            //if the user would like to begin the game...
            else if (userResponse == 1) {
                System.out.println(divider);

                //define an integer to hold the desired quantity of players
                int playerCount;

                settings.typePrint(getRandElement(headcountAsk) + " (Please enter an integer value)\n");
                //ask for the number of players that will be playing, and ask again if the entered amount is 0
                boolean acceptableCount = false;
                do {

                    //define an integer to hold the desired quantity of players
                    playerCount = cleanIntInput(input.nextLine());

                    //if the user designated that they wanted one player...
                    if (playerCount == 1) {
                        //prompt the user to confirm whether they'd like to start a game with one player
                        userResponse = optionSelect("Would you like to start a game with 1 player?", confirmation, settings);

                        //if the player chooses yes...
                        if (userResponse == 0) {
                            acceptableCount = true;
                        }
                        //if the player answers no...
                        else if (userResponse == 1) {
                            settings.typePrint(getRandElement(headcountAsk) + " (Please enter an integer value)\n");
                        }
                    }
                    //if the user designated that they wanted multiple players...
                    else {
                        //set up a prompt string to include the number of selected players
                        String playerCountStr = Integer.toString(playerCount);
                        String prompt = "Would you like to start a game with " + playerCountStr + " players?";

                        //prompt the user to confirm whether they'd like to start a game with playerCount players
                        userResponse = optionSelect(prompt, confirmation, settings);

                        //if the player chooses yes...
                        if (userResponse == 0) {
                            acceptableCount = true;
                        }
                        //if the player answers no...
                        else if (userResponse == 1) {
                            //prompt the use to enter the amount of players they would like to be in the game
                            settings.typePrint(getRandElement(headcountAsk) + " (Please enter an integer value)\n");
                        }
                    }
                } while (!acceptableCount);

                System.out.println(divider);

                //create a list of player names to feed to player objects
                String[] playerNames = new String[playerCount];

                //for each player...
                for (int i = 0; i < playerNames.length; i++) {
                    //ask the player for their preferred name, and allow them to modify if they made a typo
                    boolean acceptableName = false;
                    do {

                        //prompt the player to choose a name
                        settings.typePrint("Player " + (i + 1) + ", what would you like to be called?\n");

                        //allow the user to input a name
                        String name = input.nextLine();

                        //ask if the name is acceptable and give them the option to confirm
                        String prompt = "Is the name \"" + name + "\" acceptable?";
                        int playerResponse = optionSelect(prompt, confirmation, settings);

                        //if the name is alright...
                        if (playerResponse == 0) {
                            //welcome the player to the game, break the loop, and put the name in a list
                            settings.typePrint(getRandElement(greeting[0]) + name + getRandElement(greeting[1]));
                            playerNames[i] = name;
                            acceptableName = true;
                        }
                    } while (!acceptableName);

                    System.out.println(divider);
                }

                //initialize an array of players based on the number of players the user defined
                Player[] players = new Player[playerCount];
                for (int i = 0; i < players.length; i++) {
                    players[i] = new Player(playerNames[i]);
                }

                //alert the players that the game is starting
                settings.typePrint(getRandElement(gameCommence));
                System.out.println("\n" + divider);

                //execute the following and repeat if the players would like to play again
                do {

                    //execute the following code repeatedly until someone wins
                    do {

                        for (int index = 0; index < players.length; index++) {
                            //inform the current player that it is their turn
                            int turnQuotesIndex = ((int) (Math.random() * turnQuotes[1].length));
                            System.out.println(turnQuotes[0][turnQuotesIndex] + players[index].getName() + turnQuotes[1][turnQuotesIndex]);

                            boolean turnOver = false;
                            do {
                                //offer the players their action choices
                                int playerResponse = optionSelect("What would you like to do?", turnStartOptions, settings);

                                //if the player wants to check their history card...
                                if (playerResponse == 0) {
                                    //if the player hasn't taken a turn since their last bust...
                                    if (players[index].getRoundsSinceBust() == 0)
                                    {
                                        //tell the player that there is nothing to display
                                        settings.typePrint("No information to display yet!\n");
                                    }
                                    //if the player has taken a turn since their last bust...
                                    else
                                    {
                                        //display the roll and action history for the player
                                        printScoreSheet(players[index], round, settings);
                                    }
                                }
                                //if the player would like to roll the dice
                                else if (playerResponse == 1)
                                {
                                    //tell the system that the player's turn will be over after this action
                                    turnOver = true;

                                    //roll the dice, and total the two numbers
                                    die1 = dieOne.roll();
                                    die2 = dieTwo.roll();
                                    diceTotal = die1 + die2;

                                    //record information to the player
                                    players[index].setRolls(die1, die2);
                                    players[index].setTotalOfRolls(diceTotal);
                                    players[index].setScorePerTurn();
                                    players[index].setRoundsSinceBust(players[index].getRoundsSinceBust() + 1);


                                    //generate an options string to feed to optionSelect
                                    String die1String = "Die one: " + die1;
                                    String die2String = "Die two: " + die2;
                                    String diceTotalString = "Total Value: " + diceTotal;
                                    String[] options = {die1String, die2String, diceTotalString};

                                    //set up a prompt for the player
                                    String prompt = "Which of the following would you like to keep? Your current score is " + players[index].getScore();

                                    //prompt the player on what they would like to keep
                                    playerResponse = optionSelect(prompt, options, settings);

                                    //if the player would like to keep roll one...
                                    if (playerResponse == 0) {
                                        players[index].setScore(players[index].getScore() + die1);
                                        players[index].setSelection("Die1");
                                    }
                                    //if the player would like to keep roll two...
                                    else if (playerResponse == 1) {
                                        players[index].setScore(players[index].getScore() + die2);
                                        players[index].setSelection("Die2");
                                    }
                                    //if the player would like to keep the total of the rolls...
                                    else if (playerResponse == 2) {
                                        players[index].setScore(players[index].getScore() + diceTotal);
                                        players[index].setSelection("total");
                                    }

                                    settings.typePrint("\n" + players[index].getName() + ", your score is now " + players[index].getScore());

                                    //check to see if the player has busted
                                    if (players[index].getScore() > 30) {
                                        //inform the player that they busted and reset their roll and action history
                                        settings.typePrint(" and you busted! " + getRandElement(consolation));
                                        players[index].bust();
                                        System.out.println("\n" + divider);
                                    }
                                    //check to see if the player has won
                                    else if (players[index].getScore() == 30) {
                                        //
                                        settings.typePrint(".");
                                        int congratIndex = ((int) (Math.random() * congratulation[1].length));
                                        System.out.print("\n" + divider);
                                        settings.typePrint(congratulation[0][congratIndex] + players[index].getName() + congratulation[1][congratIndex]);
                                        System.out.print("\n" + divider + "\n");
                                        win = true;
                                    }
                                    //if neither of the above...
                                    else
                                    {
                                        System.out.println("\n" + divider);
                                    }
                                }
                            }while(!turnOver);

                            //if someone has won, cancel all upcoming turns
                            if (win)
                            {
                                break;
                            }
                        }
                        round++;
                    } while (!win);

                    //ask the user if they would like to play again
                    userResponse = optionSelect("Would you like to play again?", confirmation, settings);

                    //if the user would like to continue playing...
                    if(userResponse == 0)
                    {
                        //set win to false to re-enable the loop
                        win = false;

                        //print the game start text
                        System.out.println(divider);
                        settings.typePrint(getRandElement(gameCommence));
                        System.out.print("\n" + divider + "\n");

                        //reset all the players' info
                        for (int i = 0; i < players.length; i++)
                        {
                            players[i].bust();
                        }

                        //reset the round counter to 1
                        round = 1;

                    }
                    else if (userResponse == 1)
                    {
                        continuePlay = false;
                    }

                } while (continuePlay);
            }
        }
    }

    //a method to return a random element of a given String array
    public static String getRandElement(String[] array)
    {
        return array[((int)(Math.random() * array.length))];
    }

    //a method to accept an input int and format it for program use
    public static int cleanIntInput(String input)
    {
        //create a character array with the same length as the input string
        char[] stringCharStorage = new char[input.length()];

        //for every index value of stringCharStorage
        for (int index = 0; index < stringCharStorage.length; index++)
        {
            //fill the array with the individual characters from the input string
            stringCharStorage[index] = input.charAt(index);
        }

        //define a String variable to hold our cleaned string
        String numString = "";

        //for every character in stringCharStorage
        for(int index = 0; index < stringCharStorage.length; index++)
        {
            switch (stringCharStorage[index])
            {
                //if the character at index is 0-9...
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    //concatenate the character onto the end of numString
                    numString = numString.concat(Character.toString(stringCharStorage[index]));
                    break;
                case '0':
                    //concatenate the character 0 onto the end of numString
                    numString = numString.concat("0");
                    break;

            }
        }

        //if there are no valid integers in the input...
        if (numString.length() == 0)
        {
            //define a variable stating whether or not the given input is valid
            boolean acceptableInput = false;

            //create a scanner instance called inputDetector
            Scanner inputDetector = new Scanner(System.in);

            //inform the user that their input is invalid, and ask for a new input. repeat until a valid input is given
            do {
                System.out.println("Your input of \"" + input + "\" is invalid. Please enter an integer");
                numString = Integer.toString(cleanIntInput(inputDetector.nextLine()));
                if (numString.length() != 0)
                {
                    acceptableInput = true;
                }
            } while (!acceptableInput);

        }
        //check to see if any characters were removed in the process and notify the user of the system's interpretation
        else if (input.length() != numString.length())
        {
            System.out.println("You entered: " + input + ", System interpreted it as: " + numString);
        }

        //parse numString into integer form and return it
        return Integer.parseInt(numString);
    }

    //a method to accept an input string and format it for program use
    public static String cleanStringInput(String input)
    {
        //turn all letters to lowercase letters
        input = input.toLowerCase();

        //define a character array that will be used to store each individual character of input
        char[] stringCharStorage = new char[input.length()];

        //for as many times as input is characters long...
        for (int i = 0; i < input.length(); i++)
        {
            //fill charArray with the individual characters from input
            stringCharStorage[i] = input.charAt(i);
        }

        //create a string variable to store the cleaned version of the input
         String cleanedString = "";

        //for as many times as there are characters in input...
        for (int index = 0; index < stringCharStorage.length; index++)
        {
            //if the character at index index is a - z...
            switch(stringCharStorage[index])
            {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    //concatenates the current character onto the end of cleanedString
                    cleanedString = cleanedString.concat(Character.toString(stringCharStorage[index]));
                    break;
                default:
                    //if the input contains unacceptable characters, set the cleanedString variable to an invalid tag and break the loop
                    cleanedString = "INVALID_INPUT";
                    break;
            }
        }

        return cleanedString;
    }

    //a method to prompt the player and return a value based on their selection
    public static int optionSelect (String prompt, String[] options, Settings settings)
    {
        //create a scanner instance called input
        Scanner input = new Scanner(System.in);

        //define an array of characters we can use to differentiate between options
        String[] selectionCharacters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        //define an integer that will eventually be filled with the index of the user's response from the selectionCharacters list
        int userResponseIndex;

        //run this code and if no valid input is given, run again
        boolean validInput = false;
        do {
            //print out the prompt as well as instructions on how to respond
            System.out.println();
            settings.instructionTextPrint(prompt);

            //print the options in an orderly format
            for (int i = 0; i < options.length; i++)
            {
                System.out.println();
                settings.typePrint("[ " + selectionCharacters[i] + " ]  " + options[i]);
            }

            //print the option to modify settings
            System.out.println();
            settings.typePrint("[SET]  Modify the settings");

            //print the exit option
            System.out.println();
            settings.typePrint("[END]  Close the program");

            //ask for a string from the user and inspect it via CleanedStringInput
            System.out.println();
            String userInput = cleanStringInput(input.nextLine());

            //redefine userResponseIndex to hold the player's response index
            userResponseIndex = getEntryIndex(userInput.toUpperCase(), selectionCharacters, options.length, settings);

            //if userResponseIndex is within the acceptable answer range...
            if (userResponseIndex < options.length)
            {
                //set valid input to true, which will break the do-while loop
                validInput = true;
            }
            //if the system gives the invalid input code...
            else if (userResponseIndex == 999)
            {
                //report that the input is invalid and maintain the loo[
                settings.typePrint("invalid input. Please try again.\n");
            }

        } while(!validInput);
        return userResponseIndex;
    }

    //a method to determine if a desired entry is in an array, and to return its index if it is
    public static int getEntryIndex(String desiredEntry, String[] selectionCharacters, int entriesLimit, Settings settings)
    {
        //define a variable to store the found index that the player selected
        int selectionIndex = 999;

        //if the user selects the "modify settings" option...
        if (desiredEntry.equals("SET"))
        {
            //while the user wants to modify the settings...
            boolean done = false;
            do {

                //set up strings that reflect the current state of the settings and put those strings in an array
                String instructionSnippet = "Display instruction snippets: " + settings.isInstructionText();
                String typePrintAbility = "Allow the system to type text: " + settings.isTypePrint();
                String[] settingOptions = {instructionSnippet, typePrintAbility, "Finish"};

                //prompt the user for a setting to modify
                int userResponse = optionSelect("Select a setting to toggle it.", settingOptions, settings);

                //if the user wants to toggle instruction snippets...
                if (userResponse == 0)
                {
                    //toggle the state of the instructionText
                    settings.setInstructionText(!settings.isInstructionText());
                }
                //if the user wants to toggle type printing...
                else if (userResponse == 1)
                {
                    //toggle the state of typePrint
                    settings.setTypePrint(!settings.isTypePrint());
                }
                //if the user wants to finish modifying the settings...
                else if (userResponse == 2)
                {
                    //break the loop
                    done = true;
                }
            } while (!done);

            //return an out of bounds value that won't trigger the "invalid input" message
            selectionIndex = 998;
        }

        //if the user selects the "close the program" option...
        if (desiredEntry.equals("END"))
        {
            //define an array of goodbyes
            String[] bye = {"See you next time!", "Goodbye, for now.", "Until next time!"};

            //say goodbye to the user
            settings.typePrint("\n" + getRandElement(bye));

            //terminate the program
            System.exit(0);
        }

        //for as long as i is less than the limit of the amount of options to compare...
        for (int i = 0; i < entriesLimit; i++)
        {
            //if the entry is equaled to the desired entry
            if (selectionCharacters[i].equals(desiredEntry))
            {
                selectionIndex = i;
            }
        }

        return selectionIndex;
    }

    //a method to print out the score sheet of a player
    public static void printScoreSheet (Player player, int round, Settings settings)
    {
        //create integers to store table dimensions
        int colOneWidth;
        int colTwoWidth = "total: ".length() + 2;
        int colThreeWidth = "score: ".length() + 2;
        int colFourWidth = "selection: total".length();

        //if round has more than six orders of magnitude...
        if(round > 999999)
        {
            //set the width of column one to the number of orders of magnitude of round
            colOneWidth = ((int)(Math.log10(round)));
        }
        //if round has less than six OoM...
        else
        {
            colOneWidth = 6;
        }

        //an integer holding the overall table length
        int minTableWidth = colOneWidth + colTwoWidth + colThreeWidth + colFourWidth +3;

        //a string with the info for the top of the table
        String headerInfo = player.getName() +"    Current score: " + player.getScore();

        //define an integer that will expand the table if necessary
        int widthCorrector = 0;

        //set the value of the width corrector
        if (minTableWidth < headerInfo.length())
        {
            widthCorrector = headerInfo.length() - minTableWidth;
        }

        //create an integer to store the actual width of the table
        int tableWidth = minTableWidth + widthCorrector;

        //define an array to hold the horizontal lines of the table
        String[] line = {"\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501\u2501",
                "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500",
                "                                                                                                                        "};

        //print a header
        System.out.print("\u250F" + line[0].substring(0,tableWidth) + "\u2513\n" + "\u2503" + headerInfo + line[2].substring(0,(tableWidth - headerInfo.length())) + "\u2503" + "\n");

        String[][][] rowCharacters = {
            {
                {"\u2520", "\u252C", "\u2528"},
                {"\u2503", "\u2502"}
            },
            {
                {"\u2520", "\u253C", "\u2528"},
                {"\u2503", "\u2502"}
            }
        };

        int rowType;
        for (int i = 0; i < player.getRoundsSinceBust(); i++) {
            //create an integer that will be used to collect information from the player's game history arrays
            int roundIndex = player.getRoundsSinceBust() - (player.getRoundsSinceBust() - i);

            //if this is the first line below the heading...
            if (i == 0) {
                //format it so that it looks nice
                rowType = 0;
            }
            //if this is not the first line below the heading...
            else {
                //format it so that it looks nice
                rowType = 1;
            }

            //define the variables to go in the first row
            String die1Val = "Die1: " + player.getRollsAt(2 * roundIndex);
            String die2Val = "Die2: " + player.getRollsAt(2 * roundIndex + 1);
            String totalVal = "total: " + player.getTotalAtTurn(roundIndex);
            String scoreVal = "Score: " + player.getScoreAtTurn(roundIndex);
            String playerSelection = player.getSelectionAtTurn(roundIndex);

            //print a row
            String line1 = rowCharacters[rowType][0][0] + line[1].substring(0, colOneWidth) + rowCharacters[rowType][0][1] + line[1].substring(0, colTwoWidth) + rowCharacters[rowType][0][1] + line[1].substring(0, colThreeWidth) + rowCharacters[rowType][0][1] + line[1].substring(0, colFourWidth + widthCorrector) + rowCharacters[rowType][0][2] + "\n";
            String line2 = rowCharacters[rowType][1][0] + "round:" + line[2].substring(0, colOneWidth - "round:".length()) + rowCharacters[rowType][1][1] + die1Val + line[2].substring(0, colTwoWidth - die1Val.length()) + rowCharacters[rowType][1][1] + scoreVal + line[2].substring(0, colThreeWidth - scoreVal.length()) + rowCharacters[rowType][1][1] + "Selection:" + line[2].substring(0, colFourWidth + widthCorrector - "Selection:".length()) + rowCharacters[rowType][1][0] + "\n";
            String line3 = rowCharacters[rowType][1][0] + (round - (player.getRoundsSinceBust() - i)) + line[2].substring(0, colOneWidth - (((int) Math.log10((round - (player.getRoundsSinceBust() - i)))) + 1)) + rowCharacters[rowType][1][1] + die2Val + line[2].substring(0, colTwoWidth - die2Val.length()) + rowCharacters[rowType][1][1] + line[2].substring(0, colThreeWidth) + rowCharacters[rowType][1][1] + playerSelection + line[2].substring(0, (colFourWidth + widthCorrector) - playerSelection.length()) + rowCharacters[rowType][1][0] + "\n";
            String line4 = rowCharacters[rowType][1][0] + line[2].substring(0, colOneWidth) + rowCharacters[rowType][1][1] + totalVal + line[2].substring(0, colTwoWidth - totalVal.length()) + rowCharacters[rowType][1][1] + line[2].substring(0, colThreeWidth) + rowCharacters[rowType][1][1] + line[2].substring(0, colFourWidth + widthCorrector) + rowCharacters[rowType][1][0] + "\n";

            System.out.print( line1 + line2 + line3 + line4);
        }

        //print the last line of the table
        System.out.print("\u2517" + line[0].substring(0, colOneWidth) + "\u2537" + line[0].substring(0, colTwoWidth) + "\u2537" + line[0].substring(0, colThreeWidth) + "\u2537" + line[0].substring(0, colFourWidth) + "\u251B");
    }
}