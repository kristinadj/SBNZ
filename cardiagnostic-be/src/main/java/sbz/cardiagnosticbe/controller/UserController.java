package sbz.cardiagnosticbe.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sbz.cardiagnosticbe.dto.SingInDTO;
import sbz.cardiagnosticbe.model.User;
import sbz.cardiagnosticbe.security.TokenUtils;
import sbz.cardiagnosticbe.service.CustomUserDetailsService;
import sbz.cardiagnosticbe.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private final KieContainer kieContainer;

    @Autowired
    public UserController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> signIn(@RequestBody SingInDTO signInReq) {
        try {
            User user = userService.findByUsername(signInReq.getUsername());

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), signInReq.getPassword());
            authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(signInReq.getUsername());
            String jwt = tokenUtils.generateToken(details.getUsername());

            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid login", HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value="test")
    public ResponseEntity test() {
        User user = new User();
        user.setUsername("test");

        KieSession kSession = kieContainer.newKieSession();
        kSession.insert(user);
        int fired = kSession.fireAllRules();
        System.out.println("Fired: " + fired);
        kSession.dispose();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
