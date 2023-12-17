package pl.hodan.carservice.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pl.hodan.carservice.common.messages.Messages;

import java.sql.Date;

@Data
@Entity
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateEvent;
    @NotBlank(message = Messages.EMPTY_FIELD)
    @Size(min = 10,message = Messages.WRONG_LENGTH_TEXT)
    private String event;
    @JsonIgnore
    @ManyToOne
    private User user;

    public Calendar(Date dateEvent, String event) {
        this.dateEvent = dateEvent;
        this.event = event;
    }

    public Calendar() {
    }
}
