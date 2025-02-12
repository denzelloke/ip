package Shrek.exceptions;

public class InvalidSplitException extends Exception {
    public InvalidSplitException () {
        super();
    }

    public static void handle() {
        System.err.println("DID YER CHECK YOUR \"/by\" AND \"/from\" INPUTS LAD?? \n");
    }
}