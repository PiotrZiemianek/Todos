package pl.sda.springbootdata.service;

import org.springframework.stereotype.Service;
import pl.sda.springbootdata.domain.AppUser;
import pl.sda.springbootdata.domain.Todo;
import pl.sda.springbootdata.dto.TodoToCreate;
import pl.sda.springbootdata.dto.TodoToGet;

@Service
public class TodoMapper {
    public TodoToGet toGet(Todo todo) {
        return TodoToGet.of(
                todo.getId(),
                todo.getTitle(),
                todo.getContent(),
                todo.getAuthor().getFullName());
    }

    public Todo toTodo(TodoToCreate todoToCreate, AppUser user) {
        return Todo.builder()
                .title(todoToCreate.getTitle())
                .content(todoToCreate.getContent())
                .author(user)
                .build();
    }
}
