package sbz.cardiagnosticbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.model.Authority;
import sbz.cardiagnosticbe.model.User;
import sbz.cardiagnosticbe.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else{
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
                Authority authority = (Authority) grantedAuthority;
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    grantedAuthorities
            );
        }
    }
}
