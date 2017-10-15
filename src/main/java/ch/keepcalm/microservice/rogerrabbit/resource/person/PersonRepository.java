package ch.keepcalm.microservice.rogerrabbit.resource.person;

import ch.keepcalm.microservice.rogerrabbit.infrastructure.domain.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * @author marcelwidmer
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findById(String id);


}
