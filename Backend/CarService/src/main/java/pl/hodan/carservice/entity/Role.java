package pl.hodan.carservice.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role {
    private long id;
    private String roleName;

    public Role(long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role() {
    }
}
