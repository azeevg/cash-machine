package exceptions;

public class EmptyCashMachineException extends Exception {
    public EmptyCashMachineException() {
        super();
    }

    public EmptyCashMachineException(String message) {
        super(message);
    }

    public EmptyCashMachineException(String message, Throwable cause) {
        super(message, cause);
    }
}
