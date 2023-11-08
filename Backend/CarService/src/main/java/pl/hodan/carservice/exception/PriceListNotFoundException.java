package pl.hodan.carservice.exception;

public class PriceListNotFoundException extends RuntimeException{
    public PriceListNotFoundException( String message) {
        super(message);
    }
}
