package ch.keepcalm.microservice.rogerrabbit.resource.person;

import ch.keepcalm.microservice.rogerrabbit.infrastructure.domain.Person;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class PersonResourceAssembler extends ResourceSupport {

    private Mapper dozerBeanMapper;

    @Autowired
    public PersonResourceAssembler(Mapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }


    public PersonResource toResource(Person entity, String id) {
        PersonResource resource = instantiateResource(entity);

        Link selfLink = ControllerLinkBuilder.linkTo(methodOn(PersonController.class).getPerson(id)).withRel("self");
        resource.add(selfLink);

        return resource;
    }

    public PersonResource instantiateResource(Person entity) {
        PersonResource resource = dozerBeanMapper.map(entity, PersonResource.class);
        return resource;
    }

    public Person instantiateEntity(PersonResource resource) {
        Person entity  = dozerBeanMapper.map(resource, Person.class);
        return entity;
    }
}
