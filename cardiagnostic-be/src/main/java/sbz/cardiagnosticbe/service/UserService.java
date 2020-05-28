package sbz.cardiagnosticbe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sbz.cardiagnosticbe.model.User;
import sbz.cardiagnosticbe.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username);
        return u;
    }

    public Optional<User> findById(Long id) throws AccessDeniedException {
        Optional<User> u = userRepository.findById(id);
        return u;
    }

    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }
}
