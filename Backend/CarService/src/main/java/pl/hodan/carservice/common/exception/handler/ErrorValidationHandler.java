package pl.hodan.carservice.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.hodan.carservice.common.exception.ValidationException;
import pl.hodan.carservice.common.exception.validation.MyError;
import pl.hodan.carservice.common.exception.validation.ValidationErrorList;

import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorValidationHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorList> methodArgumentNotValidException(MethodArgumentNotValidException e){
        ValidationErrorList validationErrorList = ValidationErrorList.of(e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(this::mapToMyError)
                .collect(Collectors.toSet()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorList);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationErrorList> validationException(ValidationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getValidationErrorList());
    }

    public MyError mapToMyError(ObjectError error){
        FieldError fieldError = (FieldError) error;
        return MyError.of(fieldError.getField(),fieldError.getDefaultMessage());
    }
}
