package sbz.cardiagnosticbe.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sbz.cardiagnosticbe.dto.user.TRegister;
import sbz.cardiagnosticbe.dto.user.TSignIn;
import sbz.cardiagnosticbe.dto.user.TSignInResponse;
import sbz.cardiagnosticbe.model.db.User;
import sbz.cardiagnosticbe.model.enums.Authority;
import sbz.cardiagnosticbe.security.TokenUtils;
import sbz.cardiagnosticbe.service.CustomUserDetailsService;
import sbz.cardiagnosticbe.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;


    @RequestMapping(method = RequestMethod.POST, value="/authenticate")
    public ResponseEntity<Object> signIn(@RequestBody TSignIn signInReq) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    signInReq.getUsername(), signInReq.getPassword());
            authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(signInReq.getUsername());
            String tokenStr = tokenUtils.generateToken(details);

            User user = userService.findByUsername(signInReq.getUsername());
            TSignInResponse response = new TSignInResponse(user.getUsername(), user.getAuthority(), tokenStr);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value="/register")
    public ResponseEntity<String> register(@RequestBody TRegister registerReq) {
        try {
            if (userService.findByUsername(registerReq.getUsername()) == null) {
                User user = new User();
                user.setUsername(registerReq.getUsername());
                user.setPassword(registerReq.getPassword());
                user.setName(registerReq.getName());
                user.setLastName(registerReq.getLastName());

                if (registerReq.getIsExpert()) {
                    user.setAuthority(Authority.EXPERT);
                } else {
                    user.setAuthority(Authority.USER);
                }
                userService.register(user);
            } else {
                return new ResponseEntity<>("Username taken", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Unexpected exception", HttpStatus.BAD_REQUEST);
        }
    }
}
