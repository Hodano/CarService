package pl.hodan.carservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;

@Setter
@Getter
@Entity
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateEvent;
    private String event;
    @ManyToOne
    private User user;

    public Calendar(Date dateEvent, String event) {
        this.dateEvent = dateEvent;
        this.event = event;
    }

    public Calendar() {
    }
}
