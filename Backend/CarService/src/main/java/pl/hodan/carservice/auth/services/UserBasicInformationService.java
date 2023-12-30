package pl.hodan.carservice.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hodan.carservice.DTO.UserDTOPassword;
import pl.hodan.carservice.auth.dto.RegisterRequest;
import pl.hodan.carservice.common.entity.UserBasicInformation;
@RequiredArgsConstructor
@Service
public class UserBasicInformationService {
    public UserBasicInformation createUserDetail(RegisterRequest request) {
        UserBasicInformation userBasicInformation = new UserBasicInformation();

        userBasicInformation.setName(request.getName());
        userBasicInformation.setSurname(request.getSurname());
        userBasicInformation.setAddress(request.getAddress());
        userBasicInformation.setPhoneNumber(request.getPhoneNumber());

        return userBasicInformation;
    }

    public UserBasicInformation createUserDetail(UserDTOPassword userDTOPassword) {
        UserBasicInformation userBasicInformation = new UserBasicInformation();

        userBasicInformation.setName(userDTOPassword.getName());
        userBasicInformation.setSurname(userDTOPassword.getSurname());
        userBasicInformation.setAddress(userDTOPassword.getAddress());
        userBasicInformation.setPhoneNumber(userDTOPassword.getPhoneNumber());

        return userBasicInformation;
    }
}
