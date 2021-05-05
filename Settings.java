package com.company;

public class Settings
{
    //define booleans to represent the settings
    boolean instructionText = true;
    boolean typePrint = true;

    //a getter for instructionText's state
    public boolean isInstructionText() {
        return instructionText;
    }

    //a setter for instructionText capability
    public void setInstructionText(boolean instructionText) {
        this.instructionText = instructionText;
    }

    //a getter for typePrint's state
    public boolean isTypePrint() {
        return typePrint;
    }

    //a setter method for typePrint capability
    public void setTypePrint(boolean typePrint) {
        this.typePrint = typePrint;
    }

    //a method that will do something depending on the state of instructionText
    public void instructionTextPrint(String printable)
    {
        //if instructionText is enabled...
        if (this.instructionText)
        {
            //concatenate the instruction snippet to the given string and print it
            printable = printable.concat("\n\nTo select an option, type the character(s) that appear in the square brackets.\nFor instance, if you wanted to select option [A], you would simply type A.\nCase does not matter.\n");
            typePrint(printable);
        }
        //if instruction text is not enabled...
        else
        {
            //print the message without instruction text
            typePrint(printable);
        }
    }

    //a method that will do something depending on the state of typePrint
    public void typePrint(String toPrint)
    {
        //if type printing is enabled...
        if (this.typePrint)
        {
            //for each character in the input string...
            for (int i = 0; i < toPrint.length(); i++)
            {
                //print the character and wait a designated time before printing the next one
                System.out.printf("%c", toPrint.charAt(i));
                int delay;
                switch(toPrint.charAt(i))
                {
                    case '!':
                    case '?':
                    case'.':
                        delay = 200;
                        break;
                    case ',':
                        delay = 100;
                        break;
                    default:
                        delay = 10;
                        break;
                }

                //handle the delay between characters. this will try to delay and has a feature to catch an exception
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        //if type printing is not enabled...
        else
        {
            //print the message normally
            System.out.print(toPrint);
        }
    }
}
