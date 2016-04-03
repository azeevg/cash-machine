package exceptions;

/**
 * Created by gleb on 4/3/16.
 */
public class NotPositiveDenominationException extends IllegalArgumentException {
    public NotPositiveDenominationException() {
        super();
    }

    public NotPositiveDenominationException(String s) {
        super(s);
    }

    public NotPositiveDenominationException(String message, Throwable cause) {
        super(message, cause);
    }
}
