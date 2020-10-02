package pl.sda.springbootdata.exception;

public class TodoNotFindException extends RuntimeException {
    public TodoNotFindException(String message) {
        super(message);
    }
}
