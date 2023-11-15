package pl.hodan.carservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import pl.hodan.carservice.enums.Roles;

import java.util.Set;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Roles roleName;
    @ManyToMany
    private Set<User> userSet;

    public Role(Roles roleName) {
        this.roleName = roleName;
    }

    public Role() {
    }

}
