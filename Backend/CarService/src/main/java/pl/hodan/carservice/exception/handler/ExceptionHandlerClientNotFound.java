package pl.hodan.carservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.hodan.carservice.exception.CalendarNotFoundException;
import pl.hodan.carservice.exception.ClientNotFoundException;
@ControllerAdvice
public class ExceptionHandlerClientNotFound {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity handleClientNotFound(ClientNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
    }
}
