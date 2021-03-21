package pl.org.conceptweb.heydeskb.controller;

import java.security.Principal;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Desk;
import pl.org.conceptweb.heydeskb.service.DeskService;

@RestController
@Log
@RequestMapping("/desk")
@CrossOrigin(origins = "https://heydeskb.herokuapp.com/", maxAge = 3600)
public class DeskController {

    @Autowired
    DeskService deskService;
    
    @PostMapping()
    @CrossOrigin(origins = {"*", "http://localhost:8080", "http://localhost:4200"}, maxAge = 3600)
    public HttpResponseWrapper add(@RequestBody Desk desk) {
        return deskService.add(desk);
    }

    @GetMapping("/company")
    public HttpResponseWrapper getListByCompany() {
        return deskService.getListByCompany();
    }

    @DeleteMapping()
    public HttpResponseWrapper delete(@RequestParam Long deskId) {
        return deskService.delete(deskId);
    }
}
