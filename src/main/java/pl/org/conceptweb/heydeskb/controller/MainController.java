package pl.org.conceptweb.heydeskb.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

    @GetMapping("/")
    public String main() {
        return "Welcome to uniappbackend!";
    }
}
