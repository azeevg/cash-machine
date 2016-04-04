package exceptions;

public class NotDecreasingDenominationValuesException extends IllegalArgumentException {
    public NotDecreasingDenominationValuesException() {
        super();
    }

    public NotDecreasingDenominationValuesException(String s) {
        super(s);
    }

    public NotDecreasingDenominationValuesException(String message, Throwable cause) {
        super(message, cause);
    }
}
