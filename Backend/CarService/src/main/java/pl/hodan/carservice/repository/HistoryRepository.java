package pl.hodan.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hodan.carservice.entity.History;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {
}
