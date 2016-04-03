package exceptions;

/**
 * Created by gleb on 4/3/16.
 */
public class NegativeDenominationException extends IllegalArgumentException {
    public NegativeDenominationException() {
        super();
    }

    public NegativeDenominationException(String s) {
        super(s);
    }

    public NegativeDenominationException(String message, Throwable cause) {
        super(message, cause);
    }
}
