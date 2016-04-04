package exceptions;

public class NotUniqueDenominationValuesException extends IllegalArgumentException {
    public NotUniqueDenominationValuesException() {
        super();
    }

    public NotUniqueDenominationValuesException(String s) {
        super(s);
    }

    public NotUniqueDenominationValuesException(String message, Throwable cause) {
        super(message, cause);
    }
}
