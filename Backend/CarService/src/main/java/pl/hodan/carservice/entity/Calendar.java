package pl.hodan.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateEvent;
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
