package sbz.cardiagnosticbe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sbz.cardiagnosticbe.dto.TSingIn;
import sbz.cardiagnosticbe.security.TokenUtils;
import sbz.cardiagnosticbe.service.CustomUserDetailsService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @RequestMapping(value="/signin", method = RequestMethod.POST)
    public ResponseEntity<String> signIn(@RequestBody TSingIn signInReq) throws AuthenticationException {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    signInReq.getUsername(), signInReq.getPassword());
            authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(signInReq.getUsername());
            String jwt = tokenUtils.generateToken(details.getUsername());

            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid login", HttpStatus.BAD_REQUEST);
        }

    }
}
