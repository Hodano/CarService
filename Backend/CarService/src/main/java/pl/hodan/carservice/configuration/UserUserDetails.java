package pl.hodan.carservice.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.hodan.carservice.entity.User;

import java.util.Collection;
import java.util.List;

public class UserUserDetails implements UserDetails {

    private final String email;
    private final String password;


    private final String name;
    private final String surname;
    private final String address;
    private final int phoneNumber;

//    private final List<SimpleGrantedAuthority> authorities;

    public UserUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getUserDetail().getName();
        this.surname = user.getUserDetail().getSurname();
        this.address = user.getUserDetail().getAddress();
        this.phoneNumber = user.getUserDetail().getPhoneNumber();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
