package pl.sda.springbootdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.sda.springbootdata.domain.AppUser;
import pl.sda.springbootdata.domain.Todo;
import pl.sda.springbootdata.dto.TodoToCreate;
import pl.sda.springbootdata.dto.TodoToGet;
import pl.sda.springbootdata.exception.TodoNotFindException;
import pl.sda.springbootdata.repository.AppUserRepository;
import pl.sda.springbootdata.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final AppUserRepository appUserRepository;
    private final TodoMapper todoMapper;

    @Override
    public List<TodoToGet> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(todoMapper::toGet)
                .collect(Collectors.toList());
    }

    @Override
    public TodoToGet getTodo(Long id) throws TodoNotFindException {

        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            return todoMapper.toGet(optionalTodo.get());
        }
        throw new TodoNotFindException("Todo with id: " + id + " not found");
    }

    @Override
    public void deleteTodo(Long id) throws TodoNotFindException {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
        } else {
            throw new TodoNotFindException("Todo with id: " + id + " not found");
        }
    }

    @Override
    public List<TodoToGet> getPage(Integer number, Integer size) {
        return todoRepository
                .findAll(PageRequest.of(number, size))
                .map(todoMapper::toGet).toList();
    }

    @Override
    public TodoToGet createTodo(TodoToCreate todoToCreate, String userNickName) {
        AppUser author = appUserRepository.findByNickName(userNickName);
        Todo todo = todoRepository.save(todoMapper.toTodo(todoToCreate, author));
        return todoMapper.toGet(todo);
    }
}
