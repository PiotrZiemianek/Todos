package pl.sda.springbootdata.devUtils;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.text.TextProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.springbootdata.domain.AppUser;
import pl.sda.springbootdata.domain.Todo;
import pl.sda.springbootdata.domain.UserRole;
import pl.sda.springbootdata.repository.AppUserRepository;
import pl.sda.springbootdata.repository.TodoRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder encoder;


    @Override
    public void run(ApplicationArguments args) {
        Fairy fairy = Fairy.create();
        Set<String> uniqueNickNamesSet = new HashSet<>();
        while (uniqueNickNamesSet.size() <= 100) {
            uniqueNickNamesSet.add(fairy.person().getUsername());
        }
        List<String> uniqueNicksList = new ArrayList<>(uniqueNickNamesSet);
        for (int i = 0; i < 100; i++) {
            Person person = fairy.person();
            TextProducer textProducer = fairy.textProducer();
            AppUser user = new AppUser();
            user.setNickName(uniqueNicksList.get(i));
            user.setPassword(encoder.encode(person.getPassword()));
            user.setFirstName(person.getFirstName());
            user.setSurname(person.getLastName());
            user.addRole(UserRole.ROLE_USER);

            for (int j = 0; j < 2; j++) {
                Todo todo = new Todo(
                        textProducer.latinSentence(4),
                        textProducer.latinSentence());

                user.addTodo(todo);
            }
            appUserRepository.save(user);
        }

        appUserRepository.save(AppUser.builder()
                .roles(List.of(UserRole.ROLE_ADMIN, UserRole.ROLE_USER))
                .nickName("admin")
                .password(encoder.encode("admin"))
                .build()
        );

    }
}
