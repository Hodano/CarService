package pl.hodan.carservice.common.exception;

import lombok.Getter;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import pl.hodan.carservice.common.exception.validation.ValidationErrorList;

public class ValidationException extends RuntimeException{
    @Getter
    private final ValidationErrorList validationErrorList;

    public ValidationException(ValidationErrorList validationErrorList) {
        this.validationErrorList = validationErrorList;
    }
}
