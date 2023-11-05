package pl.hodan.carservice.exception;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
