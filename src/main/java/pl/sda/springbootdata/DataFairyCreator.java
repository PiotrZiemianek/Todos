package pl.sda.springbootdata;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataFairyCreator implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Fairy fairy = Fairy.create();

        Company company = fairy.company();
        Person person = fairy.person();

//        System.out.println(company.getName());
//        System.out.println(person.getFullName());

    }
}
