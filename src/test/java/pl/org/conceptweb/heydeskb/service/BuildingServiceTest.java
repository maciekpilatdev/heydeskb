package pl.org.conceptweb.heydeskb.service;

import java.util.List;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.org.conceptweb.heydeskb.converter.BuildingConverter;
import pl.org.conceptweb.heydeskb.inputTest.InputTester;
import pl.org.conceptweb.heydeskb.inputTest.TextInputStrategy;
import pl.org.conceptweb.heydeskb.model.BuildingDb;
import pl.org.conceptweb.heydeskb.model.CompanyDb;
import pl.org.conceptweb.heydeskb.model.FloorDb;
import pl.org.conceptweb.heydeskb.repository.BuildingDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.security.MyUserDetailsService;
import pl.org.conceptweb.heydeskb.security.SecurityAuthoritiesCheck;
import pl.org.conceptweb.heydeskb.utility.JwtUtil;

@WebMvcTest(BuildingService.class)
@ExtendWith(SpringExtension.class)
@WithMockUser(username = "admin", password = "pass", authorities = "ADMIN")
public class BuildingServiceTest {

    @MockBean
    BuildingDbRepository buildingDbRepository;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    MyUserDetailsService myUserDetailsService;
    @MockBean
    JwtUtil jwtUtil;
    @MockBean
    BuildingConverter buildingConverter;
    @MockBean
    UserRepository userRepository;
    @MockBean
    SecurityAuthoritiesCheck securityAuthoritiesCheck;
    @MockBean
    UserService userService;
    @MockBean
    InputTester inputTester;
    @MockBean
    TextInputStrategy textInputStrategy;
    @MockBean
    CompanyDb companyDb;
    @MockBean
    List<FloorDb> floorsDb;
    @MockBean
    List<BuildingDb> buildingsDb;
    @MockBean
    BuildingDb buildingDb;
    @MockBean
    BuildingService buildingService;

    @Test
    public void testAddBuilding() {
        BuildingDb building = new BuildingDb();
        when(buildingDbRepository.save(building)).thenReturn(building);
    }

    @Test
    public void testGetListByCompany() {
        when(buildingDbRepository.findByCompany(new Long(1))).thenReturn(buildingsDb);
    }

    @Test
    public void testDeleteBuilding() {
        when(buildingDbRepository.save(buildingDb)).thenReturn(buildingDb);
    }

    @Test
    public void testIsNameUnique() {
        when(buildingService.isNameUnique("")).thenReturn(Boolean.TRUE);
    }
}
