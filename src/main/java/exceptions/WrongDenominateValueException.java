package exceptions;

public class WrongDenominateValueException extends IllegalArgumentException {
    public WrongDenominateValueException() {
        super();
    }

    public WrongDenominateValueException(String s) {
        super(s);
    }

    public WrongDenominateValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
