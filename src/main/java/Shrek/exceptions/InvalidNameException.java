package Shrek.exceptions;

public class InvalidNameException extends Exception {
    public InvalidNameException() {
        super();
    }

    public static void handle() {
        System.err.println("YER INPUT HAS AN INVALID NAME! TRY AGAIN BUDDY \n");
    }
}