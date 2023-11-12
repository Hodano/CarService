package pl.hodan.carservice.service;

import org.springframework.stereotype.Service;
import pl.hodan.carservice.entity.Client;
import pl.hodan.carservice.exception.ClientNotFoundException;
import pl.hodan.carservice.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {
    private final ClientRepository clientRepository;

    private final UsersService usersService;

    public ClientsService(ClientRepository clientRepository, UsersService usersService) {
        this.clientRepository = clientRepository;
        this.usersService = usersService;
    }

    public List<Client> getClients(Long userId) {
        usersService.checkIfUserIdExist(userId);

        return clientRepository.findClientByUserId(userId);
    }

    public boolean addClient(Long userId, Client client) {
        setUserForClient(userId, client);
        if (client != null) {
            clientRepository.save(client);
            return true;
        }
        return false;

    }

    public boolean modifyClientWithUserIdByClientId(Long userId, Long clientId, Client newClient) {

        checkIfClientExistByUserId(userId, clientId);

        Optional<Client> client = clientRepository.findClientByUserIdAndId(userId, clientId);
        if (client.isPresent()) {
            client.get().setName(newClient.getName());
            client.get().setSurname(newClient.getSurname());
            client.get().setAddress(newClient.getAddress());
            client.get().setPhoneNumber(newClient.getPhoneNumber());
            client.get().setEmail(newClient.getEmail());
            clientRepository.save(client.get());
            return true;
        }

        return false;
    }

    public boolean deleteClientWithUserIdByClientId(Long userId, Long clientId) {
        checkIfClientExistByUserId(userId, clientId);

        Optional<Client> client = clientRepository.findClientByUserIdAndId(userId, clientId);
        if (client.isPresent()) {
            clientRepository.deleteById(clientId);
            return true;
        }
        return false;
    }
    public Client checkIfClientIdExist(Long clientId){
        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isPresent())
            return client.get();
        throw new ClientNotFoundException("Client with id " + clientId + "notExist");
    }
//    public Client getClientById(Long clientId){
//        checkIfClientIdExist(clientId);
//        return clientRepository.getClientById(clientId);
//    }

    private void setUserForClient(Long userId, Client client) {
        client.setUser(usersService.checkIfUserIdExist(userId));
    }

    private void checkIfClientExistByUserId(Long userId, Long clientId) {
        usersService.checkIfUserIdExist(userId);
        if (!clientRepository.existsClientById(clientId))
            throw new ClientNotFoundException("Client with id " + clientId + "notExist");

    }



}
