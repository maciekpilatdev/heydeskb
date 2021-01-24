package pl.org.conceptweb.heydeskb.controller;

import java.util.Arrays;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.org.conceptweb.heydeskb.converter.CompanyConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Company;
import pl.org.conceptweb.heydeskb.repository.CompanyDbRepository;

@Log
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyDbRepository companyDbRepository;
    @Autowired
    CompanyConverter companyConverter;

    @PostMapping()
    public HttpResponseWrapper addCompany(@RequestBody Company company) {
        HttpResponseWrapper httpResponseWrapper = new HttpResponseWrapper("ok", Arrays.asList(companyDbRepository.save(companyConverter.companyToCompanyDb(company))));       
        log.log(Level.INFO, "CompanyController: addCompany: " + httpResponseWrapper.toString());
        return httpResponseWrapper;
    }
}
