package ch.keepcalm.microservice.rogerrabbit.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Backend / Umsysteme / Database
 * @author marcelwidmer
 */
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class SystemException extends RuntimeException {

    public SystemException(String message) {
        super(message);
    }

}
