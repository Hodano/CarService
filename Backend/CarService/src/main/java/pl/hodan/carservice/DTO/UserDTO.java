package pl.hodan.carservice.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import pl.hodan.carservice.common.entity.Role;
import pl.hodan.carservice.common.messages.Messages;

import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private Long id;
    @NotBlank(message = Messages.EMPTY_FIELD)
    @Email(message = Messages.BAD_EMAIL)
    private String email;
    @NotBlank(message = Messages.EMPTY_FIELD)
    private String name;
    @NotBlank(message = Messages.EMPTY_FIELD)
    private String surname;
    @NotBlank(message = Messages.EMPTY_FIELD)
    private String address;
    @NotNull(message = Messages.PHONE_NUMBER_IS_NULL)
    private int phoneNumber;
    private Set<Role> roles;



}
