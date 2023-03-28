package project.laundry.exception;

public class DataIntegrityViolationException extends RuntimeException {
    public DataIntegrityViolationException() {
        super();
    }

    public DataIntegrityViolationException(String message) {
        super(message);
    }

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIntegrityViolationException(Throwable cause) {
        super(cause);
    }
}
