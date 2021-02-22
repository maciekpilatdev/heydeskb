package pl.org.conceptweb.heydeskb.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.UserConverter;
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

    @Autowired
    UserConverter userConverter;

    @Value("${configuration.jwt.valid}")
    private Long jwtValidTime;

    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE
//            , produces = MediaType.APPLICATION_JSON_VALUE
    )
    public HttpResponseWrapper signUp(@RequestBody User loginData) {

        HttpResponseWrapper wrapper;
        if (userRepository.findByUserName(loginData.getUserName()).isEmpty()) {
            wrapper = new HttpResponseWrapper(Constans.OK, Constans.SIGN_UP_SUCCESS_MESSAGE, Arrays.asList(userRepository.save(new User(
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
            wrapper = new HttpResponseWrapper(Constans.ERROR, Constans.SIGN_UP_ERROR_MESSAGE, new ArrayList());
        }
        log.log(Level.WARNING, "SIGN UP USER: " + wrapper.toString());
        return wrapper;
    }

    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)

    @RequestMapping(value = "/login", method = RequestMethod.POST
//            , produces = MediaType.APPLICATION_JSON_VALUE
    )
    public HttpResponseWrapper createAuthebticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        User user = new User();
        HttpResponseWrapper httpResponseWrapper;

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            user = userRepository.findByUserName(authenticationRequest.getUsername()).get();

            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.CREATE_AUTHEBTICATION_TOKEN_SUCCESS_MESSAGE, Arrays.asList(userConverter.userToUserTrans(new User(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    user.isActive(),
                    user.getAuthoritys(),
                    jwt,
                    Long.toString(System.currentTimeMillis() + jwtValidTime),
                    false,
                    user.getCompanyDb()
            ))));

        } catch (BadCredentialsException e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), Arrays.asList(user));
        }
        return httpResponseWrapper;
    }

//    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, allowedHeaders = "*", methods = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET}, maxAge = 3600)
//    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
//    public HttpResponseWrapper deleteUser(@RequestHeader("Authorization") String jwt, Principal principal) {
//        HttpResponseWrapper httpResponseWrapper;
//        User user = userRepository.findByUserName(principal.getName()).get();
//
//        if (user.getUserName() == principal.getName()) {
//            user.setIsDeleted(Boolean.TRUE);
//            userRepository.save(user);
//            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.DELETE_USER_BY_HIMSELF_SUCCESS_MESSAGE, Arrays.asList(userRepository.save(user)));
//        } else {
//            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, Constans.DELETE_USER_BY_HIMSELF_ERROR_MESSAGE, Arrays.asList(new User()));
//        }
//        return httpResponseWrapper;
//    }

    @GetMapping("/access_error")
    public String accessError() {
        return Constans.ACCESS_ERROR_MESSAGE;
    }
}
