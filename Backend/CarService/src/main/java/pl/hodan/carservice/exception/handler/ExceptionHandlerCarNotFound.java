package pl.hodan.carservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.hodan.carservice.exception.CalendarNotFoundException;
import pl.hodan.carservice.exception.CarNotFoundException;

@ControllerAdvice
public class ExceptionHandlerCarNotFound {
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity handleCarrNotFound(CarNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
    }

}
