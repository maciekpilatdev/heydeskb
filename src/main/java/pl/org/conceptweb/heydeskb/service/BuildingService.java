package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.BuildingConverter;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Building;
import pl.org.conceptweb.heydeskb.model.BuildingDb;
import pl.org.conceptweb.heydeskb.repository.BuildingDbRepository;
import pl.org.conceptweb.heydeskb.repository.UserRepository;
import pl.org.conceptweb.heydeskb.security.SecurityAuthoritiesCheck;

@Service
@Log
public class BuildingService {

    @Autowired
    BuildingDbRepository buildingDbRepository;
    @Autowired
    BuildingConverter buildingConverter;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SecurityAuthoritiesCheck securityAuthoritiesCheck;

    public HttpResponseWrapper addBuilding(Building building, String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            BuildingDb buildingDb = buildingConverter.buildingToBuildingDb(building);
            buildingDb.setCompanyDb(userRepository.findByUserName(loggedUserName).get().getCompanyDb());
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.ADD_BUILDING_SUCCESS_MESSAGE, Arrays.asList(buildingConverter.buildingDbToBuilding(buildingDbRepository.save(buildingDb))));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }

    public HttpResponseWrapper getBuildingListByCompany(String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        try {
            httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.GET_BUILDING_LIST_BY_COMPANY_SUCCESS_MESSAGE, buildingConverter.buildingsDbToBuildings(buildingDbRepository.getAllByCompany(userRepository.findByUserName(loggedUserName).get().getCompanyDb().getId())));
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }

    public HttpResponseWrapper deleteBuilding(Long buildingId, String loggedUserName) {
        HttpResponseWrapper httpResponseWrapper;
        BuildingDb buildingDb = buildingDbRepository.getOne(buildingId);
        try {
            if (securityAuthoritiesCheck.hasAuthority(loggedUserName, "ADMIN")) {
                buildingDb.setIsDeleted(true);
                buildingDbRepository.save(buildingDb);
                httpResponseWrapper = new HttpResponseWrapper(Constans.OK, Constans.DELETE_BUILDING_SUCCESS_MESSAGE, new ArrayList());
            } else {
                httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            }
        } catch (Exception e) {
            httpResponseWrapper = new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
        return httpResponseWrapper;
    }
}
