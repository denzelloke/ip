package Shrek.exceptions;

public class InvalidIndexException extends Exception {
    public InvalidIndexException() {
        super();
    }

    public static void handle() {
        System.err.println("INVALID INDEX!\n try again \n");
    }
}