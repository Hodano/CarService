package pl.hodan.carservice.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.auth.dto.AuthenticationRequest;
import pl.hodan.carservice.auth.dto.AuthenticationResponse;
import pl.hodan.carservice.auth.dto.RegisterRequest;
import pl.hodan.carservice.auth.services.AuthenticationService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request){
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest request){
        return authenticationService.authenticate(request);
    }
}