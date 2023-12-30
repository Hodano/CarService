package pl.hodan.carservice.common.service;

import org.springframework.stereotype.Service;
import pl.hodan.carservice.common.entity.Role;
import pl.hodan.carservice.common.entity.User;
import pl.hodan.carservice.common.enums.RolesEnum;
import pl.hodan.carservice.common.exception.ValidationException;
import pl.hodan.carservice.common.exception.dto.ValidationErrorList;
import pl.hodan.carservice.common.messages.Messages;
import pl.hodan.carservice.common.repository.RoleRepository;

import java.util.Set;

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
    public Role setUserRole(){
        Role role = new Role();
        role.setRole(RolesEnum.USER);
        roleRepository.save(role);
        return role;
    }
    public Role setAdminRole(){
        Role role = new Role();
        role.setRole(RolesEnum.ADMIN);
        roleRepository.save(role);
        return role;

    }
    public Set<Role> findRolesByNames(Set<RolesEnum> rolesEnumSet){

        if(rolesEnumSet==null || rolesEnumSet.isEmpty())
            throw new ValidationException(ValidationErrorList.of("role", Messages.EMPTY_FIELD));

        for(RolesEnum rolesEnum : rolesEnumSet){
            if(rolesEnum!=RolesEnum.ADMIN && rolesEnum != RolesEnum.USER)
                throw new ValidationException(ValidationErrorList.of("role",Messages.THIS_ROLES_DO_NOT_EXIST));
        }

        return roleRepository.findByRoleIn(rolesEnumSet);
    }
}
