package pl.hodan.carservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Client {
    private long id;
    private String name;
    private String surname;
    private String address;
    private int phoneNumber;
    private String email;
    private List<Car> carList; //Może być zmiana

    public Client(long id, String name, String surname, String address, int phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Client() {
        carList = new ArrayList<>();
    }
}
