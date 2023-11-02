package pl.hodan.carservice.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hodan.carservice.DTO.UserDTO;
import pl.hodan.carservice.DTO.UserDTOPassword;
import pl.hodan.carservice.entity.User;
import pl.hodan.carservice.entity.UserDetail;

@Configuration
public class UserMapperConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
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
        modelMapper.addMappings(new PropertyMap<UserDTOPassword, User>() {
            @Override
            protected void configure() {
                map().getUserDetail().setName(source.getName());
                map().getUserDetail().setSurname(source.getSurname());
                map().getUserDetail().setAddress(source.getAddress());
                map().getUserDetail().setPhoneNumber(source.getPhoneNumber());

                map().setEmail(source.getEmail());
                map().setPassword(source.getPassword());
                map().setId(source.getId());

            }
        });
        modelMapper.addMappings(new PropertyMap<User, User>() {
            @Override
            protected void configure() {
                map().getUserDetail().setName(source.getUserDetail().getName());
                map().getUserDetail().setSurname(source.getUserDetail().getSurname());
                map().getUserDetail().setAddress(source.getUserDetail().getAddress());
                map().getUserDetail().setPhoneNumber(source.getUserDetail().getPhoneNumber());

                map().setEmail(source.getEmail());
                map().setPassword(source.getPassword());
                skip(destination.getId());

            }
        });
        return modelMapper;
    }
}
