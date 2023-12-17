package pl.hodan.carservice.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOfHistoryCar; // zmiana na date...
    private String descriptionHistory;
    @JsonIgnore
    @ManyToOne
    private Car car;

    public History(Long id, Date dateOfHistoryCar, String descriptionHistory) {
        this.id = id;
        this.dateOfHistoryCar = dateOfHistoryCar;
        this.descriptionHistory = descriptionHistory;
    }

    public History() {
    }
}
