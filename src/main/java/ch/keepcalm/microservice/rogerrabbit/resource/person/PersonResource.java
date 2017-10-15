package ch.keepcalm.microservice.rogerrabbit.resource.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL) //or Include.NON_EMPTY, if that fits your use case
@ControllerAdvice
public class PersonResource extends ResourceSupport  {

    private String firstName;
    private String lastName;

}
