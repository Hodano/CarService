package pl.hodan.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface CalendarRepository extends JpaRepository<pl.hodan.carservice.entity.Calendar,Long> {
}
