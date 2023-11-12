package pl.hodan.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.hodan.carservice.enums.Color;

import java.util.Set;

@Getter
@Setter
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carModel;
    private String bodyType;
    private String yearOfProduction;
    private int carMileage;
    private String numberVin;
    @Enumerated(EnumType.STRING)
    private Color color;
    @JsonIgnore
    @ManyToOne
    private Client client;
    @JsonIgnore
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Set<History> historySet;

    public Car() {
    }

    public Car(String carModel, String bodyType, String yearOfProduction, int carMileage, String numberVin, Color color) {
        this.carModel = carModel;
        this.bodyType = bodyType;
        this.yearOfProduction = yearOfProduction;
        this.carMileage = carMileage;
        this.numberVin = numberVin;
        this.color = color;
    }
}
