package pl.hodan.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
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
    @JsonIgnore
    @ManyToOne
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
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
