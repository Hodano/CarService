package pl.hodan.carservice.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hodan.carservice.auth.RegisterRequest;
import pl.hodan.carservice.common.entity.UserDetail;
@RequiredArgsConstructor
@Service
public class UserDetailService {
    public UserDetail createUserDetail(RegisterRequest request){
        UserDetail userDetail = new UserDetail();

        userDetail.setName(request.getName());
        userDetail.setSurname(request.getSurname());
        userDetail.setAddress(request.getAddress());
        userDetail.setPhoneNumber(request.getPhoneNumber());

        return userDetail;
    }
}
