package pl.hodan.carservice.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.auth.services.AuthenticationService;
import pl.hodan.carservice.common.entity.Client;
import pl.hodan.carservice.common.service.ClientsService;

import java.util.List;

@RestController
@RequestMapping("/cars-service")
public class ClientController {
    private final ClientsService clientsService;
    private final AuthenticationService authenticationService;

    public ClientController(ClientsService clientsService, AuthenticationService authenticationService) {
        this.clientsService = clientsService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClientsByUserId() {
        Long userId = authenticationService.getCurrentUserId();

        List<Client> clients = clientsService.getClients(userId);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/client")
    public ResponseEntity<Client> getClientByClientId(@RequestParam Long clientId) {
        Long userId = authenticationService.getCurrentUserId();

        Client client = clientsService.getClientByClientId(userId, clientId);

        return ResponseEntity.ok(client);
    }

    @PostMapping("/add-client")
    public ResponseEntity<String> addClient(@RequestBody Client client) {
        Long userId = authenticationService.getCurrentUserId();

        if (clientsService.addClient(userId, client))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>("Client could not be added", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/modify-client")
    public ResponseEntity<String> modifyClient(@RequestParam Long clientId, @RequestBody Client client) {
        Long userId = authenticationService.getCurrentUserId();

        if (clientsService.modifyClientWithUserIdByClientId(userId, clientId, client))
            return new ResponseEntity<>("Client modified",HttpStatus.OK);
        return new ResponseEntity<>("Client not found or could not be modified", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-client")
    public ResponseEntity<String> deleteClient(@RequestParam Long clientId) {
        Long userId = authenticationService.getCurrentUserId();

        if (clientsService.deleteClientWithUserIdByClientId(userId, clientId))
            return new ResponseEntity<>("Client deleted",HttpStatus.OK);
        return new ResponseEntity<>("Client not found or could not be deleted", HttpStatus.NOT_FOUND);
    }

}
