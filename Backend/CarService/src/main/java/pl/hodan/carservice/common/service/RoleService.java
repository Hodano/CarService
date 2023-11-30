package pl.hodan.carservice.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hodan.carservice.common.entity.Role;
import pl.hodan.carservice.common.enums.RolesEnum;
import pl.hodan.carservice.common.repository.RoleRepository;
@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

//    public RoleService(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }

//    @PostConstruct
//    private void init()
//    {
//        if(!roleRepository.existsRoleByIdNotNull()){
//            initializeRoles();
//        }
//    }
//    private void initializeRoles(User user){
//        roleRepository.save(new Role(user.getRoles().stream().filter(x->x.)))
//        roleRepository.save(new Role(RolesEnum.USER));
//    }
    public Role createUserRole(RolesEnum rolesEnum){
        Role role = new Role();
        role.setRole(rolesEnum);
        roleRepository.save(role);
        return role;
    }
}
