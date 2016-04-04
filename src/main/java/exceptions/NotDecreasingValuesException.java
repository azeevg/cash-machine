package exceptions;

public class NotDecreasingValuesException extends IllegalArgumentException {
    public NotDecreasingValuesException() {
        super();
    }

    public NotDecreasingValuesException(String s) {
        super(s);
    }

    public NotDecreasingValuesException(String message, Throwable cause) {
        super(message, cause);
    }
}
