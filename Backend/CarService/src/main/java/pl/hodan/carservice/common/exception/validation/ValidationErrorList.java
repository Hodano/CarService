package pl.hodan.carservice.common.exception.validation;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.hodan.carservice.common.exception.ValidationException;
import pl.hodan.carservice.common.messages.MessagesEnum;

import java.util.HashSet;
import java.util.Set;

public class ValidationErrorList {
    @Getter
    private final Set<MyError> errors;

    private ValidationErrorList() {
        this.errors = new HashSet<>();
    }

    public ValidationErrorList(MyError myError) {
        this.errors = new HashSet<>();

        errors.add(myError);
    }

    public ValidationErrorList(Set<MyError> errors) {
        this.errors = errors;
    }

    public ValidationErrorList(String field, MessagesEnum messagesEnum) {
        this.errors = new HashSet<>();
        errors.add(MyError.of(field, messagesEnum.getCode()));
    }

    public ValidationErrorList(String field, String message) {
        this.errors = new HashSet<>();
        errors.add(MyError.of(field, message));
    }

    public static ValidationErrorList empty() {
        return new ValidationErrorList();
    }

    public static ValidationErrorList of(MyError myError) {
        return new ValidationErrorList(myError);
    }

    public static ValidationErrorList of(Set<MyError> errors) {
        return new ValidationErrorList(errors);
    }

    public static ValidationErrorList of(String field, MessagesEnum messagesEnum) {
        return new ValidationErrorList(field, messagesEnum);
    }

    public static ValidationErrorList of(String field, String message) {
        return new ValidationErrorList(field, message);
    }

    public void add(MyError error) {
        this.errors.add(error);
    }

    public ResponseEntity<ValidationErrorList> createResponseEntity() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this);
    }

    public ResponseEntity<ValidationErrorList> createResponseEntity(HttpStatus status) {
        return ResponseEntity.status(status).body(this);
    }

    public void throwIfNotEmpty() {
        if (!errors.isEmpty())
            throw new ValidationException(this);
    }
}

