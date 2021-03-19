package pl.org.conceptweb.heydeskb.controller;

import pl.org.conceptweb.heydeskb.model.Company;
import pl.org.conceptweb.heydeskb.model.CompanyAndUser;
import pl.org.conceptweb.heydeskb.service.CompanyService;
import static org.mockito.Mockito.*;

import com.google.gson.Gson;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import java.util.Arrays;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.org.conceptweb.heydeskb.security.MyUserDetailsService;
import pl.org.conceptweb.heydeskb.utility.JwtUtil;

@WebMvcTest(CompanyController.class)
@ExtendWith(SpringExtension.class)
@WithMockUser(username = "admin", password = "pass", authorities = "ADMIN")
public class CompanyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MyUserDetailsService myUserDetailsService;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    CompanyService companyService;

    @Test
    public void testAdd() throws Exception {
        Company company = new Company(new Long(1), "company name", "company mail", "company phone", "company street", "company street number", "company postal code", "company city", "company country", Arrays.asList(new Long(1)), Arrays.asList(new Long(1)));
        CompanyAndUser companyAndUser = new CompanyAndUser(company, "userName", "userPassword", "userPassword");
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("ok", "message", Arrays.asList(companyAndUser));
        String requestJson = new Gson().toJson(companyAndUser);
        when(companyService.add(companyAndUser)).thenReturn(httpResponseWrapper);
        mockMvc.perform(MockMvcRequestBuilders.post("/company").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result[0].userName").value("userName"));
    }
}
