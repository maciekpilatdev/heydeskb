package pl.org.conceptweb.heydeskb.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Company;
import pl.org.conceptweb.heydeskb.service.CompanyService;

@Log
@RestController
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    CompanyService companyService;
    
    @PostMapping()
    public HttpResponseWrapper add(@RequestBody Company company) {
        return companyService.add(company);
    }
}
