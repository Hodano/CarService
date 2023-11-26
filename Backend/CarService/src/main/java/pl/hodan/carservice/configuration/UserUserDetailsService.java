package pl.hodan.carservice.configuration;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.hodan.carservice.entity.User;
import pl.hodan.carservice.repository.UserRepository;

import java.util.Optional;

@Service
public class UserUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(UserUserDetails::new)
                .orElseThrow(() -> new InternalAuthenticationServiceException("Email not found"));
    }
}
