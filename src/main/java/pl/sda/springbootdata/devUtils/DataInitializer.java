package pl.sda.springbootdata.devUtils;

import io.codearte.jfairy.Fairy;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.sda.springbootdata.domain.Todo;
import pl.sda.springbootdata.repository.TodoRepository;

@Component
@Profile("dev")
public class DataInitializer implements ApplicationRunner {
    private final TodoRepository repository;

    public DataInitializer(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Fairy fairy = Fairy.create();
        for (int i = 0; i < 100; i++) {
            repository.save(new Todo(
                    fairy.company().getName(),
                    fairy.person().getFullName()));
        }

//        Page<Todo> todoPage = repository.findAll(PageRequest.of(2, 10,
//                Sort.by(Sort.Direction.DESC, "title")));
//        System.out.println(todoPage.getTotalElements());
//        System.out.println(todoPage.getTotalPages());
//        todoPage.getContent().forEach(System.out::println);
//        System.out.println(todoPage.getNumber());
//        System.out.println(todoPage.getSize());
//
//        repository.findTodosByTitleQuery("SuperMemo")
//                .forEach(System.out::println);

    }
}
