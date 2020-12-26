package pl.org.conceptweb.heydeskb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

    @GetMapping("/")
    public String main() {
        return "Welcome to uniappbackend!";
    }

    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    @GetMapping("/user")
    public String user() {
        return "Welcome user!";
    }

    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    @GetMapping("/admin")
    public String admin() {
        return "Welcome admin!";
    }
}
