package pl.hodan.carservice.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String address;
    private int phoneNumber;



}
