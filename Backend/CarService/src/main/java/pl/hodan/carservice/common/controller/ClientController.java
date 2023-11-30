package pl.hodan.carservice.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.common.entity.Client;
import pl.hodan.carservice.common.service.ClientsService;

import java.util.List;

@RestController
@RequestMapping("/cars-service")
public class ClientController {
    private final ClientsService clientsService;

    public ClientController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClientsByUserId(@RequestParam Long userId) {
        List<Client> clients = clientsService.getClients(userId);
        return ResponseEntity.ok(clients);
    }
    @PostMapping("/add-client")
    public ResponseEntity addClient(@RequestParam Long userId, @RequestBody Client client){
        if(clientsService.addClient(userId,client))
            return new ResponseEntity(HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/modify-client")
    public ResponseEntity modifyClient(@RequestParam Long userId, @RequestParam Long clientId, @RequestBody Client client){
        if(clientsService.modifyClientWithUserIdByClientId(userId,clientId,client))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete-client")
    public ResponseEntity deleteClient(@RequestParam Long userId,@RequestParam Long clientId){
        if(clientsService.deleteClientWithUserIdByClientId(userId,clientId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
