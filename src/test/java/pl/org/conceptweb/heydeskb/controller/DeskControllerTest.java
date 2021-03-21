package pl.org.conceptweb.heydeskb.controller;

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
import pl.org.conceptweb.heydeskb.model.Desk;
import pl.org.conceptweb.heydeskb.security.MyUserDetailsService;
import pl.org.conceptweb.heydeskb.service.DeskService;
import pl.org.conceptweb.heydeskb.utility.JwtUtil;

@WebMvcTest(DeskController.class)
@ExtendWith(SpringExtension.class)
@WithMockUser(username = "admin", password = "pass", authorities = "ADMIN")
public class DeskControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    @MockBean
    MyUserDetailsService myUserDetailsService;
    @MockBean
    JwtUtil jwtUtil;
    @MockBean
    private DeskService deskService;
    
    @Test
    public void testAdd() throws Exception {
  
        Desk desk = new Desk(new Long(1), new Long(1), new Long(1), new Long(1), new Long(1), true, "1", true, false, Arrays.asList(new Long(1)));
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("status", "message", Arrays.asList(desk));
        String requestJson = new Gson().toJson(desk);
        when(deskService.add(desk)).thenReturn(httpResponseWrapper);
        mockMvc.perform(MockMvcRequestBuilders.post("/desk").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result[0].name").value("1"));
    }

    @Test
    public void testGetListByCompany() throws Exception {
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("status", "message", Arrays.asList(1));
        when(deskService.getListByCompany()).thenReturn(httpResponseWrapper);
        mockMvc.perform(MockMvcRequestBuilders.get("/desk/company"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result[0]").value("1"));
    }

    @Test
    public void testDelete() throws Exception {
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("status", "message", Arrays.asList());
        when(deskService.delete(new Long(1))).thenReturn(httpResponseWrapper);
        mockMvc.perform(MockMvcRequestBuilders.delete("/desk")
                .param("deskId", "1"))
                .andExpect(status().isOk());
    }
    
}
