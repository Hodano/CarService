package pl.hodan.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<pl.hodan.carservice.entity.Calendar,Long> {

}
