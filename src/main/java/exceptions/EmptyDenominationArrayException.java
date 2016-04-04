package exceptions;

public class EmptyDenominationArrayException extends IllegalArgumentException {
    public EmptyDenominationArrayException() {
        super();
    }

    public EmptyDenominationArrayException(String s) {
        super(s);
    }

    public EmptyDenominationArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}
