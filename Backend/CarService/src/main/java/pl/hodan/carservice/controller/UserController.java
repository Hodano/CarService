package pl.hodan.carservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.DTO.UserDTO;
import pl.hodan.carservice.DTO.UserDTOPassword;
import pl.hodan.carservice.service.UsersService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/cars-service")
@RestController
public class UserController {
    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return usersService.getUsers();
    }

    @GetMapping("/user")
    public ResponseEntity getUserById(@RequestParam Long id) {
        Optional<UserDTO> userDTOOptional = usersService.getUserById(id);

        return userDTOOptional.map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("add-user")
    public ResponseEntity createUser(@RequestBody UserDTOPassword userDTOPassword){
        if(usersService.createUser(userDTOPassword))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("modify-user")
    public ResponseEntity modifyUser(@RequestParam Long id, @RequestBody UserDTOPassword userDTOPassword){
        if(usersService.modifyUser(id,userDTOPassword))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("delete-user")
    public ResponseEntity removeUser(@RequestParam Long id){
        if(usersService.removeUserById(id))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}
