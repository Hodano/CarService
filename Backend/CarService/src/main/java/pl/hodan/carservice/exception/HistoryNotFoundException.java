package pl.hodan.carservice.exception;

public class HistoryNotFoundException extends RuntimeException{
    public HistoryNotFoundException(String message) {
        super(message);
    }
}
