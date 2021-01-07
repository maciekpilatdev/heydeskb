package pl.org.conceptweb.heydeskb.controller;

import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class TestController {

    @PostMapping("/test")
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public String testSecurity() {

        log.log(Level.INFO, "TestController: testSecurity: START");

        String respones = "";
        try {
            respones = "All works!";
        } catch (Exception e) {
            respones = "ERROR: " + e;
            log.log(Level.INFO, "Respones: " + e);
        }
        return respones;
    }
}
