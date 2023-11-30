package pl.hodan.carservice.common.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.hodan.carservice.DTO.UserDTOPassword;
import pl.hodan.carservice.common.entity.User;
import pl.hodan.carservice.DTO.UserDTO;
import pl.hodan.carservice.common.exception.UserNotFoundException;
import pl.hodan.carservice.common.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UsersService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .findFirst();
    }

    public boolean createUser(UserDTOPassword userDTOPassword) {
        User user = modelMapper.map(userDTOPassword, User.class);
        if (userRepository.existsByEmail(user.getEmail()))
            return false;
        userRepository.save(user);
        return true;
    }

    public boolean modifyUser(Long userId, UserDTOPassword newUserDTOPassword) {
        User newUser = modelMapper.map(newUserDTOPassword, User.class);

        Optional<User> searchedUser = userRepository.findById(userId);
        if (searchedUser.isPresent()) {
            User user = searchedUser.get();
            modelMapper.map(newUser,user);
            userRepository.save(user);
            return true;
        }
        return false;

    }
    public boolean removeUserById(Long id){
        Optional<User> searchedUser = userRepository.findById(id);
        if(searchedUser.isPresent()){
            userRepository.delete(searchedUser.get());
            return true;
        }
        return false;
    }

    public  User checkIfUserIdExist(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent())
            return user.get();
        throw new UserNotFoundException("User with id " + userId + "notExist");
    }



}
