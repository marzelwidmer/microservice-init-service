package ch.keepcalm.microservice.rogerrabbit.resource.person;

import ch.keepcalm.microservice.rogerrabbit.infrastructure.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/customers", produces = "application/hal+json")
@Slf4j
public class PersonController {

    PersonService personService;
    PersonResourceAssembler personResourceAssembler;

    @Autowired
    public PersonController(PersonService personService, PersonResourceAssembler personResourceAssembler) {
        this.personService = personService;
        this.personResourceAssembler = personResourceAssembler;
    }

    @GetMapping(value = "persons")
    public ResponseEntity<Resources<PersonResource>> getPersons() {
        List<PersonResource> personResources = new ArrayList();

        List<Person> people = personService.getPersons();
        people.stream().forEach(person -> {
                    PersonResource personResource = personResourceAssembler.toResource(person, person.getPersonId());
                    personResources.add(personResource);
                }
        );
        return new ResponseEntity(personResources, HttpStatus.OK);
    }


    @GetMapping(value = "persons/{productid}")
    public ResponseEntity<Resources<PersonResource>> getperson(@PathVariable String productid) {
        Person person = personService.getPerson(productid);
        PersonResource productResource = personResourceAssembler.toResource(person, productid);
        return new ResponseEntity(productResource, HttpStatus.OK);
    }
}
