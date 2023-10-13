package pl.hodan.carservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateEvent;
    private String event;

    public Calendar(Long id, String dateEvent, String event) {
        this.dateEvent = dateEvent;
        this.event = event;
        this.id = id;
    }

    public Calendar() {
    }
}
