package pl.hodan.carservice.common.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hodan.carservice.common.entity.Role;
import pl.hodan.carservice.common.enums.RolesEnum;
import pl.hodan.carservice.common.repository.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role createRoles(){

        Role role = new Role();
        if(!roleRepository.existsRoleByIdNotNull())
            role.setRole(RolesEnum.ADMIN);
        else
            role.setRole(RolesEnum.USER);

        roleRepository.save(role);
        return role;
    }
}
