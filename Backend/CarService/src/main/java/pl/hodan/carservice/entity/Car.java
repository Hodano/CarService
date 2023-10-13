package pl.hodan.carservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.hodan.carservice.model.Color;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private String carModel;
    private String bodyType;
    private String yearOfProduction;
    private int carMileage;
    private String numberVin;
    @Enumerated(EnumType.STRING)
    private Color color;

    public Car() {
    }

    public Car(Long id, Long clientId, String carModel, String bodyType, String yearOfProduction, int carMileage, String numberVin, Color color) {
        this.id = id;
        this.clientId = clientId;
        this.carModel = carModel;
        this.bodyType = bodyType;
        this.yearOfProduction = yearOfProduction;
        this.carMileage = carMileage;
        this.numberVin = numberVin;
        this.color = color;
    }
}
