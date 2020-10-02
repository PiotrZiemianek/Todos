package pl.sda.springbootdata.service;

import org.springframework.data.domain.Page;
import pl.sda.springbootdata.domain.Todo;
import pl.sda.springbootdata.dto.TodoToCreate;
import pl.sda.springbootdata.exception.TodoNotFindException;

import java.util.List;


public interface TodoService {

    List<Todo> getAllTodos();

    Todo getTodo(Long id) throws TodoNotFindException;

    void deleteTodo(Long id) throws TodoNotFindException;

    Page<Todo> getPage(Integer number, Integer size);

    Todo createTodo(TodoToCreate todoToCreate);
}
