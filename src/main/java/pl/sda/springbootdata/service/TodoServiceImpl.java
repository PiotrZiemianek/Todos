package pl.sda.springbootdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.sda.springbootdata.domain.Todo;
import pl.sda.springbootdata.dto.TodoToCreate;
import pl.sda.springbootdata.exception.TodoNotFindException;
import pl.sda.springbootdata.repository.TodoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository repository;

    @Override
    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    @Override
    public Todo getTodo(Long id) throws TodoNotFindException {

        return repository.findById(id)
                .orElseThrow(() ->
                        new TodoNotFindException("Todo with id: " + id + " not found"));
    }

    @Override
    public void deleteTodo(Long id) throws TodoNotFindException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new TodoNotFindException("Todo with id: " + id + " not found");
        }
    }

    @Override
    public Page<Todo> getPage(Integer number, Integer size) {
        return repository.findAll(PageRequest.of(number, size));
    }

    @Override
    public Todo createTodo(TodoToCreate todoToCreate) {
        return repository.save(Todo.builder()
                .author(todoToCreate.getAuthor())
                .title(todoToCreate.getTitle())
                .build());
    }
}
