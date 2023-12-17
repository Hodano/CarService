package pl.hodan.carservice.common.exception.handler;

import jakarta.validation.ConstraintViolationException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.hodan.carservice.common.exception.ValidationException;
import pl.hodan.carservice.common.exception.dto.MyError;
import pl.hodan.carservice.common.exception.dto.ValidationErrorList;
import pl.hodan.carservice.common.messages.Messages;

import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorValidationHandler {
    ///obsługa na  poziomie kontrolera
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorList> methodArgumentNotValidException(MethodArgumentNotValidException e){
        ValidationErrorList validationErrorList = ValidationErrorList.of(e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(this::mapToMyError)
                .collect(Collectors.toSet()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorList);
    }
    // obsługa na poziomie encji
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorList> handleConstraintViolationException(ConstraintViolationException e) {
        ValidationErrorList validationErrorList = ValidationErrorList.of(e.getConstraintViolations()
                .stream()
                .map(constraintViolation -> new MyError(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage()))
                        .collect(Collectors.toSet())
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorList);
    }

//    @ExceptionHandler(RuntimeException.class)
//    ResponseEntity<ValidationErrorList> RuntimeException(RuntimeException e){
//        ValidationErrorList validationErrorList = ValidationErrorList.of("Internal_server_error",Messages.INVALID_SERVER_ERROR);
//        return validationErrorList.createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationErrorList> validationException(ValidationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getValidationErrorList());
    }
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<MyError>AuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException e){
        MyError myError = MyError.of("Authentication", "Unauthorized: Access is denied due to invalid credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(myError);
    }


    public MyError mapToMyError(ObjectError error){
        FieldError fieldError = (FieldError) error;
        return MyError.of(fieldError.getField(),fieldError.getDefaultMessage());
    }
}
