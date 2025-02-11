package exceptions;

public class InvalidTimeException extends Exception {
    InvalidTimeException(String message) {
        super(message);
        System.out.println("INVALID TIME LAD, WHEN DO YER WANT BE TO SCHDULE THAT TASK?\n");
    }
}