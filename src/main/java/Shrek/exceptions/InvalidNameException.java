package exceptions;

public class InvalidNameException extends Exception {
    InvalidNameException(String message) {
        super(message);
        System.out.println("YER INPUT HAS AN INVALID NAME! TRY AGAIN BUDDY \n");
    }
}