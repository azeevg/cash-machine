package exceptions;

/**
 * Created by gleb on 4/3/16.
 */
public class NotDecreasingDenominationsException extends IllegalArgumentException {
    public NotDecreasingDenominationsException() {
        super();
    }

    public NotDecreasingDenominationsException(String s) {
        super(s);
    }

    public NotDecreasingDenominationsException(String message, Throwable cause) {
        super(message, cause);
    }
}
