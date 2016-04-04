package exceptions;

public class NoCashException extends Exception {
    public NoCashException() {
        super();
    }

    public NoCashException(String message) {
        super(message);
    }

    public NoCashException(String message, Throwable cause) {
        super(message, cause);
    }
}
