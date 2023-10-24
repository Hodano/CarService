package pl.hodan.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hodan.carservice.entity.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {
}
