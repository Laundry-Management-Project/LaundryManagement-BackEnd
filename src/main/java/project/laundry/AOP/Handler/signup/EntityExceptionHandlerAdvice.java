package project.laundry.AOP.Handler.signup;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.laundry.exception.EntityNotFoundException;

@RestControllerAdvice
public class EntityExceptionHandlerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {

        return ResponseEntity.internalServerError().body(null);
    }
}
