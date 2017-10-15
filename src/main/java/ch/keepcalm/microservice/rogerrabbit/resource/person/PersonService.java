package ch.keepcalm.microservice.rogerrabbit.resource.person;

import ch.keepcalm.microservice.rogerrabbit.infrastructure.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public List<Person> getPersons(){
        Iterable<Person> users = personRepository.findAll();
        return (List<Person>) users;
    }

    public Person getPerson(String userid){
        Person person = personRepository.findByPersonId(userid);
        return person;
    }
}
