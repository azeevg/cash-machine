package exceptions;

public class NotPositiveValueException extends IllegalArgumentException {
    public NotPositiveValueException() {
        super();
    }

    public NotPositiveValueException(String s) {
        super(s);
    }

    public NotPositiveValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
