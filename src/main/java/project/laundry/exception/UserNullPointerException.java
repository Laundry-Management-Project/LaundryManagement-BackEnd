package project.laundry.exception;

public class UserNullPointerException extends RuntimeException {
    public UserNullPointerException() {
        super();
    }

    public UserNullPointerException(String message) {
        super(message);
    }

    public UserNullPointerException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNullPointerException(Throwable cause) {
        super(cause);
    }
}
