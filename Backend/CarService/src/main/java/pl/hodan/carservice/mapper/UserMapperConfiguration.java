package pl.hodan.carservice.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hodan.carservice.DTO.UserDTO;
import pl.hodan.carservice.DTO.UserDTOPassword;
import pl.hodan.carservice.entity.User;

@Configuration
public class UserMapperConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                map().setEmail(source.getEmail());
                map().setName(source.getUserDetail().getName());
                map().setSurname(source.getUserDetail().getSurname());
                map().setAddress(source.getUserDetail().getAddress());
                map().setPhoneNumber(source.getUserDetail().getPhoneNumber());
            }
        });

        modelMapper.addMappings(new PropertyMap<User, UserDTOPassword>() {
            @Override
            protected void configure() {
                map().setPassword(source.getPassword());
                map().setEmail(source.getEmail());
                map().setName(source.getUserDetail().getName());
                map().setSurname(source.getUserDetail().getSurname());
                map().setAddress(source.getUserDetail().getAddress());
                map().setPhoneNumber(source.getUserDetail().getPhoneNumber());
            }
        });
        return modelMapper;
    }
}
