package pl.hodan.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hodan.carservice.entity.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findClientByUserId(Long userId);
    boolean existsClientById(Long clientId);
    Optional<Client> findClientByUserIdAndId(Long userId, Long clientId);


}
