package pl.org.conceptweb.heydeskb.converter;

import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.Company;
import pl.org.conceptweb.heydeskb.model.CompanyDb;

@Component
public class CompanyConverter {

    public Company companyDbToCompany(CompanyDb companyDb){
        return new Company(
                companyDb.getId(),
                companyDb.getName(),
                companyDb.getMail(),
                companyDb.getPhone(),
                companyDb.getStreet(),
                companyDb.getStreetNumber(),
                companyDb.getPostalCode(),
                companyDb.getCity(),
                companyDb.getCountry());
    }

    public CompanyDb companyToCompanyDb(Company company){
        return new CompanyDb(
                company.getId(), 
                company.getName(), 
                company.getMail(), 
                company.getPhone(), 
                company.getStreet(), 
                company.getStreetNumber(), 
                company.getPostalCode(), 
                company.getCity(), 
                company.getCountry());
    }
}
