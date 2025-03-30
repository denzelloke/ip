package Shrek.exceptions;

public class InvalidTagException extends Exception {
    public InvalidTagException() {
    }

    public static void handle() {
         System.err.println("\nC'mon Lad, here are the valid commands: \n" +
                 "1. deadline [description] /by [time] \n" +
                 "2. event [description] /from [time]\n" +
                 "3. todo [description] \n" +
                 "4. mark/unmark [index] \n" +
                 "5. list \n" +
                 "6. find [keyword] \n" +
                 "7. bye \n" +
                 "KEEP TO EM LAD \n");
    }
}