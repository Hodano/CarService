package pl.hodan.carservice.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hodan.carservice.common.entity.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarByClientId(Long clientId);
    boolean existsCarById(Long id);
    Optional<Car> findCarByClientIdAndId(Long clientId, Long carId);

}
