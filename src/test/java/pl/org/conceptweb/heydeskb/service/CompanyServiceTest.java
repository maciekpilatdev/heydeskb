package pl.org.conceptweb.heydeskb.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.org.conceptweb.heydeskb.converter.CompanyConverter;
import pl.org.conceptweb.heydeskb.inputTest.EmailStrategy;
import pl.org.conceptweb.heydeskb.inputTest.InputTester;
import pl.org.conceptweb.heydeskb.inputTest.NumberStrategy;
import pl.org.conceptweb.heydeskb.inputTest.PasswordStrategy;
import pl.org.conceptweb.heydeskb.inputTest.TextInputStrategy;
import pl.org.conceptweb.heydeskb.model.CompanyDb;
import pl.org.conceptweb.heydeskb.repository.CompanyDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.security.MyUserDetailsService;
import pl.org.conceptweb.heydeskb.utility.JwtUtil;
import pl.org.conceptweb.heydeskb.utility.PasswordUtil;
import pl.org.conceptweb.heydeskb.utility.UserNameUtil;

@WebMvcTest(CompanyService.class)
@ExtendWith(SpringExtension.class)
@WithMockUser(username = "admin", password = "pass", authorities = "ADMIN")
public class CompanyServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MyUserDetailsService myUserDetailsService;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    CompanyDbRepository companyDbRepository;

    @MockBean
    CompanyConverter companyConverter;

    @MockBean
    UserService userService;

    @MockBean
    PasswordUtil passwordUtil;

    @MockBean
    UserNameUtil userNameUtil;

    @MockBean
    UserRepository userRepository;

    @MockBean
    InputTester inputTester;
    
    @MockBean
    TextInputStrategy textInputStrategy;
    
    @MockBean
    EmailStrategy emailStrategy;
    
    @MockBean
    NumberStrategy numberStrategy;
    
    @MockBean
    PasswordStrategy passwordStrategy;

    @Test
    public void testAdd() {
        CompanyDb companyDb = new CompanyDb();
        when(companyDbRepository.save(companyDb)).thenReturn(companyDb);
    }
}
