package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.UserConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.User;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.utility.PasswordUtil;
import pl.org.conceptweb.heydeskb.utility.UserNameUtil;
import pl.org.conceptweb.heydeskb.model.MethodResponse;
import pl.org.conceptweb.heydeskb.configuration.AppConfiguration;

@Log
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordUtil passwordUtil;
    @Autowired
    UserNameUtil userNameUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserConverter userConverter;

    public User getLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByUserNameWithoutDeleted(currentPrincipalName);
    }

    public HttpResponseWrapper deleteById(Long userId) {
        HttpResponseWrapper httpResponseWrapper;

        try {
            User user = userRepository.findById(userId).get();
            user.setIsDeleted(Boolean.TRUE);
            user.setActive(Boolean.FALSE);
            userRepository.save(user);

            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.DELETE_USER_BY_ID_SUCCESS_MESSAGE, Arrays.asList(userConverter.userToUserTrans(userRepository.save(user))));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }

    public HttpResponseWrapper add(String userName, String password, String repeatedPassword) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            MethodResponse passwordMethodResponse = passwordUtil.ifPasswordIsProper(password, repeatedPassword, AppConfiguration.MIN_PASSWORD_LENGTH, AppConfiguration.MAX_PASSWORD_LENGTH);
            MethodResponse userNameMethordResponse = userNameUtil.ifUserNameIsProper(userName, AppConfiguration.MIN_USER_NAME_LENGTH, AppConfiguration.MAX_USER_NAME_LENGTH);
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, passwordMethodResponse.getMessage() + " / " + userNameMethordResponse.getMessage(), new ArrayList());
            if (passwordMethodResponse.getStatus().equals(Constans.OK) && userNameMethordResponse.getStatus().equals(Constans.OK)) {
                httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.ADD_USER_SUCCESS_MESSAGE, Arrays.asList(userRepository.save(new User(
                        userName,
                        passwordEncoder.encode(password),
                        true,
                        "USER",
                        null,
                        null,
                        false,
                        getLogged().getCompanyDb()))));
            }
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }

    public HttpResponseWrapper getAllByCompany(Long companyId) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_ALL_BY_COMPANY_SUCCESS_MESSAGE, userRepository.findAllUsersByCompany(companyId));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
