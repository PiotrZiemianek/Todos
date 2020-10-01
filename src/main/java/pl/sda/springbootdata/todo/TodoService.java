package pl.sda.springbootdata.todo;

import org.springframework.data.domain.Page;

import java.util.List;


public interface TodoService {

    List<Todo> getAllTodos();

    Todo getTodo(Long id) throws TodoNotFindException;

    void deleteTodo(Long id) throws TodoNotFindException;

    Page<Todo> getPage(Integer number, Integer size);

    Todo createTodo(TodoToCreate todoToCreate);
}
