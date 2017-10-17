package ch.keepcalm.microservice.rogerrabbit.resource.person;

import ch.keepcalm.microservice.rogerrabbit.infrastructure.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/persons", produces = "application/hal+json")
@Slf4j
public class PersonController {

    PersonService personService;
    PersonResourceAssembler personResourceAssembler;

    @Autowired
    public PersonController(PersonService personService, PersonResourceAssembler personResourceAssembler) {
        this.personService = personService;
        this.personResourceAssembler = personResourceAssembler;
    }

    @GetMapping
    public ResponseEntity<Resources<PersonResource>> getPersons() {
        List<PersonResource> personResources = new ArrayList();

        List<Person> people = personService.getPersons();
        people.stream().forEach(person -> {
                    PersonResource personResource = personResourceAssembler.toResource(person, person.getId());
                    personResources.add(personResource);
                }
        );
        return new ResponseEntity(personResources, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Resources<PersonResource>> getPerson(@PathVariable String id) {
        Person person = personService.getPerson(id);
        PersonResource productResource = personResourceAssembler.toResource(person, id);
        return new ResponseEntity(productResource, HttpStatus.OK);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Resources<PersonResource>> updatePerson(@RequestBody PersonResource personResource, @PathVariable String id) {
        Person person = personService.getPerson(id);
        person.setFirstName(personResource.getFirstName());
        person.setLastName(personResource.getLastName());
        Person updatedPerson = personService.update(person);

        return new ResponseEntity(personResourceAssembler.toResource(updatedPerson, person.getId()), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Resources<PersonResource>> createPerson(@RequestBody PersonResource personResource) {
        Person person = personService.save(personResourceAssembler.instantiateEntity(personResource));

        return new ResponseEntity(personResourceAssembler.toResource(person, person.getId()), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Resources<PersonResource>> deletePerson( @PathVariable String id)  {
        personService.deletePerson(id);
        return new ResponseEntity(HttpEntity.EMPTY.getBody(),HttpStatus.OK);
    }

}
