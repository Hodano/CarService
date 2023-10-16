package pl.hodan.carservice.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pl.hodan.carservice.entity.Role;
import pl.hodan.carservice.enums.Roles;
import pl.hodan.carservice.repository.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private boolean initialized = false;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void init()
    {
        if(!initialized){
            initializeRoles();
            initialized=true;
        }
    }
    private void initializeRoles(){
        roleRepository.save(new Role(Roles.ADMIN));
        roleRepository.save(new Role(Roles.USER));
    }
}
