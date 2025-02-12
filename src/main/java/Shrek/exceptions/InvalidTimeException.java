package Shrek.exceptions;

public class InvalidTimeException extends Exception {
    public InvalidTimeException() {
        super();
    }

    public static void handle () {
        System.err.println("INVALID TIME LAD, WHEN DO YER WANT TO SCHEDULE THAT TASK?\n");
    }
}