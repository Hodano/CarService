package pl.hodan.carservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.hodan.carservice.entity.User;
import pl.hodan.carservice.DTO.UserDTO;
import pl.hodan.carservice.repository.UserDetailRepository;
import pl.hodan.carservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;

    private final ModelMapper modelMapper;

    public UsersService(UserRepository userRepository, UserDetailRepository userDetailRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getUsers(){
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> modelMapper.map(user,UserDTO.class))
                .collect(Collectors.toList());
    }
    public Optional<UserDTO> getUserById(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.stream()
                .map(user -> modelMapper.map(user,UserDTO.class))
                .findFirst();

    }

}
