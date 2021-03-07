package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.configuration.AppConfiguration;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.CompanyConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.CompanyDbRepository;
import pl.org.conceptweb.heydeskb.model.CompanyAndUser;
import pl.org.conceptweb.heydeskb.model.MethodResponse;
import pl.org.conceptweb.heydeskb.model.User;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.utility.PasswordUtil;
import pl.org.conceptweb.heydeskb.utility.UserNameUtil;

@Log
@Service
public class CompanyService {

    @Autowired
    CompanyDbRepository companyDbRepository;
    @Autowired
    CompanyConverter companyConverter;
    @Autowired
    UserService userService;
    @Autowired
    PasswordUtil passwordUtil;
    @Autowired
    UserNameUtil userNameUtil;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    public HttpResponseWrapper add(CompanyAndUser companyAndUser) {
        HttpResponseWrapper httpResponseWrapper = null;
        try {      
            MethodResponse passwordMethodResponse = passwordUtil.ifPasswordIsProper(companyAndUser.getPassword(), companyAndUser.getRepeatedPassword(), AppConfiguration.MIN_PASSWORD_LENGTH, AppConfiguration.MAX_PASSWORD_LENGTH);
            MethodResponse userNameMethordResponse = userNameUtil.ifUserNameIsProper(companyAndUser.getUserName(), AppConfiguration.MIN_USER_NAME_LENGTH, AppConfiguration.MAX_USER_NAME_LENGTH);

            if (passwordMethodResponse.getStatus().equals(Constans.OK) && userNameMethordResponse.getStatus().equals(Constans.OK)) {
                httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.ADD_USER_SUCCESS_MESSAGE, Arrays.asList(userRepository.save(new User(
                        companyAndUser.getUserName(),
                        passwordEncoder.encode(companyAndUser.getPassword()),
                        true,
                        "ADMIN",
                        null,
                        null,
                        false,
                        companyDbRepository.save(companyConverter.companyToCompanyDb(companyAndUser.getCompany()))))));
            }
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
