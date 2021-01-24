package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.AuthenticationRequest;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.User;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.utility.JwtUtil;

@Log
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${configuration.jwt.valid}")
    private Long jwtValidTime;

    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapper signUp(@RequestBody User loginData) {

        HttpResponseWrapper wrapper;
        if (userRepository.findByUserName(loginData.getUserName()).isEmpty()) {
            wrapper = new HttpResponseWrapper("ok", Arrays.asList(userRepository.save(new User(
                    loginData.getUserName(),
                    passwordEncoder.encode(loginData.getPassword()),
                    true,
                    "user",
                    null,
                    null,
                    false,
                    null
            ))));
        } else {
            wrapper = new HttpResponseWrapper("username not available", new ArrayList());
        }
        log.log(Level.WARNING, "SIGN UP USER: " + wrapper.toString());
        return wrapper;
    }

    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponseWrapper createAuthebticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        User user = new User();
        HttpResponseWrapper httpResponseWrapper;
        
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            user = userRepository.findByUserName(authenticationRequest.getUsername()).get();
            httpResponseWrapper = new HttpResponseWrapper("ok", Arrays.asList(new User(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    user.isActive(),
                    user.getAuthoritys(),
                    jwt,
                    Long.toString(System.currentTimeMillis() + jwtValidTime),
                    false,
                    user.getCompanyDb()
            )));

        } catch (BadCredentialsException e) {
            System.out.println("ERROR!");
            httpResponseWrapper = new HttpResponseWrapper("error: " + e, Arrays.asList(user));
        }
        return httpResponseWrapper;
    }

    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, allowedHeaders = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET}, maxAge = 3600)
    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
    public HttpResponseWrapper deleteUser(@RequestHeader("Authorization") String jwt, Principal principal) {
        System.out.println("DZIAŁAM");
        HttpResponseWrapper httpResponseWrapper = null;
        User user = userRepository.findByUserName(principal.getName()).get();

        if (user.getUserName() == principal.getName()) {
            user.setIsDeleted(Boolean.TRUE);
            userRepository.save(user);
            httpResponseWrapper = new HttpResponseWrapper("ok", Arrays.asList(userRepository.save(user)));
        } else {
            httpResponseWrapper = new HttpResponseWrapper("error", Arrays.asList(new User()));
        }

        return httpResponseWrapper;
    }

    @GetMapping("/access_error")
    public String accessError() {
        return "Access denied!";
    }
}
