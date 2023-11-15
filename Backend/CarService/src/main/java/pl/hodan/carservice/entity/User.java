package pl.hodan.carservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetail userDetail;

    @OneToMany(mappedBy = "user")
    private Set<Calendar> calendarSet;
    @OneToMany(mappedBy = "user")
    private Set<PriceList> priceListSet;
    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL)
    private Set<Client> clientSet;
    @ManyToMany(mappedBy = "userSet")
    private Set<Role> roleSet;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

}
