package ch.keepcalm.microservice.rogerrabbit;

import ch.keepcalm.microservice.rogerrabbit.infrastructure.domain.Person;
import ch.keepcalm.microservice.rogerrabbit.resource.person.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelwidmer on 04/07/16.
 */
@SpringBootApplication
@EnableEurekaClient
public class RogerRabbitService  {

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
        persons.add(Person.builder().firstName("Jessica").lastName("Rabbit").build());
        persons.add(Person.builder().firstName("Roger").lastName("Rabbit").build());

        return persons;
    }

    @Bean
    CommandLineRunner init(PersonRepository personRepository) {
        return (args) -> {
            personRepository.save(mockPerson());
        };
    }

}