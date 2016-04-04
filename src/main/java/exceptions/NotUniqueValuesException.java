package exceptions;

public class NotUniqueValuesException extends IllegalArgumentException {
    public NotUniqueValuesException() {
        super();
    }

    public NotUniqueValuesException(String s) {
        super(s);
    }

    public NotUniqueValuesException(String message, Throwable cause) {
        super(message, cause);
    }
}
