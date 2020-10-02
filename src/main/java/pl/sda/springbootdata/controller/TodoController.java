package pl.sda.springbootdata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.sda.springbootdata.domain.Todo;
import pl.sda.springbootdata.dto.TodoToCreate;
import pl.sda.springbootdata.dto.TodoToGet;
import pl.sda.springbootdata.service.TodoService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;

    @GetMapping
    public List<TodoToGet> getTodos() {
        return service.getAllTodos();
    }

    @GetMapping("/{id}")
    public TodoToGet getTodo(@PathVariable Long id) {
        return service.getTodo(id);
    }

    @PostMapping
    public ResponseEntity<TodoToGet> addTodo(@Valid TodoToCreate todoToCreate, Principal principal) {
        TodoToGet createdTodo = service.createTodo(todoToCreate, principal.getName());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTodo);
    }

    @GetMapping("/page")
    public List<TodoToGet> getPage(@RequestParam Integer number, @RequestParam Integer size) {
        return service.getPage(number, size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Long id) {

        service.deleteTodo(id);
        return ResponseEntity.noContent().build();

    }
}
