package pl.hodan.carservice.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pl.hodan.carservice.common.enums.Color;
import pl.hodan.carservice.common.messages.Messages;

import java.util.Set;

@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = Messages.EMPTY_FIELD)
    private String carModel;
    @NotBlank(message = Messages.EMPTY_FIELD)
    private String bodyType;
    @NotBlank(message = Messages.EMPTY_FIELD)
    private String yearOfProduction;
    @NotNull(message = Messages.CAR_MILEAGE_IS_NULL)
    @Min(value = 1,message = Messages.CAR_MILEAGE_MUST_BE_BIGGER_THAN_1)
    private int carMileage;
    @NotBlank(message = Messages.EMPTY_FIELD)
    @Size(min = 8,message = Messages.CAR_VIN_MUST_BE_LONGER_THAN_8_CHARACTERS)
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
