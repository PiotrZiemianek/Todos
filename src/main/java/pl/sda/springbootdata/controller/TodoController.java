package pl.sda.springbootdata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.springbootdata.domain.Todo;
import pl.sda.springbootdata.dto.TodoToCreate;
import pl.sda.springbootdata.service.TodoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;

    @GetMapping
    public List<Todo> getTodos() {
        return service.getAllTodos();
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable Long id) {
        return service.getTodo(id);
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@Valid TodoToCreate todoToCreate) {
        Todo createdTodo = service.createTodo(todoToCreate);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTodo);
    }

    @GetMapping("/page")
    public Page<Todo> getPage(@RequestParam Integer number, @RequestParam Integer size) {
        return service.getPage(number, size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {

        service.deleteTodo(id);
        return ResponseEntity.noContent().build();

    }
}
