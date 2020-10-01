package pl.sda.springbootdata.exceptionUtils;

public class TodoNotFindException extends RuntimeException {
    public TodoNotFindException(String message) {
        super(message);
    }
}
