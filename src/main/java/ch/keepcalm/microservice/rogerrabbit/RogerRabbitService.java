package ch.keepcalm.microservice.rogerrabbit;

import ch.keepcalm.microservice.rogerrabbit.infrastructure.domain.Person;
import ch.keepcalm.microservice.rogerrabbit.resource.person.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelwidmer on 04/07/16.
 */
@SpringBootApplication
@EnableEurekaClient
public class RogerRabbitService implements CommandLineRunner {

    PersonRepository personRepository;

    public RogerRabbitService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RogerRabbitService.class, args);
    }


    private List<Person> mockPerson(){
        List<Person> persons = new ArrayList<>();
        persons.add(Person.builder().firstName("Jone").lastName("Doe").build());
        persons.add(Person.builder().firstName("Jane").lastName("Doe").build());
        return persons;
    }

    @Override
    public void run(String... strings) throws Exception {
        personRepository.save(mockPerson());
    }
}