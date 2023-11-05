package pl.hodan.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hodan.carservice.entity.Calendar;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<pl.hodan.carservice.entity.Calendar,Long> {
    List<Calendar> findCalendarsByUserId(Long userId);
    Optional<Calendar> findCalendarByUserIdAndId(Long userId, Long calendarId);
    Boolean existsCalendarById(Long calendarId);

}
