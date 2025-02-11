package exceptions;

public class InvalidTagException extends Exception {
    InvalidTagException(String message) {
        super(message);
        System.out.println("Here are the valid commands: \n1. deadline \n2. event \n3. todo \n4. list \n5.bye \n  KEEP TO EM LAD \n");
    }
}