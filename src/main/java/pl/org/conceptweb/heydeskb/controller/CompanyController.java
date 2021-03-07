package pl.org.conceptweb.heydeskb.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.service.CompanyService;
import pl.org.conceptweb.heydeskb.model.CompanyAndUser;

@Log
@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "https://heydeskb.herokuapp.com/", maxAge = 3600)
public class CompanyController {
    
    @Autowired
    CompanyService companyService;
    
    @PostMapping()
    public HttpResponseWrapper add(@RequestBody CompanyAndUser companyAndUser) {
        return companyService.add(companyAndUser);
    }
}
