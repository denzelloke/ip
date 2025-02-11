package exceptions;

public class InvalidSplitException extends Exception {
    InvalidSplitException (String message) {
        super(message);
        System.out.println("DID YER CHECK YOUR /by AND /from INPUTS LAD?? \n");
    }
}