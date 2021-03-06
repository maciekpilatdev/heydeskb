package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.CompanyConverter;
import pl.org.conceptweb.heydeskb.model.Company;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.repository.CompanyDbRepository;

@Log
@Service
public class CompanyService {
    
    @Autowired
    CompanyDbRepository companyDbRepository;
    @Autowired
    CompanyConverter companyConverter;
    
        public HttpResponseWrapper add(Company company) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.ADD_COMPANY_SUCCESS_MESSAGE, Arrays.asList(companyDbRepository.save(companyConverter.companyToCompanyDb(company))));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
