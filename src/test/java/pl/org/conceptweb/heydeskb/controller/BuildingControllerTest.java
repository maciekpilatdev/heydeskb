package pl.org.conceptweb.heydeskb.controller;

import com.google.gson.Gson;
import pl.org.conceptweb.heydeskb.model.Building;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.service.BuildingService;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
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

@WebMvcTest(BuildingController.class)
@ExtendWith(SpringExtension.class)
@WithMockUser(username = "admin", password = "pass", authorities = "ADMIN")
public class BuildingControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    MyUserDetailsService myUserDetailsService;
    @MockBean
    JwtUtil jwtUtil;
    @MockBean
    private BuildingService buildingService;

    @Test
    public void testAddBuilding() throws Exception {
        List<Long> floors = Arrays.asList(Long.parseLong("1"));
        Building building = new Building(Long.parseLong("1"), "1", floors, Long.parseLong("1"), Boolean.FALSE);
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("ok", "dodano budynek", Arrays.asList(building));
        String requestJson = new Gson().toJson(building);
        when(buildingService.addBuilding(building)).thenReturn(httpResponseWrapper);
        mockMvc.perform(MockMvcRequestBuilders.post("/building").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result[0].name").value("1"));
    }

    @Test
    public void testGetBuildingListByCompany() throws Exception {
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("ok", "", Arrays.asList(1));
        when(buildingService.getBuildingListByCompany()).thenReturn(httpResponseWrapper);
        mockMvc.perform(MockMvcRequestBuilders.get("/building"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result[0]").value("1"));
    }

    @Test
    public void testDeleteBuilding() throws Exception {
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("ok", "deleted", Arrays.asList());
        when(buildingService.deleteBuilding(Long.parseLong("1"))).thenReturn(httpResponseWrapper);
        mockMvc.perform(MockMvcRequestBuilders.delete("/building")
                .param("buildingId", "1"))
                .andExpect(status().isOk());
    }
}
