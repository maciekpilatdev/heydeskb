package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.CompanyConverter;
import pl.org.conceptweb.heydeskb.inputTest.InputTester;
import pl.org.conceptweb.heydeskb.inputTest.TextInputStrategy;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.CompanyDbRepository;
import pl.org.conceptweb.heydeskb.model.CompanyAndUser;
import pl.org.conceptweb.heydeskb.model.MethodResponse;
import pl.org.conceptweb.heydeskb.model.User;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.utility.PasswordUtil;
import pl.org.conceptweb.heydeskb.utility.UserNameUtil;
import pl.org.conceptweb.heydeskb.inputTest.EmailStrategy;
import pl.org.conceptweb.heydeskb.inputTest.NumberStrategy;
import pl.org.conceptweb.heydeskb.inputTest.PasswordStrategy;

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
    @Autowired
    InputTester inputTester;
    @Autowired
    TextInputStrategy textInputStrategy;
    @Autowired
    EmailStrategy emailStrategy;
    @Autowired
    NumberStrategy numberStrategy;
    @Autowired
    PasswordStrategy passwordStrategy;

    public HttpResponseWrapper add(CompanyAndUser companyAndUser) {
        HttpResponseWrapper httpResponseWrapper = null;

        MethodResponse companyCityTest = inputTester.runTest(textInputStrategy, companyAndUser.getCompany().getCity());
        MethodResponse companyCountryTest = inputTester.runTest(textInputStrategy, companyAndUser.getCompany().getCountry());
        MethodResponse companyMailTest = inputTester.runTest(emailStrategy, companyAndUser.getCompany().getMail());
        MethodResponse companyNameTest = inputTester.runTest(textInputStrategy, companyAndUser.getCompany().getName());
        MethodResponse companyPhoneTest = inputTester.runTest(numberStrategy, companyAndUser.getCompany().getPhone());
        //MethodResponse companyPhoneTest = inputTester.runTest(numberStrategy, companyAndUser.getCompany().getPostalCode());
        MethodResponse companyStreetTest = inputTester.runTest(textInputStrategy, companyAndUser.getCompany().getStreet());
        //MethodResponse companyStreetTest = inputTester.runTest(numberStrategy, companyAndUser.getCompany().getStreetNumber());
        MethodResponse passwordTest = inputTester.runTest(passwordStrategy, companyAndUser.getPassword() + "," + companyAndUser.getRepeatedPassword());
        MethodResponse userNameTest = inputTester.runTest(textInputStrategy, companyAndUser.getUserName());

        if (companyCityTest.getStatus().equals(Constans.ERROR)) {
            return new HttpResponseWrapper(companyCityTest.getStatus(), companyCityTest.getMessage(), new ArrayList());
        } else if (companyCountryTest.getStatus().equals(Constans.ERROR)) {
            return new HttpResponseWrapper(companyCountryTest.getStatus(), companyCountryTest.getMessage(), new ArrayList());
        } else if (companyMailTest.getStatus().equals(Constans.ERROR)) {
            return new HttpResponseWrapper(companyMailTest.getStatus(), companyMailTest.getMessage(), new ArrayList());
        } else if (companyNameTest.getStatus().equals(Constans.ERROR)) {
            return new HttpResponseWrapper(companyNameTest.getStatus(), companyNameTest.getMessage(), new ArrayList());
        } else if (companyPhoneTest.getStatus().equals(Constans.ERROR)) {
            return new HttpResponseWrapper(companyPhoneTest.getStatus(), companyPhoneTest.getMessage(), new ArrayList());
        } else if (companyStreetTest.getStatus().equals(Constans.ERROR)) {
            return new HttpResponseWrapper(companyStreetTest.getStatus(), companyStreetTest.getMessage(), new ArrayList());
        } else if (passwordTest.getStatus().equals(Constans.ERROR)) {
            return new HttpResponseWrapper(passwordTest.getStatus(), passwordTest.getMessage(), new ArrayList());
        }  else if (companyCountryTest.getStatus().equals(Constans.ERROR)) {
            return new HttpResponseWrapper(userNameTest.getStatus(), userNameTest.getMessage(), new ArrayList());
        }

        try {
//            MethodResponse passwordMethodResponse = passwordUtil.ifPasswordIsProper(companyAndUser.getPassword(), companyAndUser.getRepeatedPassword(), AppConfiguration.MIN_PASSWORD_LENGTH, AppConfiguration.MAX_PASSWORD_LENGTH);
//            MethodResponse userNameMethordResponse = userNameUtil.ifUserNameIsProper(companyAndUser.getUserName(), AppConfiguration.MIN_USER_NAME_LENGTH, AppConfiguration.MAX_USER_NAME_LENGTH);

//            if (passwordMethodResponse.getStatus().equals(Constans.OK) && userNameMethordResponse.getStatus().equals(Constans.OK)) {
                httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.ADD_COMPANY, Arrays.asList(userRepository.save(new User(
                        companyAndUser.getUserName(),
                        passwordEncoder.encode(companyAndUser.getPassword()),
                        true,
                        "ADMIN",
                        null,
                        null,
                        false,
                        companyDbRepository.save(companyConverter.companyToCompanyDb(companyAndUser.getCompany()))))));
//            }
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
