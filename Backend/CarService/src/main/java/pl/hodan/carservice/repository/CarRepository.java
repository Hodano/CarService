package pl.hodan.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hodan.carservice.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
}
