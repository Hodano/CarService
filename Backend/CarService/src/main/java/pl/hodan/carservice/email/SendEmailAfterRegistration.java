package pl.hodan.carservice.email;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface SendEmailAfterRegistration {
}
