package pl.org.conceptweb.heydeskb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.org.conceptweb.heydeskb.constans.Constans;
import pl.org.conceptweb.heydeskb.converter.BuildingConverter;
import pl.org.conceptweb.heydeskb.inputTest.InputTester;
import pl.org.conceptweb.heydeskb.inputTest.TextInputStrategy;
import pl.org.conceptweb.heydeskb.model.HttpResponseWrapper;
import pl.org.conceptweb.heydeskb.model.Building;
import pl.org.conceptweb.heydeskb.model.BuildingDb;
import pl.org.conceptweb.heydeskb.model.MethodResponse;
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
    @Autowired
    UserService userService;
    @Autowired
    InputTester inputTester;
    @Autowired
    TextInputStrategy textInputStrategy;

    public HttpResponseWrapper addBuilding(Building building) {
        try {
            MethodResponse buildingName = inputTester.runTest(textInputStrategy, building.getName());
            if (buildingName.getStatus().equals(Constans.ERROR)) {
                return new HttpResponseWrapper(Constans.ERROR, buildingName.getMessage(), new ArrayList());
            } else if (!isNameUnique(building.getName())) {
                return new HttpResponseWrapper(Constans.ERROR, Constans.NAME_NOT_UNIQUE_ERROR_MESSAGE, new ArrayList());
            } else if (!securityAuthoritiesCheck.hasAuthority(userService.getLogged().getUserName(), Constans.AUTHORITY_ADMIN)) {
                return new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            }
            
            BuildingDb buildingDb = buildingConverter.buildingToBuildingDb(building);
            buildingDb.setCompanyDb(userService.getLogged().getCompanyDb());
            return new HttpResponseWrapper(Constans.OK, Constans.ADD_BUILDING_SUCCESS_MESSAGE, Arrays.asList(buildingConverter.buildingDbToBuilding(buildingDbRepository.save(buildingDb))));
        } catch (Exception e) {
            log.log(Level.WARNING, "ERROR: BuildingService: addBuilding: ", e);
            return new HttpResponseWrapper(Constans.ERROR, Constans.INADEQUATE_DATA, new ArrayList());
        }
    }

    public HttpResponseWrapper getBuildingListByCompany() {
        try {
            return new HttpResponseWrapper(Constans.OK, Constans.GET_BUILDING_LIST_BY_COMPANY_SUCCESS_MESSAGE, buildingConverter.buildingsDbToBuildings(buildingDbRepository.findByCompany(userService.getLogged().getCompanyDb().getId())));
        } catch (Exception e) {
            return new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
    }

    public HttpResponseWrapper deleteBuilding(Long buildingId) {
        try {
            BuildingDb buildingDb = buildingDbRepository.getOne(buildingId);
            if (securityAuthoritiesCheck.hasAuthority(userService.getLogged().getUserName(), "ADMIN")) {
                buildingDb.setIsDeleted(true);
                buildingDbRepository.save(buildingDb);
                return new HttpResponseWrapper(Constans.OK, Constans.DELETE_BUILDING_SUCCESS_MESSAGE, new ArrayList());
            } else {
                return new HttpResponseWrapper(Constans.ERROR, Constans.HAS_AUTHORITY_ERROR_MESSAGE, new ArrayList());
            }
        } catch (NullPointerException e) {
            log.log(Level.WARNING, "ERROR: BuildingService: deleteBuilding: ", e);
            return new HttpResponseWrapper(Constans.ERROR, e.toString(), new ArrayList());
        }
    }

    public Boolean isNameUnique(String buildingName) {
        return buildingDbRepository.findByCompanyAndName(userService.getLogged().getCompanyDb().getId(), buildingName).isEmpty();
    }
}
