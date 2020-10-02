package pl.sda.springbootdata.service;

import org.springframework.data.domain.Page;
import pl.sda.springbootdata.domain.Todo;
import pl.sda.springbootdata.dto.TodoToCreate;
import pl.sda.springbootdata.dto.TodoToGet;
import pl.sda.springbootdata.exception.TodoNotFindException;

import java.util.List;


public interface TodoService {

    List<TodoToGet> getAllTodos();

    TodoToGet getTodo(Long id) throws TodoNotFindException;

    void deleteTodo(Long id) throws TodoNotFindException;

    List<TodoToGet> getPage(Integer number, Integer size);

    TodoToGet createTodo(TodoToCreate todoToCreate, String userNickName);
}
