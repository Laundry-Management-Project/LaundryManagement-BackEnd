package project.laundry.exception;

public class FormNullPointerException extends RuntimeException {

    public FormNullPointerException() {
        super();
    }

    public FormNullPointerException(String message) {
        super(message);
    }

    public FormNullPointerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormNullPointerException(Throwable cause) {
        super(cause);
    }
}
