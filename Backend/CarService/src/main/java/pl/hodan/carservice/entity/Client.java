package pl.hodan.carservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String address;
    private int phoneNumber;
    private String email;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "client")
    private Set<Car> carSet;


    public Client(String name, String surname, String address, int phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Client() {
    }
}
