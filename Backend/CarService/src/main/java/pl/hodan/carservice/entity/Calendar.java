package pl.hodan.carservice.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Calendar {
    private long id;
    private String dateEvent;
    private String event;

    public Calendar(long id, String dateEvent, String event) {
        this.dateEvent = dateEvent;
        this.event = event;
        this.id = id;
    }

    public Calendar() {
    }
}
