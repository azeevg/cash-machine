package exceptions;

/**
 * Created by gleb on 4/3/16.
 */
public class EmptyDenominationArrayException extends  IllegalArgumentException{
    public EmptyDenominationArrayException() {
    }

    public EmptyDenominationArrayException(String s) {
        super(s);
    }

    public EmptyDenominationArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}
