package pl.hodan.carservice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private long id;
    private String name;
    private String surname;
    private int phoneNumber;
    private String address;
    private String email;
    private String password;

    public User(long id, String name, String surname, int phoneNumber, String address, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public User() {
    }
}
