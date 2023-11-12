package pl.hodan.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hodan.carservice.entity.History;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {
    List<History> findHistoriesByCarId(Long carId);
    Optional<History> findHistoriesByCarIdAndId(Long carId, Long historyId);
    boolean existsHistoriesById(Long historyId);
}
