package Shrek.exceptions;

public class InvalidTagException extends Exception {
    public InvalidTagException() {
    }

    public static void handle() {
         System.err.println("\nC'mon Lad, here are the valid commands: \n1. deadline [description] /by [time] \n2. event [description] /from [time]\n3. todo [description] \n4. mark/unmark \n5. list \n6. bye \nKEEP TO EM LAD \n");
    }
}