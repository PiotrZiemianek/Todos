package pl.sda.springbootdata.todo;

public class TodoNotFindException extends RuntimeException {
    public TodoNotFindException(String message) {
        super(message);
    }
}
