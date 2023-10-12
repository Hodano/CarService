package pl.hodan.carservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Car {
    private long id;
    private long clientId;
    private String carModel;
    private String bodyType;
    private String yearOfProduction;
    private int carMileage;
    private String numberVin;
    private String color;
    private List<History> historyList;

    public Car() {
    }

    public Car(long id, long clientId, String carModel, String bodyType, String yearOfProduction, int carMileage, String numberVin, String color) {
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
