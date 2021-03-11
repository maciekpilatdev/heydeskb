package pl.org.conceptweb.heydeskb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.org.conceptweb.heydeskb.model.Company;
import pl.org.conceptweb.heydeskb.model.CompanyDb;

@Component
@Log
public class CompanyConverter {

    @Autowired
    BuildingConverter buildingConverter;
    @Autowired
    UserConverter userConverter;

    public Company companyDbToCompany(CompanyDb companyDb) throws NullPointerException{

        return new Company(
                companyDb.getId(),
                companyDb.getName(),
                companyDb.getMail(),
                companyDb.getPhone(),
                companyDb.getStreet(),
                companyDb.getStreetNumber(),
                companyDb.getPostalCode(),
                companyDb.getCity(),
                companyDb.getCountry(),
                buildingConverter.buildingsDbToBuildings(companyDb.getBuildingDb()),
                userConverter.usersToUsersTrans(companyDb.getUsers())
        );
    }

    public CompanyDb companyToCompanyDb(Company company) throws NullPointerException{

        return new CompanyDb(
                company.getId(),
                company.getName(),
                company.getMail(),
                company.getPhone(),
                company.getStreet(),
                company.getStreetNumber(),
                company.getPostalCode(),
                company.getCity(),
                company.getCountry(),
                buildingConverter.buildingsToBuildingsDb(company.getBuildings()),
                userConverter.usersTransToUsers(company.getUsersTrans())
        );
    }

    public List<Company> companysDbToCompanys(List<CompanyDb> companysDb) {
        List<Company> companys = new ArrayList();
        try {
            companysDb.forEach(companyDb -> {
                companys.add(companyDbToCompany(companyDb));
            });
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "CompanyConverter: companysDbToCompanys: " + e);
        };
        return companys;
    }
    
    public List<CompanyDb> companysToCompanysDb(List<Company> companys) {
        List<CompanyDb> companysDb = new ArrayList();
        try {
            companys.forEach(company -> {
                companysDb.add(companyToCompanyDb(company));
            });
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "CompanyConverter: companysToCompanysDb: " + e);
        };
        return companysDb;
    }
}
