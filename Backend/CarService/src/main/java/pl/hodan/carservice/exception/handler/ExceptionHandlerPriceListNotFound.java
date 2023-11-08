package pl.hodan.carservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.hodan.carservice.exception.CalendarNotFoundException;
import pl.hodan.carservice.exception.PriceListNotFoundException;

@ControllerAdvice
public class ExceptionHandlerPriceListNotFound {
    @ExceptionHandler(PriceListNotFoundException.class)
    public ResponseEntity handlePriceListNotFound(PriceListNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PriceList not found");
    }
}