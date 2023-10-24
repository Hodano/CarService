package pl.hodan.carservice.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hodan.carservice.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsRoleByIdNotNull();
}
