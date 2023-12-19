package pl.hodan.carservice.auth.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import pl.hodan.carservice.auth.dto.AuthenticationRequest;
import pl.hodan.carservice.auth.dto.AuthenticationResponse;
import pl.hodan.carservice.auth.dto.RegisterRequest;
import pl.hodan.carservice.common.configuration.UserUserDetails;
import pl.hodan.carservice.common.entity.User;
import pl.hodan.carservice.common.jwt.service.JwtService;
import pl.hodan.carservice.common.messages.Messages;
import pl.hodan.carservice.common.repository.UserRepository;
import pl.hodan.carservice.common.service.RoleService;
import pl.hodan.carservice.email.SendEmailAfterRegistration;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserBasicInformationService userBasicInformationService;
    private final RoleService roleService;
    private final UsersService usersService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @SendEmailAfterRegistration
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {

        usersService.checkIsEmailAlreadyExist(request.getEmail());

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userBasicInformation(userBasicInformationService.createUserDetail(request))
                .roles(Set.of(roleService.createRoles()))
                .build();

        userRepository.save(user);

        UserUserDetails userDetails = convertUserForUserDetails(user);

        AuthenticationResponse response = AuthenticationResponse.builder()
                .name(userDetails.getName())
                .surname(userDetails.getSurname())
                .userName(userDetails.getUsername())
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtService.generateToken(request.getEmail()))
                .body(response);
    }

    public ResponseEntity<?> authenticate(AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));


            UserUserDetails userDetails = (UserUserDetails) authentication.getPrincipal();
            AuthenticationResponse response = AuthenticationResponse.builder()
                    .name(userDetails.getName())
                    .surname(userDetails.getSurname())
                    .userName(userDetails.getUsername())
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtService.generateToken(request.getEmail()))
                    .body(response);
        } catch (AuthenticationException e) {
            logger.warn("Authentication failed for user: {}" ,request.getEmail());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Messages.INVALID_EMAIL_OR_PASSWORD);
        } catch (HttpServerErrorException.InternalServerError e) {
            logger.error("Internal server error occurred)", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred");
        }

    }

    private UserUserDetails convertUserForUserDetails(User user) {
        return new UserUserDetails(user);
    }
    public Long getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null|| !authentication.isAuthenticated()){
            throw new AuthenticationCredentialsNotFoundException("The user is not logged in or not authenticated");
            }
        UserUserDetails userDetails =  (UserUserDetails) authentication.getPrincipal();
        return userDetails.getId();
        }
}

