package ch.keepcalm.microservice.rogerrabbit.resource.index;


import ch.keepcalm.microservice.rogerrabbit.resource.person.PersonController;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by marcelwidmer on 20/03/16.
 */
@RestController
@RequestMapping(produces = "application/hal+json")
public class ApiController {

    @GetMapping(value = "/")
    public ResourceSupport api() {
        ResourceSupport resourceSupport = new ResourceSupport();
        resourceSupport.add(linkTo(methodOn(ApiController.class).api()).withSelfRel());
        resourceSupport.add(linkTo(methodOn(PersonController.class).getPersons()).withRel("persons"));
        resourceSupport.add(linkTo(ApiController.class).slash("/manage").slash("/docs/manual.html").withRel("documentation"));

        return resourceSupport;
    }

}



