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

@Service
@Log
public class BuildingService {

    @Autowired
    BuildingDbRepository buildingDbRepository;
    @Autowired
    BuildingConverter buildingConverter;
    @Autowired
    UserRepository userRepository;

    public HttpResponseWrapper addBuikding(Building building, String loggedUserName) {
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
}
