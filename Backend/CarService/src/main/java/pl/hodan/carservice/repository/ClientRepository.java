package pl.hodan.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hodan.carservice.entity.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
