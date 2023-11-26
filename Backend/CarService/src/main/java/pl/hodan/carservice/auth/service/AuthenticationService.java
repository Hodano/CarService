package pl.hodan.carservice.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.hodan.carservice.auth.AuthenticationRequest;
import pl.hodan.carservice.auth.AuthenticationResponse;
import pl.hodan.carservice.auth.RegisterRequest;
import pl.hodan.carservice.configuration.UserUserDetails;
import pl.hodan.carservice.entity.User;
import pl.hodan.carservice.enums.RolesEnum;
import pl.hodan.carservice.exception.UserNotFoundException;
import pl.hodan.carservice.repository.UserRepository;
import pl.hodan.carservice.security.service.JwtService;
import pl.hodan.carservice.service.RoleService;
import pl.hodan.carservice.service.UserDetailService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDetailService userDetailService;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {


        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userDetail(userDetailService.createUserDetail(request))
                .roles(Set.of(roleService.createUserRole(RolesEnum.USER)))
                .build();

        userRepository.save(user);

        UserUserDetails  userDetails = convertUserForUserDetails(user);

        var jwtToken = jwtService.generateToken(userDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("No user found with this email address"));

        UserUserDetails  userDetails = convertUserForUserDetails(user);

        var jwtToken = jwtService.generateToken(userDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    private UserUserDetails convertUserForUserDetails(User user){
        UserUserDetails userDetails = new UserUserDetails(user);
        return userDetails;
    }
}
