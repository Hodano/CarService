package pl.hodan.carservice.common.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.hodan.carservice.DTO.UserDTO;
import pl.hodan.carservice.DTO.UserDTOPassword;
import pl.hodan.carservice.common.entity.User;

@Configuration
public class MapperConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setEmail(source.getEmail());
                map().setName(source.getUserBasicInformation().getName());
                map().setSurname(source.getUserBasicInformation().getSurname());
                map().setAddress(source.getUserBasicInformation().getAddress());
                map().setPhoneNumber(source.getUserBasicInformation().getPhoneNumber());
            }
        });

        modelMapper.addMappings(new PropertyMap<User, UserDTOPassword>() {
            @Override
            protected void configure() {
                map().setPassword(source.getPassword());
                map().setEmail(source.getEmail());
                map().setName(source.getUserBasicInformation().getName());
                map().setSurname(source.getUserBasicInformation().getSurname());
                map().setAddress(source.getUserBasicInformation().getAddress());
                map().setPhoneNumber(source.getUserBasicInformation().getPhoneNumber());

            }
        });
        modelMapper.addMappings(new PropertyMap<UserDTOPassword, User>() {
            @Override
            protected void configure() {
                map().getUserBasicInformation().setName(source.getName());
                map().getUserBasicInformation().setSurname(source.getSurname());
                map().getUserBasicInformation().setAddress(source.getAddress());
                map().getUserBasicInformation().setPhoneNumber(source.getPhoneNumber());

                map().setEmail(source.getEmail());
                map().setPassword(source.getPassword());
                map().setId(source.getId());

            }
        });
        modelMapper.addMappings(new PropertyMap<User, User>() {
            @Override
            protected void configure() {
                map().getUserBasicInformation().setName(source.getUserBasicInformation().getName());
                map().getUserBasicInformation().setSurname(source.getUserBasicInformation().getSurname());
                map().getUserBasicInformation().setAddress(source.getUserBasicInformation().getAddress());
                map().getUserBasicInformation().setPhoneNumber(source.getUserBasicInformation().getPhoneNumber());

                map().setEmail(source.getEmail());
                map().setPassword(source.getPassword());
                skip(destination.getId());

            }
        });
//        modelMapper.addMappings(new PropertyMap<Client, Client>() {
//            @Override
//            protected void configure() {
//                map().setName(source.getName());
//                map().setSurname(source.getSurname());
//                map().setAddress(source.getAddress());
//                map().setPhoneNumber(source.getPhoneNumber());
//                map().setEmail(source.getEmail());
//
//            }
//        });
        return modelMapper;
    }
}
