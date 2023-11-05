package pl.hodan.carservice.exception;

public class CalendarNotFoundException extends RuntimeException{
    public CalendarNotFoundException(String message) {
        super(message);
    }
}
