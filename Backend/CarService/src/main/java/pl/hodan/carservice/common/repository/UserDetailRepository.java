package pl.hodan.carservice.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hodan.carservice.common.entity.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {
}
